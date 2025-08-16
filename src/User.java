import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    private final String ID;
    private final String NAME;
    private final LocalDate REGISTRATION_DATE;

    public User(String ID, String name) {
        this.ID = ID;
        this.NAME = name;
        this.REGISTRATION_DATE = LocalDate.now();
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID = '" + ID + '\'' +
                ", name = '" + NAME + '\'' +
                ", registration Date = " + REGISTRATION_DATE +
                '}';
    }
}
