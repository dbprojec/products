package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.Control;

import java.util.ArrayList;

public interface View {
    public void populateView(ArrayList<Product> products);
    public void addProduct(Product product);
}
