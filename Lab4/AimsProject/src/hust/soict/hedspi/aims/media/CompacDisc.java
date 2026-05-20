package hust.soict.hedspi.aims.media;
import java.util.ArrayList;

public class CompacDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompacDisc(int id, String title, String category, float cost, int length, String director, String artist) {
        super(id, title, category, cost, length, director);
        this.artist = artist;
    }

    public CompacDisc(String title, String category, String director, int length, float cost, String artist) {
        super(0, title, category, cost, length, director);
        this.artist = artist;
    }

    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("Track is already in the list.");
        } else {
            tracks.add(track);
            System.out.println("Track '" + track.getTitle() + "' added.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track '" + track.getTitle() + "' removed.");
        } else {
            System.out.println("Track does not exist in the list.");
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() {
        System.out.println("Playing CD: " + this.getTitle() + " by " + artist);
        System.out.println("Total length: " + this.getLength());
        for (Track track : tracks) {
            track.play();
        }
    }
}