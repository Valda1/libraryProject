package controller;

import library.Book;
import library.BorrowedBook;
import repository.DBManager;
import repository.LibraryRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryController {

    Scanner scanner = new Scanner(System.in);
    Book newBook = new Book();
    LibraryRepository libraryRepository = new LibraryRepository();
    Connection conn = new DBManager().getConnection();

    public LibraryController() {

    }

    public void addBook() {
        try {
            Book newBook = this.collectBookInfo();
            libraryRepository.addBookToDB(newBook);
            System.out.println("Book has been added successfully!");
            System.out.println(" ");

        } catch (Exception e) {
            System.out.println("Error occurred while adding a book: " + e);
            System.out.println(e);
        }
    }


    private Book collectBookInfo() {
        System.out.println("Enter the name of the book:");
        newBook.setBookTitle(scanner.nextLine());
        newBook.setBookTitle(scanner.nextLine());
        System.out.println("Enter the author of the book:");
        newBook.setAuthor(scanner.nextLine());
        System.out.println("Enter the number of copies:");
        newBook.setNumberOfCopies(scanner.nextInt());

        return newBook;
    }

    public void displayAllBooks() {

        try {
            ArrayList<Book> books = libraryRepository.getAllBooksFromDB();
            books.forEach(System.out::println);
            System.out.println(" ");
        } catch (Exception e) {
            System.out.println("Error occurred while displaying list of book in the library " + e);
        }
    }

    public void updateBook() {

        try {
            System.out.println("Enter the ID of the book you want to update:");
            Integer bookID = scanner.nextInt();

            System.out.println("Enter 1 to update title of the book; enter 2 to update author; enter 3 to update number of copies:");
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                System.out.println("Enter updated title of the book:");
                libraryRepository.updateBookTitle(bookID, scanner.nextLine());
                libraryRepository.updateBookTitle(bookID, scanner.nextLine());
                System.out.println("Title of the book has been updated successfully!");
                System.out.println(" ");
            } else if (userInput == 2) {
                System.out.println("Enter updated author of the book:");
                libraryRepository.updateAuthor(bookID, scanner.nextLine());
                libraryRepository.updateAuthor(bookID, scanner.nextLine());
                System.out.println("Author of the book has been updated successfully!");
                System.out.println(" ");
            } else if (userInput == 3) {
                System.out.println("Enter updated number of copies:");
                libraryRepository.updateNumberOfCopies(bookID, scanner.nextInt());
                System.out.println("Available number of copies of the book has been updated successfully!");
                System.out.println(" ");
            } else {
                System.out.println("Please choose valid option!");
                System.out.println(" ");
            }

        } catch (Exception e) {
            System.out.println("Error occurred while updating book: " + e);
        }


    }

    public void deleteBook() {
        try {
            System.out.println("Enter the ID of the book you want to delete:");
            Integer bookID = scanner.nextInt();
            libraryRepository.deleteBookFromDB(bookID);
            System.out.println("Book has been deleted successfully!");
            System.out.println(" ");
        } catch (Exception e) {
            System.out.println("Error occurred while deleting a book: " + e);
        }


    }

    public void borrowBook() {

        try {
            System.out.println("Enter the ID of the book you want to borrow:");
            Integer bookID = scanner.nextInt();
            libraryRepository.borrowBookFromDB(bookID);
            System.out.println("Book has been borrowed successfully!");
            System.out.println(" ");

        } catch (Exception e) {
            System.out.println("Error occurred while borrowing a book: " + e);
        }
    }

    public void displayBorrowedBooks() {

        try {
            ArrayList<BorrowedBook> borrowedBooks = libraryRepository.getBorrowedBooksFromDB();
            borrowedBooks.forEach(System.out::println);
            System.out.println(" ");
        } catch (Exception e) {
            System.out.println("Error occurred while displaying list of borrowed books: " + e);
        }


    }

    public void returnBook() {
        try {
            System.out.println("Enter the ID of the borrowed book you want to return:");
            Integer borrowedBookID = scanner.nextInt();
            libraryRepository.returnBookInDB(borrowedBookID);
            System.out.println("Book has been returned successfully!");
            System.out.println(" ");
        } catch (Exception e) {
            System.out.println("Error occurred while returning the book: " + e);
        }

    }

    public void closeConnection() {
        try{
            if(conn != null) conn.close();
        }catch (Exception e) {
            System.out.println(e.getClass() + e.getMessage());
        }
    }

    }






