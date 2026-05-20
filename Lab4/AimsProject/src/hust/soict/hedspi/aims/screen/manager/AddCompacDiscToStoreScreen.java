package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.CompacDisc;

public class AddCompacDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle;
    private JTextField tfCategory;
    private JTextField tfCost;
    private JTextField tfArtist;
    private JTextField tfDirector;

    public AddCompacDiscToStoreScreen (Store store) {
        super (store, "Add Compact Disc (CD) to Store");
    }

    @Override
    protected JPanel createFormCenter() {
        JPanel formPanel = new JPanel(new GridLayout(6,2,10,20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50,200,50,200));

        tfTitle = new JTextField();
        tfCategory = new JTextField();
        tfCost = new JTextField();
        tfArtist = new JTextField();
        tfDirector = new JTextField();

        formPanel.add(new JLabel("Title: "));
        formPanel.add(tfTitle);

        formPanel.add(new JLabel("Category: "));
        formPanel.add(tfCategory);

        formPanel.add(new JLabel("Cost ($): "));
        formPanel.add(tfCost);

        formPanel.add(new JLabel("Artist: "));
        formPanel.add(tfArtist);

        formPanel.add(new JLabel("Director: "));
        formPanel.add(tfDirector);

        JButton btnAdd = new JButton("Add CD");
        btnAdd.setFont(new Font(btnAdd.getFont().getName(), Font.BOLD, 16));
        formPanel.add(new JLabel(""));
        formPanel.add(btnAdd);

        btnAdd.addActionListener(e -> {
            try {
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                float cost = Float.parseFloat(tfCost.getText());
                String artist = tfArtist.getText();
                String director = tfDirector.getText();

                // Tạo CD mới (Độ dài length mặc định tạm thời 0 hoặc lấy từ textfield nếu cần
                CompacDisc cd = new CompacDisc(0, title, category, cost, 0, director, artist);
                store.addMedia(cd);

                JOptionPane.showMessageDialog(this,"Cd added successfully!");
                new StoreManagerScreen(store);
                this.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Cost must be a valid number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        return formPanel;
    }
}
