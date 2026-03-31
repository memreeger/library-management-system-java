package service;

import exception.LibraryException;
import model.Book;
import model.Transaction;
import model.TransactionType;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<Integer, Book> books;
    private Map<Integer, User> users;
    private List<Transaction> transactions;
    private int nextTransactionId;

    public Library() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.transactions = new ArrayList<>();
        this.nextTransactionId = 1;
    }

    public void addBook(Book book) throws LibraryException {
        if (book == null) {
            throw new LibraryException("Book cannot be null.");
        }
        if (books.containsKey(book.getId())) {
            throw new LibraryException("A book with this ID already exists.");
        }
        books.put(book.getId(), book);
    }

    public void removeBook(int bookId) throws LibraryException {
        if (!books.containsKey(bookId)) {
            throw new LibraryException("Book not found.");
        }
        books.remove(bookId);
    }

    public void registerUser(User user) throws LibraryException {
        if (user == null) {
            throw new LibraryException("User cannot be null.");
        }

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new LibraryException("User name cannot be empty.");
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new LibraryException("Email cannot be empty.");
        }

        if (users.containsKey(user.getId())) {
            throw new LibraryException("A user with this ID already exists.");
        }

        if (emailExists(user.getEmail())) {
            throw new LibraryException("A user with this e-mail already exists.");
        }

        users.put(user.getId(), user);
    }

    public boolean emailExists(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        for (User u : users.values()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public List<Book> searchBookByTitle(String title) {
        List<Book> result = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }

        return result;
    }

    public List<Book> searchBookByAuthor(String author) {
        List<Book> result = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }

        return result;
    }

    public void rentBook(int userId, int bookId) throws LibraryException {
        User user = findUserById(userId);
        Book book = findBookById(bookId);

        if (user.hasRentedBook(book)) {
            throw new LibraryException(user.getName() + " already rented this book.");
        }

        if (!book.rentCopy()) {
            throw new LibraryException("Book is not available for rent.");
        }

        user.rentBookInternal(book);
        transactions.add(new Transaction(nextTransactionId++, user, book, TransactionType.RENT));
    }

    public void returnBook(int userId, int bookId) throws LibraryException {
        User user = findUserById(userId);
        Book book = findBookById(bookId);

        if (!user.hasRentedBook(book)) {
            throw new LibraryException(user.getName() + " does not have this book.");
        }

        user.returnBookInternal(book);

        if (!book.returnCopy()) {
            throw new LibraryException("Book return operation failed.");
        }

        transactions.add(new Transaction(nextTransactionId++, user, book, TransactionType.RETURN));
    }

    public void buyBook(int userId, int bookId) throws LibraryException {
        User user = findUserById(userId);
        Book book = findBookById(bookId);

        if (!book.buyCopy()) {
            throw new LibraryException("Book is not available for purchase.");
        }

        user.buyBookInternal(book);
        transactions.add(new Transaction(nextTransactionId++, user, book, TransactionType.BUY));
    }

    private User findUserById(int userId) throws LibraryException {
        User user = users.get(userId);
        if (user == null) {
            throw new LibraryException("User not found.");
        }
        return user;
    }

    private Book findBookById(int bookId) throws LibraryException {
        Book book = books.get(bookId);
        if (book == null) {
            throw new LibraryException("Book not found.");
        }
        return book;
    }

    public void showAllBooks() {
        System.out.println("=== ALL BOOKS ===");
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    public void showAllUsers() {
        System.out.println("=== ALL USERS ===");
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public void showTransactionHistory() {
        System.out.println("=== TRANSACTION HISTORY ===");
        if (transactions.isEmpty()) {
            System.out.println("No transaction history found.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public List<Book> getBooksSortedByTitle() {
        List<Book> sortedBooks = new ArrayList<>(books.values());

        sortedBooks.sort((b1, b2) ->
                b1.getTitle().compareToIgnoreCase(b2.getTitle())
        );

        return sortedBooks;
    }

    public List<Book> getBooksSortedByPrice() {
        List<Book> sortedBooks = new ArrayList<>(books.values());

        sortedBooks.sort((b1, b2) ->
                Double.compare(b1.getPrice(), b2.getPrice())
        );

        return sortedBooks;
    }


}