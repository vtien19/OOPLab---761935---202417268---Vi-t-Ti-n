package hust.soict.hedspi.aims.screen.customer;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class AimsCustomerApp extends Application {

    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception {
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

        primaryStage.setTitle("Store");
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

        DigitalVideoDisc dvd5 = new DigitalVideoDisc(
                "Harry Potter and the Order of the Phoenix (2007)",
                "Fantasy",
                "David Yates",
                138,
                6.5f
        );

        DigitalVideoDisc dvd6 = new DigitalVideoDisc(
                "Harry Potter and the Half-Blood Prince (2009)",
                "Fantasy",
                "David Yates",
                153,
                5.8f
        );

        DigitalVideoDisc dvd7 = new DigitalVideoDisc(
                "Harry Potter and the Deathly Hallows - Part 1 (2010)",
                "Fantasy",
                "David Yates",
                146,
                6.3f
        );

        DigitalVideoDisc dvd8 = new DigitalVideoDisc(
                "Harry Potter and the Deathly Hallows - Part 2 (2011)",
                "Fantasy",
                "David Yates",
                130,
                7.0f
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
        store.addMedia(dvd5);
        store.addMedia(dvd6);
        store.addMedia(dvd7);
        store.addMedia(dvd8);
        store.addMedia(book);

        launch(args);
    }
}