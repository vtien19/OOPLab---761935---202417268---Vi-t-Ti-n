package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

// 1. Kế thừa Disc và triển khai Playable
public class DigitalVideoDisc extends Disc implements Playable {

    // Giữ lại biến static để quản lý ID nếu bạn chưa chuyển nó lên lớp Media
    private static int nbDigitalVideoDiscs = 0;

    // 2. Cập nhật các Constructor sử dụng super()
    public DigitalVideoDisc(String title) {
        super(++nbDigitalVideoDiscs, title, null, 0.0f, 0, null);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, 0, null);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, 0, director);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, length, director);
    }

    public DigitalVideoDisc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost, length, director); // Gọi lên lớp cha Disc
    }

    // 3. Triển khai phương thức play() từ Interface Playable
    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            System.err.println("ERROR: DVD length is non-positive!");
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }

    // 4. Override lại toString để hiển thị thông tin DVD
    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + ": " + getCost() + " $";
    }
}