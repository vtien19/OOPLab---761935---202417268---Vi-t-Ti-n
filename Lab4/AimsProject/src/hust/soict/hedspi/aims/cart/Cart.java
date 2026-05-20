package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import java.util.ArrayList;
import java.util.Collections;

public class Cart {
    // Dùng ArrayList để không lo bị đầy bộ nhớ (Vượt quá 20 đĩa)
    private ArrayList<Media> itemsOrdered = new ArrayList<>();

    public int getQtyOfDVDs() {
        int count = 0;
        for (Media m : itemsOrdered) {
            if (m instanceof DigitalVideoDisc) {
                count++;
            }
        }
        return count;
    }

    public Media search(String title) {
        for (Media m : itemsOrdered) {
            // So sánh không phân biệt hoa thường để tìm vật phẩm trong giỏ
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null; // Trả về null nếu không tìm thấy trong giỏ hàng
    }
    public Media search(int id) {
        for (Media m : itemsOrdered) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public void addMedia(Media media) {
        itemsOrdered.add(media);
        System.out.println("The media has been added.");
    }

    public void removeMedia(Media media) {
        if(itemsOrdered.remove(media)) {
            System.out.println("The media has been removed.");
        } else {
            System.out.println("Not found!");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media m : itemsOrdered) {
            total += m.getCost();
        }
        return total;
    }

    public void print() {
        System.out.println("*******************CART*******************");
        System.out.println("Ordered Items: ");

        for (int i = 0; i < itemsOrdered.size(); i++){
            // Lệnh này gọi hàm toString() tương ứng của Book, CD hoặc DVD
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }

        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("*******************************************");
    }

    public void sortByTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Giỏ hàng đã được sắp xếp theo tiêu đề.");
    }

    public void sortByCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);System.out.println("Giỏ hàng đã được sắp xếp theo giá.");
        System.out.println("Giỏ hàng đã được sắp xếp theo giá.");
    }
}
