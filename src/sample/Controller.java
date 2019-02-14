package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, String> productPriceColumn;

    @FXML
    private TableColumn<Product, String> productQuantitiyColumn;

    @FXML
    private TableColumn<Product, String> productStoreColumn;

    @FXML
    private TableColumn<Product, String> productExpDateColumn;

    @FXML
    private TextField productAddNameField;

    @FXML
    private TextField productAddPriceField;

    @FXML
    private TextField productAddQuantityField;

    @FXML
    private ChoiceBox storeCheckbox = new ChoiceBox();

    @FXML
    private DatePicker expDatePicker = new DatePicker();


    @FXML
    private ListView<String> productListView;

    private ObservableList<Product> products;
    private ObservableList<String> stores;
    private ObservableList<String> productNames;

    @FXML
    private void initialize(){

        // Initialize Mock Data
        products = initializeMockData();
        productNames = getProductName();
        stores = initializeStore();


        // Populate List View
        productListView.setItems(productNames);
        productListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        // Populate Table View Columns
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productQuantitiyColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productStoreColumn.setCellValueFactory(new PropertyValueFactory<>("store"));
        productExpDateColumn.setCellValueFactory(new PropertyValueFactory<>("expDate"));

        // Add the coulumns and Mock data to table view
        productTableView.setItems(products);
        productTableView.getColumns().clear();
        productTableView.getColumns().addAll(productNameColumn, productPriceColumn, productQuantitiyColumn, productStoreColumn, productExpDateColumn);

        //set checkbox and date picker
        storeCheckbox.setItems(stores);

    }


    private ObservableList<String> initializeStore(){
        ObservableList<String> mockStores = FXCollections.observableArrayList();
        mockStores.addAll("Merkato", "Piyassa", "Bole", "Qera", "Lafto");
        return mockStores;
    }

    public ObservableList<Product> initializeMockData(){

        ObservableList<Product> mockProducts = FXCollections.observableArrayList();

        mockProducts.add(new Product("Laptop Computer", 1000, 10, "Merkato", new Date()));
        mockProducts.add(new Product("Pants", 10, 100, "Merkato", new Date()));
        mockProducts.add(new Product("Ball", 15.99, 10, "Piyassa", new Date()));
        mockProducts.add(new Product("TV", 400, 5, "Bole", new Date()));

        return mockProducts;

    }

    public ObservableList<String> getProductName() {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Product p: products) {
            names.add(p.getName());
        }
        return names;
    }

    @FXML
    public void addProduct() {

        // get new Product values
        String name = productAddNameField.getText();
        String price = productAddPriceField.getText();
        String quantity = productAddQuantityField.getText();
        String store = storeCheckbox.getValue().toString();
        Instant instant = Instant.from(expDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()));
        Date expDate = Date.from(instant);

        //Create New Product
        Product newProduct = new Product(name, Double.parseDouble(price), Integer.parseInt(quantity), store, expDate);

        // Update Views
        products.add(newProduct);
        productNames.add(name);
    }


    @FXML
    public void checkoutProducts(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("checkOutForm.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 400, 355);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            CheckOutForm checkOutFormController = fxmlLoader.<CheckOutForm>getController();
            checkOutFormController.initData()
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
        System.out.println(productListView.getSelectionModel().getSelectedItems());
    }

}
