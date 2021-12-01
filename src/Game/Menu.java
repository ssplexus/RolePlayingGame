package Game;

import java.util.Scanner;
import java.util.function.Supplier;

public class Menu
{
    public static int getMenu(int numOptions, Supplier<String> menu)
    {
        int command;

        do
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println(menu.get());

            // Проверка корректности ввода команды

            while (!scanner.hasNextInt())
            {
                System.out.println("It's not an integer!");
                System.out.println("Enter command:");
                scanner.next();
            }
            command = scanner.nextInt();
            if(command <= numOptions && command >= 0) break;
            System.out.println("Incorrect!");
        }while (true);

        if(command == 0) return command;
        return command;
    }
}
