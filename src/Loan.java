import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    private final String BOOK_ID;
    private final String USER_ID;
    private final LocalDate ISSUE_DATE;
    private LocalDate returnDate;

    public Loan(String bookId, String userId) {
        this.BOOK_ID = bookId;
        this.USER_ID = userId;
        this.ISSUE_DATE = LocalDate.now();
    }

    public String getBookId() {
        return BOOK_ID;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "book ID = '" + BOOK_ID + '\'' +
                ", user ID = '" + USER_ID + '\'' +
                ", issue Date = " + ISSUE_DATE +
                ", return Date = " + returnDate +
                '}';
    }
}