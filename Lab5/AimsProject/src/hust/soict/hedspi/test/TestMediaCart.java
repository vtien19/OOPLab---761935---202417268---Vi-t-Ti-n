package hust.soict.hedspi.test;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompacDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

import java.util.ArrayList;

public class TestMediaCart {
    public static void main(String[] args) {
        // 1. Khởi tạo Giỏ hàng
        Cart myCart = new Cart();

        // 2. Tạo một số phương tiện Media

        // Tạo DVD
        DigitalVideoDisc dvd = new DigitalVideoDisc(
                "The Lion King",
                "Animation",
                "Roger Allers",
                87,
                19.95f
        );

        // Tạo Sách
        Book book = new Book(
                1,
                "Java Programming",
                "Education",
                25.5f
        );

        book.addAuthor("James Gosling");
        book.addAuthor("Joshua Bloch");

        // Tạo CD và các bài hát Track
        CompacDisc cd = new CompacDisc(
                2,
                "Greatest Hits",
                "Music",
                15.0f,
                0,
                "Various Artists",
                "Queen"
        );

        Track track1 = new Track("Bohemian Rhapsody", 6);
        Track track2 = new Track("We Will Rock You", 3);
        Track track3 = new Track("Invalid Track", -1); // Track lỗi để test exception

        cd.addTrack(track1);
        cd.addTrack(track2);
        cd.addTrack(track3);

        // 3. Thêm tất cả vào danh sách Media chung để test đa hình
        ArrayList<Media> mediaList = new ArrayList<>();

        mediaList.add(dvd);
        mediaList.add(book);
        mediaList.add(cd);

        // 4. Duyệt danh sách và in thông tin
        System.out.println("--- Information of Media in List ---");

        for (Media media : mediaList) {
            System.out.println(media.toString());
        }

        // 5. Thêm vào giỏ hàng và in tổng tiền
        System.out.println("\n--- Adding to Cart ---");

        try {
            myCart.addMedia(dvd);
            myCart.addMedia(book);
            myCart.addMedia(cd);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\n--- Cart Details ---");
        myCart.print();

        // 6. Test tính năng Play
        System.out.println("\n--- Testing Play Feature ---");

        System.out.println("Playing DVD:");
        try {
            dvd.play();
        } catch (PlayerException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\nPlaying CD:");
        try {
            cd.play();
        } catch (PlayerException e) {
            System.err.println(e.getMessage());
        }

        // Book không implements Playable nên không gọi book.play()
    }
}