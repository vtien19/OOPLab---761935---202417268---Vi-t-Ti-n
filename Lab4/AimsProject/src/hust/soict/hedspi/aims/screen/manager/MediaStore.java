package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hust.soict.hedspi.aims.media.Media;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Nếu sản phẩm có thể "Play" đuọc (CD, DVD) thì tạo nút Play
        if (media instanceof hust.soict.hedspi.aims.media.Playable) {
            JButton playButton = new JButton("Play");
            container.add(playButton);

            // Xử lý sự kiện Click nút PLay -> Hiện JDialog thông báo
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog d = new JDialog((JFrame) SwingUtilities.getWindowAncestor(MediaStore.this), "Playing Media", true);
                    d.setLayout(new FlowLayout());
                    d.add(new JLabel("Playing: " + media.getTitle()));
                    d.setSize(300, 150);
                    d.setLocationRelativeTo(null);
                    d.setVisible(true);
                }
            });
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
