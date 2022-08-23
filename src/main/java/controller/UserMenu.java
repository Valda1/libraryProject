package controller;

import java.util.Scanner;

public class UserMenu {

    Scanner scanner = new Scanner(System.in);
    LibraryController libraryController = new LibraryController();

    public void start(){
        this.handleUserChoice();
    }

    public void handleUserChoice() {
        showOptions();
        System.out.println(" ");

        String userChoice = scanner.nextLine();

        switch (userChoice){
            case "1" :
                libraryController.addBook();
                break;
            case "2" :
                libraryController.displayAllBooks();
                break;
            case "3" :
                libraryController.updateBook();
                break;
            case "4" :
                libraryController.deleteBook();
                break;
            case "5" :
                libraryController.borrowBook();
                break;
            case "6" :
                libraryController.displayBorrowedBooks();
                break;
            case "7" :
                libraryController.returnBook();
                break;
            case "8" :
                libraryController.closeConnection();
                System.exit(0);
                break;
            default:
                System.out.println("Please choose a valid option!");
                System.out.println(" ");
        }

        start();
    }

    private void showOptions() {
        System.out.println(
                "Choose an option:" +
                        "\n 1. Add book to the library" +
                        "\n 2. View the list of available books" +
                        "\n 3. Update a book info" +
                        "\n 4. Remove a book from the library" +
                        "\n 5. Borrow a book from the library" +
                        "\n 6. See list of borrowed books" +
                        "\n 7. Return a book to the library" +
                        "\n 8. Exit the application"
        );
    }
}
