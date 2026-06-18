package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.exception.InvalidDataException;
import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.Comparator;

public class Cart {

    public static final int MAX_NUMBERS_ORDERED = 20;

    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public int getQtyOfDVDs() {
        return itemsOrdered.size();
    }

    public void addMedia(Media media) throws LimitExceededException {
        if (media == null) {
            throw new InvalidDataException("ERROR: Cannot add null media to cart.");
        }

        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            throw new LimitExceededException(
                    "ERROR: The number of media has reached its limit."
            );
        }

        if (itemsOrdered.contains(media)) {
            throw new InvalidDataException(
                    "ERROR: This media is already in the cart: " + media.getTitle()
            );
        }

        itemsOrdered.add(media);
        System.out.println("The media has been added to cart: " + media.getTitle());
    }

    public void addMedia(Media... mediaList) throws LimitExceededException {
        if (mediaList == null) {
            throw new InvalidDataException("ERROR: Media list is null.");
        }

        for (Media media : mediaList) {
            addMedia(media);
        }
    }

    public void removeMedia(Media media) {
        if (media == null) {
            throw new InvalidDataException("ERROR: Cannot remove null media.");
        }

        if (!itemsOrdered.contains(media)) {
            throw new InvalidDataException(
                    "ERROR: This media is not in the cart: " + media.getTitle()
            );
        }

        itemsOrdered.remove(media);
        System.out.println("The media has been removed from cart: " + media.getTitle());
    }

    public float totalCost() {
        float total = 0.0f;

        for (Media media : itemsOrdered) {
            total += media.getCost();
        }

        return total;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");

        for (int i = 0; i < itemsOrdered.size(); i++) {
            Media media = itemsOrdered.get(i);
            System.out.println((i + 1) + ". " + media.toString());
        }

        System.out.println("Total cost: " + totalCost() + " $");
        System.out.println("**************************************************");
    }

    public Media search(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found: " + media);
                return media;
            }
        }

        System.out.println("No media found with ID: " + id);
        return null;
    }

    public Media search(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidDataException("ERROR: Search title cannot be empty.");
        }

        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)
                    || media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Found: " + media);
                return media;
            }
        }

        System.out.println("No media found with title: " + title);
        return null;
    }

    public Media searchById(int id) {
        return search(id);
    }

    public Media searchByTitle(String title) {
        return search(title);
    }

    public void sortByTitle() {
        Collections.sort(itemsOrdered, new Comparator<Media>() {
            @Override
            public int compare(Media media1, Media media2) {
                int titleCompare = media1.getTitle().compareToIgnoreCase(media2.getTitle());

                if (titleCompare != 0) {
                    return titleCompare;
                }

                return Float.compare(media1.getCost(), media2.getCost());
            }
        });

        System.out.println("The cart has been sorted by title.");
    }

    public void sortByCost() {
        Collections.sort(itemsOrdered, new Comparator<Media>() {
            @Override
            public int compare(Media media1, Media media2) {
                int costCompare = Float.compare(media2.getCost(), media1.getCost());

                if (costCompare != 0) {
                    return costCompare;
                }

                return media1.getTitle().compareToIgnoreCase(media2.getTitle());
            }
        });

        System.out.println("The cart has been sorted by cost.");
    }

    public void clear() {
        itemsOrdered.clear();
    }
}