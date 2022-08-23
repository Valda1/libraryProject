package library;

import java.sql.Timestamp;

public class Book {

    private Integer bookID;
    private String bookTitle;
    private String author;
    private int numberOfCopies;
    private Timestamp borrowedAt;
    private Timestamp returnedAt;

    public Book(Integer bookID, String bookTitle, String author, int numberOfCopies) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.author = author;
        this.numberOfCopies = numberOfCopies;
    }

    public Book() {

    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public Timestamp getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(Timestamp borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public Timestamp getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(Timestamp returnedAt) {
        this.returnedAt = returnedAt;
    }
}
