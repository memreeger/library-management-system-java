package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private List<Book> rentedBooks;
    private List<Book> purchasedBooks;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rentedBooks = new ArrayList<>();
        this.purchasedBooks = new ArrayList<>();
    }

    public boolean hasRentedBook(Book book) {
        if (book == null) {
            return false;
        }

        for (Book rentedBook : rentedBooks) {
            if (rentedBook.getId() == book.getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPurchasedBook(Book book) {
        if (book == null) {
            return false;
        }

        for (Book purchasedBook : purchasedBooks) {
            if (purchasedBook.getId() == book.getId()) {
                return true;
            }
        }
        return false;
    }

    public void rentBookInternal(Book book) {
        if (book != null && !hasRentedBook(book)) {
            rentedBooks.add(book);
        }
    }

    public void returnBookInternal(Book book) {
        if (book != null) {
            rentedBooks.removeIf(rentedBook -> rentedBook.getId() == book.getId());
        }
    }

    public void buyBookInternal(Book book) {
        if (book != null && !hasPurchasedBook(book)) {
            purchasedBooks.add(book);
        }
    }

    public void viewBorrowedBooks() {
        System.out.println(name + "'s borrowed books:");
        if (rentedBooks.isEmpty()) {
            System.out.println("No borrowed books.");
            return;
        }

        for (Book book : rentedBooks) {
            System.out.println("- " + book.getTitle());
        }
    }

    public void viewPurchasedBooks() {
        System.out.println(name + "'s purchased books:");
        if (purchasedBooks.isEmpty()) {
            System.out.println("No purchased books.");
            return;
        }

        for (Book book : purchasedBooks) {
            System.out.println("- " + book.getTitle());
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getRentedBooks() {
        return new ArrayList<>(rentedBooks);
    }

    public List<Book> getPurchasedBooks() {
        return new ArrayList<>(purchasedBooks);
    }

    @Override
    public String toString() {
        return "User ID: " + id +
                ", Name: " + name +
                ", Email: " + email +
                ", Rented Books: " + rentedBooks.size() +
                ", Purchased Books: " + purchasedBooks.size();
    }
}