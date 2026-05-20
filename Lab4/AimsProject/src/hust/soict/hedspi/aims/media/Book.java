package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

// Kế thừa Media để tái sử dụng id, title, category, cost
public class Book extends Media {
    private List<String> authors = new ArrayList<String>();

    // Constructor
    public Book(int id, String title, String category, float cost){
        super(id, title, category, cost);
    }

    public Book(String title, String category, float cost) {
        super(0, title, category, cost);
    }

    // Hàm thêm tác giả (Đã giữ lại bản đầy đủ thông báo)
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Đã thêm tác giả: " + authorName);
        } else {
            System.out.println("Tác giả '" + authorName + "' đã tồn tại!");
        }
    }

    // Hàm xóa tác giả (Đã giữ lại bản đầy đủ thông báo)
    public void removeAuthor(String authorName){
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Đã xoá tác giả: " + authorName);
        } else {
            System.out.println("Không tìm thấy tác giả '" + authorName + "' để xoá!");
        }
    }

    // Hàm lấy danh sách tác giả (nếu cần dùng ở chỗ khác)
    public List<String> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory() + " - Authors: " + authors + ": " + getCost() + " $";
    }
}