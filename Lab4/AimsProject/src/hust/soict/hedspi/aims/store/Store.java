package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void print() {
        System.out.println("\n********************STORE********************");
        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println((i+1) + ". " + itemsInStore.get(i).toString());
        }
        System.out.println("*********************************************");
    }

    public Media search(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media; // Trả về đối tượng nếu tìm thấy
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // Thêm mặt hàng vào cửa hàng
    public void addMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("Add '" + media.getTitle() + "' to the store.");
        }
    }

    // Xoá mặt hàng khỏi cửa hàng
    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("Removed '" + media.getTitle() + "' from the store.");
        } else {
            System.out.println("Media not found!");
        }
    }

    // Hiển thị danh sách hàng trong kho
    public void displayStore() {
        System.out.println("\n--- STORE INVENTORY ---");
        for (Media m : itemsInStore) {
            System.out.println(m.toString());
        }
        System.out.println("----------------------\n");
    }

    public ArrayList<Media> getItemsInStore() {
        return this.itemsInStore; // Thay "itemsInStore" bằng tên biến ArrayList trong lớp Store của bạn
    }
}
