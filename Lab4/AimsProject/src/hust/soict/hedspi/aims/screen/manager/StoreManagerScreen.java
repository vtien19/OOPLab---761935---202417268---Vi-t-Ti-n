package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import hust.soict.hedspi.aims.store.Store;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompacDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class StoreManagerScreen extends JFrame {
    private Store store;

    public StoreManagerScreen(Store store) {
        this.store = store;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Store Manager");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreMenu = new JMenuItem("View store");
        menu.add(viewStoreMenu);

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBookMenu = new JMenuItem("Add Book");
        JMenuItem addCDMenu = new JMenuItem("Add CD");
        JMenuItem addDVDMenu = new JMenuItem("Add DVD");

        smUpdateStore.add(addBookMenu);
        smUpdateStore.add(addCDMenu);
        smUpdateStore.add(addDVDMenu);
        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        addBookMenu.addActionListener(e -> {
            new AddBookToStoreScreen(store);
            this.dispose(); // Đóng màn hình hiện tại
        });
        addCDMenu.addActionListener(e -> {
            new AddCompacDiscToStoreScreen(store);
            this.dispose();
        });
        addDVDMenu.addActionListener(e -> {
            new AddDigitalVideoDiscToStoreScreen(store);
            this.dispose();
        });

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        // Hiển thị tối đa 9 vật phẩm lên lưới đồ họa
        int limit = Math.min(mediaInStore.size(), 9);
        for (int i = 0; i < limit; i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }

        return center;
    }

    public static void main(String[] args) {
        Store store = new Store();

        store.addMedia(new DigitalVideoDisc(1, "Harry Potter and the Philosopher's Stone (2001)", "Fantasy", 3.0f, 152, "Chris Columbus"));
        store.addMedia(new DigitalVideoDisc(2, "Harry Potter and the Chamber of Secrets (2002)", "Fantasy", 3.5f, 161, "Chris Columbus"));
        store.addMedia(new DigitalVideoDisc(3, "Harry Potter and the Prisoner of Azkaban (2004)", "Fantasy", 5.0f, 142, "Alfonso Cuarón"));

        // Hàng 2 trong ảnh (Tập 4 Harry Potter và 2 đĩa nhạc CD Fetch the Bolt Cutters, Future Nostalgia)
        store.addMedia(new DigitalVideoDisc(4, "Harry Potter and the Goblet of Fire (2005)", "Fantasy", 4.5f, 157, "Mike Newell"));
        store.addMedia(new CompacDisc(5, "Fetch the Bolt Cutters", "Alternative", 10.39f, 51, "Fiona Apple", "Fiona Apple"));
        store.addMedia(new CompacDisc(6, "Future Nostalgia", "Pop", 9.6f, 37, "Dua Lipa", "Dua Lipa"));

        // Hàng 3 trong ảnh (Bộ 3 cuốn truyện chữ The Hunger Games - Sách không có nút Play)
        store.addMedia(new Book(7, "The Hunger Games", "Dystopian", 5.5f));
        store.addMedia(new Book(8, "Catching Fire", "Dystopian", 4.9f));
        store.addMedia(new Book(9, "Mockingjay", "Dystopian", 5.1f));

        // 3. Khởi chạy giao diện chính của Store Manager
        System.out.println("Bật màn hình giao diện quản lý AIMS...");
        new StoreManagerScreen(store);
    }
}