package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.Book;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle, tfCategory, tfCost;

    public AddBookToStoreScreen(Store store) {
        super(store, "Add Book to Store");
    }

    @Override
    protected JPanel createFormCenter() {
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));

        form.add(new JLabel(" Title:"));
        tfTitle = new JTextField();
        form.add(tfTitle);

        form.add(new JLabel(" Category:"));
        tfCategory = new JTextField();
        form.add(tfCategory);

        form.add(new JLabel(" Cost:"));
        tfCost = new JTextField();
        form.add(tfCost);

        JButton btnAdd = new JButton("Add Book");
        form.add(new JLabel("")); // Ô trống để căn chỉnh layout
        form.add(btnAdd);

        // Xử lý sự kiện khi ấn nút Add Book
        btnAdd.addActionListener(e -> {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());

            // Thêm đối tượng Book mới vào store hệ thống
            store.addMedia(new Book(title, category, cost));

            JOptionPane.showMessageDialog(this, "Book added successfully!");
            new StoreManagerScreen(store);
            this.dispose();
        });

        return form;
    }
}