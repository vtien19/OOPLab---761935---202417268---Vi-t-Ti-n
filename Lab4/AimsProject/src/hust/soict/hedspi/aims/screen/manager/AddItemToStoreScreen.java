package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import hust.soict.hedspi.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;

    public AddItemToStoreScreen(Store store , String title) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        // Tái sử dụng thanh menu từ StoreManagerScreen
        StoreManagerScreen dummy = new StoreManagerScreen(store);
        dummy.setVisible(false);

        JMenuBar bar = dummy.createMenuBar();
        bar.getMenu(0).getItem(0).addActionListener(e -> {
            new StoreManagerScreen(store);
            this.dispose();
        });

        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(bar);
        north.add(dummy.createHeader());

        cp.add(north, BorderLayout.NORTH);

        cp.add(createFormCenter(), BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setSize(1024,768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected abstract JPanel createFormCenter();
}
