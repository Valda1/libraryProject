package repository;

import library.Book;
import library.BorrowedBook;

import java.sql.*;
import java.util.ArrayList;

public class LibraryRepository {
    Connection conn = new DBManager().getConnection();

    public void addBookToDB(Book newBook) throws SQLException {
        String sql = "INSERT INTO booksInLibrary (bookTitle, author, numberOfCopies) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, newBook.getBookTitle());
        preparedStatement.setString(2, newBook.getAuthor());
        preparedStatement.setInt(3, newBook.getNumberOfCopies());

        preparedStatement.execute();

    }

    public ArrayList<Book> getAllBooksFromDB() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM booksInLibrary";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            int bookID = resultSet.getInt(1);
            String bookTitle = resultSet.getString(2);
            String author = resultSet.getString(3);
            int numberOfCopies = resultSet.getInt(4);

            String output = "Book info: \n\t Book ID: %d \n\t Title: %s" +
                    " \n\t Author: %s \n\t Number of copies: %d";

            System.out.println(String.format(output, bookID, bookTitle, author, numberOfCopies));
        }
        return books;

    }

    public void updateBookTitle(Integer bookID, String bookTitle) throws SQLException {
        String sql = "UPDATE booksInLibrary SET bookTitle = ? WHERE bookID = ?;";
        this.updateBook(sql, bookID, bookTitle);

    }

    public void updateAuthor(Integer bookID, String author) throws SQLException {
        String sql = "UPDATE booksInLibrary SET author = ? WHERE bookID = ?;";
        this.updateBook(sql, bookID, author);

    }

    public void updateNumberOfCopies(Integer bookID, int numberOfCopies) throws SQLException {
        String sql = "UPDATE booksInLibrary SET numberOfCopies = ? WHERE bookID = ?;";
        this.updateBook(sql, bookID, String.valueOf(numberOfCopies));

    }

    public void updateBook(String sql, Integer bookID, String value) throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, value);
        preparedStatement.setInt(2, bookID);

        preparedStatement.executeUpdate();

    }


    public void deleteBookFromDB(Integer bookID) throws SQLException {
        String sql = "DELETE FROM booksInLibrary WHERE bookID = ?;";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, bookID);

        if (preparedStatement.executeUpdate() == 0) {
            throw new SQLException("Error occurred while deleting a book from database!");
        }
    }


    public void borrowBookFromDB(Integer bookID) throws SQLException {

        String sql = "SELECT bookID FROM borrowedBooks WHERE bookID = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setInt(1, bookID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println("You have currently already borrowed this book!");
            System.out.println(" ");
        } else {
            String sql2 = "INSERT INTO borrowedBooks (bookID, bookTitle, author) SELECT bookID, bookTitle, author FROM booksInLibrary WHERE bookID = ?;";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);

            preparedStatement2.setInt(1, bookID);
            preparedStatement2.executeUpdate();


            reduceNumberOfCopies(bookID);
        }

    }

    public void reduceNumberOfCopies(Integer bookID) throws SQLException{
        String sql = "UPDATE booksInLibrary SET numberOfCopies = numberOfCopies - 1 WHERE bookID = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setInt(1, bookID);
        preparedStatement.execute();
    }


    public ArrayList<BorrowedBook> getBorrowedBooksFromDB() throws SQLException {
        ArrayList<BorrowedBook> borrowedBooks = new ArrayList<>();
        String sql = "SELECT * FROM borrowedBooks";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            int borrowedBookID = resultSet.getInt(1);
            String bookTitle = resultSet.getString(3);
            String author = resultSet.getString(4);
            Timestamp borrowedAt = resultSet.getTimestamp(5);

            String output = "Borrowed book info: \n\t Book ID: %d \n\t Title: %s" +
                    " \n\t Author: %s \n\t Borrowed at: %s";

            System.out.println(String.format(output, borrowedBookID, bookTitle, author, borrowedAt));
        }
        return borrowedBooks;


    }

    public void returnBookInDB(Integer borrowedBookID) throws SQLException {
        increaseNumberOfCopies(borrowedBookID);

        String sql = "DELETE FROM borrowedBooks WHERE borrowedBookID = ?;";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, borrowedBookID);
        preparedStatement.executeUpdate();



        /*if(borrowedBookID != null){
            String sql = "DELETE FROM borrowedBooks WHERE borrowedBookID = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, borrowedBookID);
            preparedStatement.executeUpdate();
        }else{
            System.out.println("Error occurred while returning book!");
        }*/


    }

    public void increaseNumberOfCopies(Integer borrowedBookID) throws SQLException{

            String sql = "UPDATE booksInLibrary JOIN borrowedBooks ON booksInLibrary.bookID = borrowedBooks.bookID" +
                    " SET booksInLibrary.numberOfCopies = booksInLibrary.numberOfCopies + 1 WHERE borrowedBooks.borrowedBookID = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, borrowedBookID);
            preparedStatement.execute();

        }


    }


