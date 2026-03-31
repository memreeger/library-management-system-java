package main;

import exception.LibraryException;
import model.Book;
import model.User;
import service.Library;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        seedData(library);

        while (running) {
            printMenu();

            try {
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addBook(scanner, library);
                        break;
                    case 2:
                        addUser(scanner, library);
                        break;
                    case 3:
                        rentBook(scanner, library);
                        break;
                    case 4:
                        returnBook(scanner, library);
                        break;
                    case 5:
                        buyBook(scanner, library);
                        break;
                    case 6:
                        library.showAllBooks();
                        break;
                    case 7:
                        library.showAllUsers();
                        break;
                    case 8:
                        library.showTransactionHistory();
                        break;
                    case 9:
                        searchBookByTitle(scanner, library);
                        break;
                    case 10:
                        searchBookByAuthor(scanner, library);
                        break;
                    case 11:
                        showSortedByTitle(library);
                        break;

                    case 12:
                        showSortedByPrice(library);
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting Library System...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (LibraryException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid data: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    public static void printMenu() {
        System.out.println("======================================");
        System.out.println("         LIBRARY MANAGEMENT SYSTEM");
        System.out.println("======================================");
        System.out.println("1  - Add Book");
        System.out.println("2  - Register User");
        System.out.println("3  - Rent Book");
        System.out.println("4  - Return Book");
        System.out.println("5  - Buy Book");
        System.out.println("6  - Show All Books");
        System.out.println("7  - Show All Users");
        System.out.println("8  - Show Transaction History");
        System.out.println("9  - Search Book By Title");
        System.out.println("10 - Search Book By Author");
        System.out.println("11 - Sort Books by Title");
        System.out.println("12 - Sort Books by Price");
        System.out.println("0  - Exit");
        System.out.println("======================================");
    }

    public static void showSortedByTitle(Library library) {
        List<Book> books = library.getBooksSortedByTitle();

        System.out.println("=== BOOKS SORTED BY TITLE ===");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void showSortedByPrice(Library library) {
        List<Book> books = library.getBooksSortedByPrice();

        System.out.println("=== BOOKS SORTED BY PRICE ===");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void addBook(Scanner scanner, Library library) throws LibraryException {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter total copies: ");
        int totalCopies = Integer.parseInt(scanner.nextLine());

        Book book = new Book(id, title, author, price, totalCopies);
        library.addBook(book);

        System.out.println("Book added successfully.");
    }

    public static void addUser(Scanner scanner, Library library) throws LibraryException {
        System.out.print("Enter user id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter user name: ");
        String name = scanner.nextLine();

        System.out.print("Enter user email: ");
        String email = scanner.nextLine();

        User user = new User(id, name, email);
        library.registerUser(user);

        System.out.println("User registered successfully.");
    }

    public static void rentBook(Scanner scanner, Library library) throws LibraryException {
        System.out.print("Enter user id: ");
        int userId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter book id: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        library.rentBook(userId, bookId);
        System.out.println("Book rented successfully.");
    }

    public static void returnBook(Scanner scanner, Library library) throws LibraryException {
        System.out.print("Enter user id: ");
        int userId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter book id: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        library.returnBook(userId, bookId);
        System.out.println("Book returned successfully.");
    }

    public static void buyBook(Scanner scanner, Library library) throws LibraryException {
        System.out.print("Enter user id: ");
        int userId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter book id: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        library.buyBook(userId, bookId);
        System.out.println("Book purchased successfully.");
    }

    public static void searchBookByTitle(Scanner scanner, Library library) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        List<Book> books = library.searchBookByTitle(title);

        if (books.isEmpty()) {
            System.out.println("No books found with this title.");
            return;
        }

        System.out.println("Books found:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void searchBookByAuthor(Scanner scanner, Library library) {
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        List<Book> books = library.searchBookByAuthor(author);

        if (books.isEmpty()) {
            System.out.println("No books found by this author.");
            return;
        }

        System.out.println("Books found:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void seedData(Library library) {
        try {
            library.addBook(new Book(1, "Anna Karenina", "Tolstoy", 50, 5));
            library.addBook(new Book(2, "Crime and Punishment", "Dostoevsky", 60, 3));
            library.addBook(new Book(3, "The Brothers Karamazov", "Dostoevsky", 70, 2));

            library.registerUser(new User(1, "Cemre", "cemre@mail.com"));
            library.registerUser(new User(2, "Emre", "emre@mail.com"));

        } catch (LibraryException e) {
            System.out.println("Seed data error: " + e.getMessage());
        }
    }
}