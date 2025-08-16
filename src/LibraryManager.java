import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class LibraryManager {
    private final List<Book> BOOKS;
    private final List<User> USERS;
    private final List<Loan> LOANS;

    private final String BOOKS_FILE = "Books.dat";
    private final String USERS_FILE = "Users.dat";
    private final String LOANS_FILE = "Loans.dat";

    public LibraryManager() {
        BOOKS = localDate(BOOKS_FILE);
        USERS = localDate(USERS_FILE);
        LOANS = localDate(LOANS_FILE);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> localDate(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private <T> void saveData(String fileName, List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Error saving to " + fileName);
        }
    }

    private void saveAll() {
        saveData(BOOKS_FILE, BOOKS);
        saveData(USERS_FILE, USERS);
        saveData(LOANS_FILE, LOANS);
    }

    public void addBook(Book book) {
        BOOKS.add(book);
        saveData(BOOKS_FILE, BOOKS);
    }

    public void removeBook(String bookID) {
        Book book = findBookByID(bookID);
        if (book != null && book.isAvailable()) {
            BOOKS.remove(book);
            saveData(BOOKS_FILE, BOOKS);
            System.out.println("Book " + bookID + " removed");
        } else {
            System.err.println("Cannot remove: Book not found:");
        }
    }

    public List<Book> searchBooks(String keyword) {
        return BOOKS.stream().
                filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase())).toList();
    }

    public void listBooks() {
        BOOKS.forEach(books -> System.out.println(books.toString()));
    }

    public Book findBookByID(String ID) {
        return BOOKS.stream().
                filter(b -> b.getID().equals(ID)).findFirst().orElse(null);
    }

    public void addUser(User user) {
        USERS.add(user);
        saveData(USERS_FILE, USERS);
    }

    public void removeUser(String userID) {
        User user = findUserByID(userID);
        if (user != null) {
            USERS.removeIf(u -> u.getID().equals(userID));
            saveData(USERS_FILE, USERS);
            System.out.println("User " + userID + " removed");
        } else {
            System.err.println("Cannot remove: User not found:");
        }
    }

    public void listUsers() {
        USERS.forEach(u -> System.out.println(u.toString()));
    }

    public User findUserByID(String ID) {
        return USERS.stream().filter(u -> u.getID().equals(ID)).
                findFirst().orElse(null);
    }

    public void issuedBook(String bookID, String userID) {
        Book book = findBookByID(bookID);
        book.setAvailable(false);
        LOANS.add(new Loan(bookID, userID));
        saveAll();
        System.out.println("Book " + bookID + " successfully issued " + userID);
    }

    public void returnedBook(String bookID) {
        Loan activeLoan = null;

        for (int i = LOANS.size() - 1; i >= 0; i--) {
            Loan loan = LOANS.get(i);
            if (loan.getBookId().equals(bookID) && !loan.isReturned()) {
                activeLoan = loan;
                break;
            }
        }

        if (activeLoan != null) {
            activeLoan.setReturnDate(LocalDate.now());
            Book book = findBookByID(bookID);
            if (book != null) {
                book.setAvailable(true);
            }
            saveAll();
            System.out.println("Book " + bookID + " returned successfully on " + activeLoan.getReturnDate());
            return;
        }

        for (int i = LOANS.size() - 1; i >= 0; i--) {
            Loan loan = LOANS.get(i);
            if (loan.getBookId().equals(bookID)) {
                System.err.println("This book has already been returned on " + loan.getReturnDate());
                return;
            }
        }
        System.err.println("Cannot return: Book not found in loans");
    }


    public void listCurrentLoans() {
        LOANS.stream().filter(loan -> !loan.isReturned()).
                forEach(System.out::println);
    }

    public void listAllLoans() {
        LOANS.forEach(loan -> System.out.println(loan.toString()));
    }
}