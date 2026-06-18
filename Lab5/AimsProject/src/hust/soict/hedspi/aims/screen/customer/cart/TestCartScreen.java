package hust.soict.hedspi.aims.screen.customer.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.screen.customer.controller.CartController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestCartScreen extends Application {

    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String CART_FXML_FILE_PATH =
                "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(CART_FXML_FILE_PATH));

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

        primaryStage.setTitle("Cart");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        store = new Store();
        cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
                "Harry Potter and the Philosopher's Stone (2001)",
                "Fantasy",
                "Chris Columbus",
                152,
                3.0f
        );

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
                "Harry Potter and the Chamber of Secrets (2002)",
                "Fantasy",
                "Chris Columbus",
                161,
                3.5f
        );

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(
                "Harry Potter and the Prisoner of Azkaban (2004)",
                "Fantasy",
                "Alfonso Cuaron",
                142,
                5.0f
        );

        DigitalVideoDisc dvd4 = new DigitalVideoDisc(
                "Harry Potter and the Goblet of Fire (2005)",
                "Fantasy",
                "Mike Newell",
                157,
                4.5f
        );

        Book book = new Book(
                "Green Eggs and Ham",
                "Children",
                3.3f
        );

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        store.addMedia(dvd4);
        store.addMedia(book);

        cart.addMedia(dvd1);
        cart.addMedia(dvd2);
        cart.addMedia(dvd3);
        cart.addMedia(dvd4);

        launch(args);
    }
}