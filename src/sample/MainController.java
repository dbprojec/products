package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class MainController{

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

    private ArrayList<Product> products;
    private ObservableList<String> stores;
    private ObservableList<String> productNames;
    private AdminView adminView;
    private CustomerView customerView;

    @FXML
    private void initialize(){

        // Initialize Mock Data
        this.products = initializeMockData();
        this.stores = initializeStore();

        this.customerView = new CustomerView(this.products);
        this.adminView = new AdminView(this.products);


        // Populate List View
        this.productListView.setItems(this.customerView.getStringObservableList());
        this.productListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Populate Table View Columns
        this.productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.productQuantitiyColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        this.productStoreColumn.setCellValueFactory(new PropertyValueFactory<>("store"));
        this.productExpDateColumn.setCellValueFactory(new PropertyValueFactory<>("expDate"));

        // Add the coulumns and Mock data to table view
        this.productTableView.setItems(this.adminView.getProductObservableList());
        this.productTableView.getColumns().clear();
        this.productTableView.getColumns().addAll(productNameColumn, productPriceColumn, productQuantitiyColumn, productStoreColumn, productExpDateColumn);

        //set checkbox and date picker
        storeCheckbox.setItems(stores);

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
        this.adminView.addProduct(newProduct);
        this.customerView.addProduct(newProduct);
    }

    private ObservableList<String> initializeStore(){
        ObservableList<String> mockStores = FXCollections.observableArrayList();
        mockStores.addAll("Merkato", "Piyassa", "Bole", "Qera", "Lafto");
        return mockStores;
    }

    public ArrayList<Product> initializeMockData(){

        ArrayList<Product> mockProducts = new ArrayList<>();

        mockProducts.add(new Product("Laptop Computer", 1000, 10, "Merkato", new Date()));
        mockProducts.add(new Product("Pants", 10, 100, "Merkato", new Date()));
        mockProducts.add(new Product("Ball", 15.99, 10, "Piyassa", new Date()));
        mockProducts.add(new Product("TV", 400, 5, "Bole", new Date()));

        return mockProducts;

    }


}
