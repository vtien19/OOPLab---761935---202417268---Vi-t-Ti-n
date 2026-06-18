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
        if (authorName == null || authorName.isBlank()) {
            throw new IllegalArgumentException("ERROR: Author name cannot be empty.");
        }

        if (authors.contains(authorName)) {
            throw new IllegalArgumentException(
                    "ERROR: Author already exists: " + authorName
            );
        }

        authors.add(authorName);
        System.out.println("Author has been added: " + authorName);
    }

    // Hàm xóa tác giả (Đã giữ lại bản đầy đủ thông báo)
    public void removeAuthor(String authorName) {
        if (authorName == null || authorName.isBlank()) {
            throw new IllegalArgumentException("ERROR: Author name cannot be empty.");
        }

        if (!authors.contains(authorName)) {
            throw new IllegalArgumentException(
                    "ERROR: Author is not listed: " + authorName
            );
        }

        authors.remove(authorName);
        System.out.println("Author has been removed: " + authorName);
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