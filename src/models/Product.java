package models;

import exceptions.CostLessThanOneException;

public class Product {

    private String id;
    private String name;
    private String description;
    private Category category;
    private Double cost;
    private String orderedQuantity = "1";


    public Product(String id, String name, String description, Category category, Double cost) throws CostLessThanOneException {
        if(cost<1.0)
            throw new CostLessThanOneException("Cost must be greater than zero!");
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(String orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

}
