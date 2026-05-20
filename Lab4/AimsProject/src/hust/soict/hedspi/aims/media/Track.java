package hust.soict.hedspi.aims.media;

public class Track implements Playable{
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    @Override
    public void play() {
        if (this.length > 0) {
            System.out.println("Playing track: " + this.getTitle());
            System.out.println("Track length: " + this.getLength());
        } else {
            System.out.println("ERROR: Track " + this.getTitle() + " length is 0 or less!");
        }
    }

    // Getter title và length...

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    // So sánh để chống trùng bài
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Track)) return false;
        Track tmp = (Track)obj;
        boolean titleMatch = (this.title != null) ? this.title.equals(tmp.title) : (tmp.title == null);
        return titleMatch && this.length == tmp.length;
    }
}