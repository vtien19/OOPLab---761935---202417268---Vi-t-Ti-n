package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ViewStoreController {

    private Store store;
    private Cart cart;

    @FXML
    private GridPane gridPane;

    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        final String ITEM_FXML_FILE_PATH =
                "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml";

        int column = 0;
        int row = 0;

        for (int i = 0; i < store.getItemsInStore().size(); i++) {
            try {
                Media media = store.getItemsInStore().get(i);

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(
                        Objects.requireNonNull(getClass().getResource(ITEM_FXML_FILE_PATH))
                );

                AnchorPane itemPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setCart(cart);
                itemController.setData(media);

                gridPane.add(itemPane, column, row);
                GridPane.setMargin(itemPane, new Insets(0, 0, 0, 0));

                column++;

                if (column == 3) {
                    column = 0;
                    row++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnViewCartPressed(ActionEvent event) {
        try {
            final String CART_FXML_FILE_PATH =
                    "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(
                    Objects.requireNonNull(getClass().getResource(CART_FXML_FILE_PATH))
            );

            fxmlLoader.setControllerFactory(controllerClass -> {
                if (controllerClass == CartController.class) {
                    return new CartController(store, cart);
                }

                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Cart");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            showError(e);
        }
    }

    private void showError(Exception e) {
        e.printStackTrace();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("AIMS");
        alert.setHeaderText("Cannot switch screen");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}