package screens.main.lists;

import interfaces.CartInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import models.Product;

import java.io.IOException;

public class ProductsListViewCell extends ListCell<Product> {

    @FXML
    private Label product_name;

    @FXML
    private Label product_cost;

    @FXML
    private Button sub_button;

    @FXML
    private Button add_button;

    @FXML
    private GridPane gridPane;

    private FXMLLoader mLLoader;

    private CartInterface cartInterface;

    public ProductsListViewCell(CartInterface cartInterface) {
        this.cartInterface = cartInterface;
    }

    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product, empty);

        if(empty || product == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/product_list_cell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            product_name.setText(product.getName());
            product_cost.setText(product.getCost() + " AED");

            sub_button.setOnAction(event -> cartInterface.productRemovedFromCart(product));

            add_button.setOnAction(event -> cartInterface.productAddedToCart(product));

            setText(null);
            setGraphic(gridPane);
        }

    }
}
