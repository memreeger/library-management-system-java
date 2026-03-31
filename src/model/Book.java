package model;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private int totalCopies;
    private int availableCopies;

    public Book(int id, String title, String author, double price, int totalCopies) {
        if (totalCopies < 0) {
            throw new IllegalArgumentException("Total copies cannot be negative.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public boolean rentCopy() {
        if (availableCopies <= 0) {
            return false;
        }
        availableCopies--;
        return true;
    }

    public boolean returnCopy() {
        if (availableCopies >= totalCopies) {
            return false;
        }
        availableCopies++;
        return true;
    }

    public boolean buyCopy() {
        if (availableCopies <= 0 || totalCopies <= 0) {
            return false;
        }
        availableCopies--;
        totalCopies--;
        return true;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    @Override
    public String toString() {
        return "Book ID: " + id +
                ", Title: " + title +
                ", Author: " + author +
                ", Price: " + price +
                ", Total Copies: " + totalCopies +
                ", Available Copies: " + availableCopies;
    }
}