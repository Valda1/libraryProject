import controller.UserMenu;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to our library service!");
        System.out.println("______________________");

        UserMenu userMenu = new UserMenu();
        userMenu.handleUserChoice();


    }
}
