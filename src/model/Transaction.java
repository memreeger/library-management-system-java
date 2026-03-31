package model;

import java.time.LocalDate;

public class Transaction {
    private int transactionId;
    private User user;
    private Book book;
    private LocalDate date;
    private TransactionType type;

    public Transaction(int transactionId, User user, Book book, TransactionType type) {
        this.transactionId = transactionId;
        this.user = user;
        this.book = book;
        this.type = type;
        this.date = LocalDate.now();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
                ", User: " + user.getName() +
                ", Book: " + book.getTitle() +
                ", Type: " + type +
                ", Date: " + date;
    }
}