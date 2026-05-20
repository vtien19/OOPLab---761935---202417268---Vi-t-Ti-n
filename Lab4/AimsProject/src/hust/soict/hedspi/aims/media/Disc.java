package hust.soict.hedspi.aims.media;

public class Disc extends Media {
    private int length;
    private String director;

    // Constructor cơ bản
    public Disc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost); // Gọi constructor của Media
        this.length = length;
        this.director = director;
    }

    // Constructor dùng cho các trường hợp không có length hoặc director ngay từ đầu
    public Disc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }
}