package hust.soict.hedspi.aims.screen.customer;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompacDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AimsData {

    public static final ObservableList<Media> storeItems = FXCollections.observableArrayList();
    public static final ObservableList<Media> cartItems = FXCollections.observableArrayList();

    static {
        /*
         * Nếu constructor bên project m khác, sửa mấy dòng tạo Book/DVD/CD ở dưới.
         * Phần giao diện phía sau vẫn giữ nguyên.
         */

        storeItems.add(new DigitalVideoDisc(
                "The Lion King",
                "Animation",
                "Roger Allers",
                87,
                19.95f
        ));

        storeItems.add(new DigitalVideoDisc(
                "Star Wars",
                "Science Fiction",
                "George Lucas",
                87,
                24.95f
        ));

        storeItems.add(new DigitalVideoDisc(
                "Aladdin",
                "Animation",
                "John Musker",
                90,
                18.99f
        ));

        storeItems.add(new Book(
                "Harry Potter",
                "Fantasy",
                15.50f
        ));

        storeItems.add(new Book(
                "Clean Code",
                "Programming",
                30.00f
        ));

        storeItems.add(new CompacDisc(
                "Greatest Hits",
                "Music",
                "Various Artists",
                60,
                14.95f,
                "Various Artists"
        ));
    }

    public static float getTotalCost() {
        float total = 0;

        for (Media media : cartItems) {
            total += media.getCost();
        }

        return total;
    }
}