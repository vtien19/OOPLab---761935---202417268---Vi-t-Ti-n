package hust.soict.hedspi.test;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;

import java.util.ArrayList;
import java.util.List;

public class TestAimsClass {
    public static void main(String[] args) {
        List<Media> media = new ArrayList<Media>();
        DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);

        Book book = new Book(1, "Java Programming", "Education", 25.0f);
        book.addAuthor("James Gosling");

        CompacDisc cd = new CompacDisc(2, "Starboy", "Pop", 15.0f,0 , "Dart Punk", "The Weeknd");
        Track track1 = new Track("Starboy", 3);
        Track track2 = new Track("Die For You", 4);
        cd.addTrack(track1);
        cd.addTrack(track2);
        cd.addTrack(track1);

        media.add(dvd);
        media.add(book);
        media.add(cd);

        System.out.println("\n--- Thông tin các vật phẩm phía trong danh sách ---");
        for(Media m : media) {
            System.out.println(m.toString());
        }

        System.out.println("\n--- Thử nghioeemj tính năng Playback ---");
        for(Media m : media) {
            if(m instanceof Playable) {
                ((Playable) m).play();
            } else {
            System.out.println("Vật phẩm: " + m.getTitle() + " không hỗ trợ xem/nghe (Playable).");
            }
        }

        System.out.println("\n--- Kiểm tra giỏ hàng (Cart) ---");
        Cart anOrder = new Cart();
        anOrder.addMedia(dvd);
        anOrder.addMedia(book);
        anOrder.addMedia(cd);

        System.out.println("Tổng chi phí giỏ hàng là: " + anOrder.totalCost() + "$");
    }
}
