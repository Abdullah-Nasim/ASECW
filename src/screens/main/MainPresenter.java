package screens.main;

import models.Category;
import models.Customer;
import models.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class MainPresenter {

    private MainView mainView;

    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();

    MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    void loadData() throws FileNotFoundException {

        ClassLoader classLoader = getClass().getClassLoader();

        File categoriesFile = new File(Objects.requireNonNull(classLoader.getResource("assets/categories.csv")).getFile());
        File productsFile = new File(Objects.requireNonNull(classLoader.getResource("assets/products.csv")).getFile());
        File customersFile = new File(Objects.requireNonNull(classLoader.getResource("assets/customers.csv")).getFile());

        //Reading categories from categories.csv
        Scanner categoriesScanner = new Scanner(categoriesFile);
        categoriesScanner.useDelimiter(";");
        ArrayList<String> categoriesTempArray = new ArrayList<>();
        while(categoriesScanner.hasNext()){
            categoriesTempArray.add(categoriesScanner.next().replace("\r\n", ""));
        }
        for(int i = 0 ; i < categoriesTempArray.size(); i += 2 ){
            categories.add(new Category(categoriesTempArray.get(i), categoriesTempArray.get(i+1)));
        }
        categoriesTempArray.clear();
        //Finished generating categories data structure

        //Reading products from products.csv
        Scanner productsScanner = new Scanner(productsFile);
        productsScanner.useDelimiter(";");
        ArrayList<String> productsTempArray = new ArrayList<>();
        while(productsScanner.hasNext()){
            productsTempArray.add(productsScanner.next().replace("\r\n", ""));
        }
        for(int i = 0 ; i < productsTempArray.size(); i += 5 ){
            products.add(new Product(productsTempArray.get(i), productsTempArray.get(i+1), productsTempArray.get(i+2),
                    getCategoryUsingId(productsTempArray.get(i+3)), Double.parseDouble(productsTempArray.get(i+4))));
        }
        //Finished generating products data structure

        //Reading customers from customer.csv
        Scanner customersScanner = new Scanner(customersFile);
        customersScanner.useDelimiter(";");
        ArrayList<String> customersTempArray = new ArrayList<>();
        while(customersScanner.hasNext()){
            customersTempArray.add(customersScanner.next().replace("\r\n", ""));
        }
        for(int i = 0 ; i < customersTempArray.size(); i += 4 ){
            customers.add(new Customer(customersTempArray.get(i), customersTempArray.get(i+1), customersTempArray.get(i+2), customersTempArray.get(i+3)));
        }
        //Finished generating customers data structure

        categoriesScanner.close();
        productsScanner.close();
        customersScanner.close();

        TreeMap<String, ArrayList<Product>> menuTreeMap = new TreeMap<>();

        menuTreeMap.put("CAT101", getBeverageItems(products));
        menuTreeMap.put("CAT102", getFoodItems(products));
        menuTreeMap.put("CAT103", getOtherItems(products));

        mainView.onMenuLoaded(menuTreeMap);
    }

    private ArrayList<Product> getBeverageItems(ArrayList<Product> products){
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

    private Category getCategoryUsingId(String Id){
        for (Category category : categories) {
            if (category.getId().equals(Id))
                return category;
        }
        return null;
    }
}
