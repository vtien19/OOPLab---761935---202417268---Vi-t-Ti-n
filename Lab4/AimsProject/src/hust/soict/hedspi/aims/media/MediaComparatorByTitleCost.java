package hust.soict.hedspi.aims.media;
import java.util.Comparator;

// Bộ so sánh 1: ưu tiên Title, sau đó đến Cost
public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare (Media m1, Media m2) {
        // so sánh title trước
        int titleDiff = m1.getTitle().compareToIgnoreCase(m2.getTitle());
        if (titleDiff != 0) return titleDiff;

        // Nếu dừng title, so sánh cose (đắt hơn đứng trước -> m2 so với m1)
        return Float.compare(m2.getCost(), m2.getCost());
    }
}

// Bộ so sánh 2: Ưu tiên Cost, sau đó đến Title
class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        // So sánh cost trước (giảm dần)
        int costDiff = Float.compare(m2.getCost(), m1.getCost());
        if (costDiff != 0) return costDiff;

        // Nếu trùng cost, so sánh title alphabet
        return m1.getTitle().compareToIgnoreCase(m2.getTitle());
    }
}
