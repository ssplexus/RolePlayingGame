package Game.BattleGround;

import Game.Menu;
import Game.Player.Player;
import Game.Units.Monsters.Goblin;
import Game.Units.Monsters.Skeleton;
import Game.Units.Unit;

import java.util.Random;

/**
 *  Класс поля битвы
 */
public class BattleGround implements Runnable
{
    private Player player; // игрок

    /** Конструктор поля битвы
     *
     * @param player - игрок
     */
    public BattleGround(Player player)
    {
        this.player = player;
    }

    /**
     * Запуск битвы
     */
    @Override
    public void run()
    {
        System.out.println("You entered a dark forest!");
        Unit enemy = null;
        if(new Random().nextInt(101) > 30)
            enemy = new Skeleton(); // с вероятностью 70% встретить скелета
        else
            enemy = new Goblin(); // 30% встретить гоблина
        System.out.printf("You were attacked by a %s!\n", enemy.getName());

        System.out.println(player.getCharacter()); // вывод характеристик игрока
        System.out.println(enemy); // вывод характеристик врага

        boolean isEnemyTurn = false; // признак хода противника
        while (!player.getCharacter().isDestroyed() && !enemy.isDestroyed()) // цикл пока жив игрок и противник
        {
            player.getCharacter().printHP(); // вывод здоровья игрока
            enemy.printHP(); // вывод здоровья врага
            System.out.println();
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            if(isEnemyTurn) // ход противника
                enemy.attack(player.getCharacter(), false);
            else
            {
                int option = getBattleMenu(); // выбор опции меню
                if (option == 2) // попытаться контратаковать врага
                {
                    System.out.println("You trying to counter enemy...");
                    player.getCharacter().tryToCounter(true);
                }
                if (option == 1) player.getCharacter().attack(enemy, false); // атаковать врага
                if (option == 0) // попытаться убежать
                {
                    System.out.println("You trying to escape...");
                    if(new Random().nextInt(101) < 50) // шанс сбежать 50/50
                    {
                        player.getCharacter().setDestroyed();
                        System.out.println("Fail!");
                    }
                    else
                    {
                        System.out.println("Success!");
                        break;
                    }
                }
            }
            isEnemyTurn = isEnemyTurn? false: true; // переход хода
        }

        if(player.getCharacter().isDestroyed()) // если персонаж игрока мёртв, то игра окончена
            System.out.println("You are dead!");
        else
            System.out.println("Enemy is dead");
    }

    public int getBattleMenu()
    {
        return Menu.getMenu(3,()->"Enter command:\n" +
                "1 - Attack\n"+
                "2 - Try to counter\n" +
                "0 - Try to escape\n");
    }
}
