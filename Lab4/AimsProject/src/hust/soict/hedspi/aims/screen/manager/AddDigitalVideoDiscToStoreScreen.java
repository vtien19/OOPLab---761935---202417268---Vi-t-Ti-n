package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;

import javax.swing.*;
import java.awt.*;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.CompacDisc;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle;
    private JTextField tfCategory;
    private JTextField tfCost;
    private JTextField tfDirector;
    private JTextField tfLength;

    public AddDigitalVideoDiscToStoreScreen (Store store) {
        super (store, "Add Digital Video Disc (DVD) to Store");
    }

    @Override
    protected JPanel createFormCenter() {
        JPanel formPanel = new JPanel(new GridLayout(6,2,10,20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        tfTitle = new JTextField();
        tfCategory = new JTextField();
        tfCost = new JTextField();
        tfDirector = new JTextField();
        tfLength = new JTextField();

        formPanel.add(new JLabel("Title: "));
        formPanel.add(tfTitle);

        formPanel.add(new JLabel("Category: "));
        formPanel.add(tfCategory);

        formPanel.add(new JLabel("Cost ($): "));
        formPanel.add(tfCost);

        formPanel.add(new JLabel("Length (mins): "));
        formPanel.add(tfLength);

        formPanel.add(new JLabel("Director: "));
        formPanel.add(tfDirector);

        JButton btnAdd = new JButton("Add DVD");
        btnAdd.setFont(new Font(btnAdd.getFont().getName(), Font.BOLD, 16));
        formPanel.add(new JLabel(""));
        formPanel.add(btnAdd);

        btnAdd.addActionListener(e -> {
            try {
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                float cost = Float.parseFloat(tfCost.getText());
                String director = tfDirector.getText();
                int length = Integer.parseInt(tfLength.getText());

                DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
                store.addMedia(dvd);

                JOptionPane.showMessageDialog(this, "DVD added successfully!");
                new StoreManagerScreen(store);
                this.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Cost and Length must be valid numbers!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        return formPanel;
    }
}
