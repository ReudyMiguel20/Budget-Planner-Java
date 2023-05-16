package budget;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        ItemList itemList = new ItemList();
        UserInterface UI = new UserInterface(itemList);

        UI.start();
    }
}
