package hust.soict.hedspi.aims.media;

import java.util.Comparator;
import java.util.Objects;

public abstract class Media implements Comparable<Media> {
    private int id;
    private String title;
    private String category;
    private float cost;

    // Constructor
    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        setCost(cost);
    }

    // Các bộ so sánh Comparator
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    // Getter và Setter
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCost(float cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("ERROR: Cost cannot be negative.");
        }

        this.cost = cost;
    }

    /*
     * Hai Media bằng nhau nếu có cùng title và cost.
     * Dùng instanceof để tránh ClassCastException.
     * Dùng Objects.equals để tránh NullPointerException khi title bị null.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Media)) {
            return false;
        }

        Media other = (Media) obj;

        return Objects.equals(this.title, other.title)
                && Float.compare(this.cost, other.cost) == 0;
    }

    /*
     * Khi override equals(), nên override hashCode().
     * Điều này giúp Media hoạt động đúng trong HashSet, HashMap,...
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, cost);
    }

    /*
     * Natural ordering của Media:
     * - So sánh title trước theo alphabet.
     * - Nếu title giống nhau thì so sánh cost.
     *
     * Nếu other == null thì chủ động ném NullPointerException
     * với message rõ ràng.
     */
    @Override
    public int compareTo(Media other) {
        if (other == null) {
            throw new NullPointerException("ERROR: Cannot compare Media with null.");
        }

        int titleCompare = compareTitle(this.title, other.title);

        if (titleCompare != 0) {
            return titleCompare;
        }

        return Float.compare(this.cost, other.cost);
    }

    private int compareTitle(String title1, String title2) {
        if (title1 == null && title2 == null) {
            return 0;
        }

        if (title1 == null) {
            return -1;
        }

        if (title2 == null) {
            return 1;
        }

        return title1.compareToIgnoreCase(title2);
    }
}