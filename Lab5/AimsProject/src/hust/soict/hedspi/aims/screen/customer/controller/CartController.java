package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CartController {

    private Store store;
    private Cart cart;

    private FilteredList<Media> filteredMedia;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label costLabel;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(
                new PropertyValueFactory<Media, Integer>("id")
        );

        colMediaTitle.setCellValueFactory(
                new PropertyValueFactory<Media, String>("title")
        );

        colMediaCategory.setCellValueFactory(
                new PropertyValueFactory<Media, String>("category")
        );

        colMediaCost.setCellValueFactory(
                new PropertyValueFactory<Media, Float>("cost")
        );

        filteredMedia = new FilteredList<>(cart.getItemsOrdered(), media -> true);
        tblMedia.setItems(filteredMedia);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> updateButtonBar(newValue));

        tfFilter.textProperty()
                .addListener((observable, oldValue, newValue) -> showFilteredMedia(newValue));

        radioBtnFilterId.selectedProperty()
                .addListener((observable, oldValue, newValue) -> showFilteredMedia(tfFilter.getText()));

        radioBtnFilterTitle.selectedProperty()
                .addListener((observable, oldValue, newValue) -> showFilteredMedia(tfFilter.getText()));

        updateTotalCost();
    }

    private void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);

            if (media instanceof Playable) {
                btnPlay.setVisible(true);
            } else {
                btnPlay.setVisible(false);
            }
        }
    }

    private void showFilteredMedia(String keyword) {
        try {
            filteredMedia.setPredicate(media -> {
                if (keyword == null || keyword.isBlank()) {
                    return true;
                }

                String lowerKeyword = keyword.toLowerCase();

                if (radioBtnFilterId.isSelected()) {
                    return String.valueOf(media.getId()).contains(lowerKeyword);
                }

                if (radioBtnFilterTitle.isSelected()) {
                    return media.getTitle().toLowerCase().contains(lowerKeyword);
                }

                return true;
            });

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("AIMS");
            alert.setHeaderText("Filter error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void updateTotalCost() {
        costLabel.setText(String.format("%.1f $", cart.totalCost()));
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();

        if (media == null) {
            return;
        }

        if (!(media instanceof Playable)) {
            return;
        }

        try {
            ((Playable) media).play();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AIMS");
            alert.setHeaderText(null);
            alert.setContentText("Playing: " + media.getTitle());
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("AIMS");
            alert.setHeaderText("Cannot play media");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        try {
            Media media = tblMedia.getSelectionModel().getSelectedItem();

            if (media == null) {
                throw new IllegalArgumentException("Please select a media first.");
            }

            cart.removeMedia(media);
            updateTotalCost();
            updateButtonBar(null);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("AIMS");
            alert.setHeaderText("Cannot remove media");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        try {
            if (cart.getItemsOrdered().isEmpty()) {
                throw new IllegalStateException("Your cart is empty.");
            }

            cart.getItemsOrdered().clear();
            tfFilter.clear();
            updateTotalCost();
            updateButtonBar(null);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AIMS");
            alert.setHeaderText(null);
            alert.setContentText("Your order has been placed successfully.");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("AIMS");
            alert.setHeaderText("Cannot place order");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH =
                    "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(
                    Objects.requireNonNull(getClass().getResource(STORE_FXML_FILE_PATH))
            );

            fxmlLoader.setControllerFactory(controllerClass -> {
                if (controllerClass == ViewStoreController.class) {
                    return new ViewStoreController(store, cart);
                }

                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Store");
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