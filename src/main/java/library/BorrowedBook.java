package library;

import java.sql.Timestamp;

public class BorrowedBook {

    private Integer borrowedBookID;
    private String bookTitle;
    private String author;
    private Timestamp borrowedAt;
    private Timestamp returnedAt;

    public BorrowedBook(Integer borrowedBookID, String bookTitle, String author, Timestamp borrowedAt, Timestamp returnedAt) {
        this.borrowedBookID = borrowedBookID;
        this.bookTitle = bookTitle;
        this.author = author;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public BorrowedBook() {
    }

    public Integer getBookID() {
        return borrowedBookID;
    }

    public void setBorrowedBookID(Integer borrowedBookID) {
        this.borrowedBookID = borrowedBookID;
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
