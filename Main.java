package budget;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        ItemList itemList = new ItemList();
        UserInterface UI = new UserInterface(itemList);

        UI.start();
    }
}
