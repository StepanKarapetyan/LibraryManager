import java.io.Serializable;

public class Book implements Serializable {
    private final String ID;
    private final String TITLE;
    private final String AUTHOR;
    private final int YEAR;
    private boolean isAvailable;

    public Book(String ID, String title, String author, int year) {
        this.ID = ID;
        this.TITLE = title;
        this.AUTHOR = author;
        this.YEAR = year;
        this.isAvailable = true;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return TITLE;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID = '" + ID + '\'' +
                ", title = '" + TITLE + '\'' +
                ", author = '" + AUTHOR + '\'' +
                ", year = " + YEAR +
                ", is Available = " + isAvailable +
                '}';
    }
}