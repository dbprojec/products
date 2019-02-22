package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class AdminView implements View {
    private ArrayList<Product> products;
    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();

    public AdminView(ArrayList<Product> products) {
        this.products = products;
        this.populateView(this.products);
    }

    public ObservableList<Product> getProductObservableList() {
        return productObservableList;
    }


    @Override
    public void populateView(ArrayList<Product> products) {
        this.productObservableList.addAll(products);

    }

    @Override
    public void addProduct(Product product) {
        this.productObservableList.add(product);

    }
}
