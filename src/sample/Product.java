package sample;

import java.util.Date;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private String store;
    private Date expDate;

    public Product() {
        this.name = "";
        this.price = 0;
        this.quantity = 0;
    }

    public Product(String name, double price, int quantity, String store, Date expDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.store = store;
        this.expDate = expDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setExpDate(Date expDate) {
        expDate = expDate;
    }

    public String getStore() {

        return store;
    }

    public Date getExpDate() {
        return expDate;
    }
}
