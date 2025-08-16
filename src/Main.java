import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryManager lm = new LibraryManager();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("====MENU====");
            System.out.println("1. add book");
            System.out.println("2. remove book");
            System.out.println("3. search book");
            System.out.println("4. list books");
            System.out.println("5. add user");
            System.out.println("6. Remove User");
            System.out.println("7. List Users");
            System.out.println("8. Issue Book");
            System.out.println("9. Return Book");
            System.out.println("10. Current Loans");
            System.out.println("11. All Loans");
            System.out.println("0. Exit");

            double choice = sc.nextDouble();
            sc.nextLine();

            switch ((int) choice) {
                case 1:
                    while (true){
                        System.out.println("Enter Book ID: Or '0' to finish or exit: ");
                        String id = sc.nextLine().trim();

                        if(id.equalsIgnoreCase("0")){
                            break;
                        }

                        if (lm.findBookByID(id) != null) {
                            System.err.println("Cannot add: Book with ID " + id + " already exists.");
                            continue;
                        }

                        System.out.println("Enter Book Title: ");
                        String title = sc.nextLine().trim();

                        System.out.println("Enter Book Author: ");
                        String author = sc.nextLine().trim();

                        int year;
                        while (true) {
                            System.out.println("Enter Book Year: ");
                            String input = sc.next().trim();
                            try {
                                year = Integer.parseInt(input);
                                break;
                            } catch (NumberFormatException e) {
                                System.err.println("Invalid year. Please enter a number.");
                            }
                        }
                        sc.nextLine();
                        lm.addBook(new Book(id, title, author, year));
                        System.out.println("Book '" + title + "' successfully added.");
                    }
                    break;
                case 2:
                    lm.listBooks();
                    while (true){
                        System.out.println("Enter book ID to remove: Or '0' to finish or exit: ");
                        String id = sc.nextLine().trim();
                        if(id.equalsIgnoreCase("0")){
                            break;
                        }
                        lm.removeBook(id);
                    }
                    break;
                case 3:
                    while (true){
                        System.out.print("Search Book keyword: or '0' to finish or exit: ");
                        String keyword = sc.nextLine().trim();
                        if(keyword.equalsIgnoreCase("0")){
                            break;
                        }
                        lm.searchBooks(keyword).forEach(System.out::println);
                    }
                break;
                case 4:
                    lm.listBooks();
                    System.err.println("Press Enter to return MENU");
                    sc.nextLine();
                    break;
                case 5:
                    while (true){
                        System.out.println("Enter user ID: or '0' to finish or exit: ");
                        String id = sc.nextLine().trim();

                        if(id.equalsIgnoreCase("0")){
                            break;
                        }

                        if(lm.findUserByID(id) != null) {
                            System.err.println("Cannot add: User with ID " + id + " already exists.");
                            continue;
                        }

                        System.out.println("Enter User Name: ");
                        String name = sc.nextLine().trim();

                        lm.addUser(new User(id, name));
                    }
                    break;
                case 6:
                    lm.listUsers();
                    while (true){
                        System.out.println("Enter user ID to remove user: or '0' to finish or exit: ");
                        String id = sc.nextLine().trim();

                        if(id.equalsIgnoreCase("0")){
                            break;
                        }
                        lm.removeUser(id);
                    }
                    break;
                case 7:
                    lm.listUsers();
                    System.err.println("Press Enter to return MENU");
                    sc.nextLine();
                    break;
                case 8:
                    while (true){
                        System.out.println("Enter book ID issued: or '0' to finish or exit: ");
                        String bookId = sc.nextLine().trim();
                        Book book = lm.findBookByID(bookId);

                        if(bookId.equalsIgnoreCase("0")){
                            break;
                        }

                        if(book == null) {
                            System.err.println("Cannot issue: Book with ID " + bookId + " not found.");
                            continue;
                        }

                        System.out.println("Enter User ID: ");
                        String userId = sc.nextLine().trim();
                        User user = lm.findUserByID(userId);

                        if(user == null) {
                            System.err.println("Cannot issue: User with ID " + userId + " not found.");
                            continue;
                        }

                        if (!book.isAvailable()) {
                            System.err.println("Book already issued.");
                            continue;
                        }

                        lm.issuedBook(bookId, userId);
                    }
                    break;
                case 9:
                    while (true) {
                        lm.listAllLoans();
                        System.out.println("Enter Book ID to return: or '0' to finish or exit: ");
                        String id = sc.nextLine().trim();

                        if(id.equalsIgnoreCase("0")){
                            break;
                        }
                        lm.returnedBook(id);
                    }
                    break;
                case 10:
                    lm.listCurrentLoans();
                    System.err.println("press Enter to return MENU");
                    sc.nextLine();
                    break;
                case 11:
                    lm.listAllLoans();
                    System.err.println("press Enter to return MENU");
                    sc.nextLine();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    flag = false;
                    break;
                default:
                    System.err.println("Invalid choice");
            }
        }
    }
}