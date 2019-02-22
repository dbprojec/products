package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CustomerView implements View{
    private ArrayList<Product> products;
    private ObservableList<String> stringObservableList = FXCollections.observableArrayList();

    public CustomerView(ArrayList<Product> products) {
        this.products = products;
        this.populateView(this.products);
    }

    public ObservableList<String> getStringObservableList() {
        return stringObservableList;
    }

    @Override
    public void populateView(ArrayList<Product> products) {
        for (Product p: products) {
            this.stringObservableList.add(p.getName());
        }
    }

    @Override
    public void addProduct(Product product) {
        this.stringObservableList.add(product.getName());

    }
}
