package screens.main;

import exceptions.CostLessThanOneException;
import exceptions.InvalidCSVFormatException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import models.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

class MainPresenter {

    private MainView mainView;

    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();

    private ClassLoader classLoader = getClass().getClassLoader();

    MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    void loadData() throws FileNotFoundException, InvalidCSVFormatException, CostLessThanOneException {

        File categoriesFile = new File(Objects.requireNonNull(classLoader.getResource("assets/categories.csv")).getFile());
        File productsFile = new File(Objects.requireNonNull(classLoader.getResource("assets/products.csv")).getFile());
        File customersFile = new File(Objects.requireNonNull(classLoader.getResource("assets/customers.csv")).getFile());

        //Reading categories from categories.csv
        Scanner categoriesScanner = new Scanner(categoriesFile);
        categoriesScanner.useDelimiter(";");
        try{
            ArrayList<String> categoriesTempArray = new ArrayList<>();
            while(categoriesScanner.hasNext()){
                categoriesTempArray.add(categoriesScanner.next().replace("\r\n", ""));
            }
            for(int i = 0 ; i < categoriesTempArray.size(); i += 2 ){
                categories.add(new Category(categoriesTempArray.get(i), categoriesTempArray.get(i+1)));
            }
            categoriesTempArray.clear();
        }catch (Exception ex){
            throw new InvalidCSVFormatException("Invalid CSV Format Exception");
        }
        //Finished generating categories data structure

        //Reading products from products.csv
        Scanner productsScanner = new Scanner(productsFile);
        productsScanner.useDelimiter(";");
        try{
            ArrayList<String> productsTempArray = new ArrayList<>();
            while(productsScanner.hasNext()){
                productsTempArray.add(productsScanner.next().replace("\r\n", ""));
            }
            for(int i = 0 ; i < productsTempArray.size(); i += 5 ){
                products.add(new Product(productsTempArray.get(i), productsTempArray.get(i+1), productsTempArray.get(i+2),
                        getCategoryUsingId(productsTempArray.get(i+3)), Double.parseDouble(productsTempArray.get(i+4))));
                Report.getInstance().reportHashMap.put(productsTempArray.get(i),0);
            }
            productsTempArray.clear();
        }catch (Exception ex){
            if(ex instanceof InvalidCSVFormatException)
                throw new InvalidCSVFormatException("Invalid CSV Format Exception");
            else if (ex instanceof CostLessThanOneException)
                throw new CostLessThanOneException("All the products prices should be greater than 0. Kindly check the CSV files and try again!");
            else
                ex.printStackTrace();
        }
        //Finished generating products data structure

        //Reading customers from customer.csv
        Scanner customersScanner = new Scanner(customersFile);
        customersScanner.useDelimiter(";");
        try{
            ArrayList<String> customersTempArray = new ArrayList<>();
            while(customersScanner.hasNext()){
                customersTempArray.add(customersScanner.next().replace("\r\n", ""));
            }
            for(int i = 0 ; i < customersTempArray.size(); i += 4 ){
                customers.add(new Customer(customersTempArray.get(i), customersTempArray.get(i+1), customersTempArray.get(i+2), customersTempArray.get(i+3)));
            }
            customersTempArray.clear();
            //Finished generating customers data structure
        }catch (Exception ex){
            throw new InvalidCSVFormatException("Invalid CSV Format Exception");
        }

        categoriesScanner.close();
        productsScanner.close();
        customersScanner.close();

        TreeMap<String, ArrayList<Product>> menuTreeMap = new TreeMap<>();

        menuTreeMap.put("CAT101", getBeverageItems());
        menuTreeMap.put("CAT102", getFoodItems(products));
        menuTreeMap.put("CAT103", getOtherItems(products));

        mainView.onMenuLoaded(menuTreeMap);
    }

    public ArrayList<Product> getBeverageItems() {
        ArrayList<Product> tempItemsArray = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().getId().equals("CAT101"))
                tempItemsArray.add(product);
        }
        return tempItemsArray;
    }


    private ArrayList<Product> getFoodItems(ArrayList<Product> products){
        ArrayList<Product> tempItemsArray = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().getId().equals("CAT102"))
                tempItemsArray.add(product);
        }
        return tempItemsArray;
    }

    private ArrayList<Product> getOtherItems(ArrayList<Product> products){
        ArrayList<Product> tempItemsArray = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().getId().equals("CAT103"))
                tempItemsArray.add(product);
        }
        return tempItemsArray;
    }

    public Category getCategoryUsingId(String Id){
        for (Category category : categories) {
            if (category.getId().equals(Id))
                return category;
        }
        return null;
    }

    void generateReportAndExit() throws Exception{
        File report = new File(Objects.requireNonNull(classLoader.getResource("assets/report.txt")).getFile());
        PrintWriter writer = new PrintWriter(report, "UTF-8");
        writer.println(String.format("%20s %50s %20s \r\n", "Product ID", "Product Name","Quantity Sold"));
        writer.println("\n");
        writer.println("\n");
        for(Product product: products){
            writer.println(String.format("%20s %50s %20s \r\n", product.getId(), product.getName(), Report.getInstance().reportHashMap.get(product.getId())));
        }

        writer.println("\n");
        writer.println("\n");

        writer.println(String.format("%20s %50s %20s \r\n", "", "Total:", Report.getInstance().totalIncome + " AED"));

        writer.close();

        Desktop.getDesktop().open(report);

        Platform.exit();
        System.exit(0);

    }

    String generateOrderNumber(){
        if(Order.getInstance().orders.size() == 0)
            return "ORDER101";
        else
            return "ORDER" + (Integer.valueOf(Order.getInstance().orders.get(Order.getInstance().orders.size()-1).getId().substring(5)) + 1);
    }

    String generateCustomerNumber(){
        if(Order.getInstance().orders.size() == 0)
            return "CUST101";
        else
            return "CUST" + (Integer.valueOf(Order.getInstance().orders.get(Order.getInstance().orders.size()-1).getCustomerId().substring(4)) + 1);
    }

    String generateCurrentTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }

    boolean checkForDiscountEligibility(ObservableList<Product> cartObservableList){
        int numFoodItems = 0;
        int numBeverages = 0;

        for (Product product : cartObservableList) {
            if (product.getId().startsWith("FOOD"))
                numFoodItems = numFoodItems + 1;
            if (product.getId().startsWith("BRV"))
                numBeverages = numBeverages + 1;
        }

        return numFoodItems >= 2 && numBeverages >= 1;
    }
}
