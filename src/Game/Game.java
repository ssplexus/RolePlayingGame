package Game;

import java.util.Scanner;
import java.util.function.Supplier;

public class Game
{

    public void run()
    {

        while (getMainMenu() != 0)
        {

        }
    }

    private int getMainMenu()
    {
        return getMenu(3,()->"Enter command:\n" +
                "2 - 'Trader'\n"+
                "1 - 'Dark forest'\n" +
                "0 - 'Exit'\n");
    }

    private int getMenu(int numOptions, Supplier<String> menu)
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

    public void playerCustomize()
    {

    }
}
