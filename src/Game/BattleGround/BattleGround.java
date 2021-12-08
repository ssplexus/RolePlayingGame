package Game.BattleGround;

import Game.Menu;
import Game.Player.Player;
import Game.Units.Monsters.Goblin;
import Game.Units.Monsters.Skeleton;
import Game.Units.Unit;

import java.util.Random;

public class BattleGround implements Runnable
{
    private Player player;

    public BattleGround(Player player)
    {
        this.player = player;
    }

    @Override
    public void run()
    {
        System.out.println("You entered a dark forest!");
        Unit enemy = null;
        if(new Random().nextInt(101) > 70)
            enemy = new Skeleton();
        else
            enemy = new Goblin();
        System.out.printf("You were attacked by a %s!\n", enemy.getName());

        System.out.println(player.getCharacter());
        System.out.println(enemy);

        boolean isEnemyTurn = false;
        while (!player.getCharacter().isDestroyed() && !enemy.isDestroyed())
        {
            player.getCharacter().printHP();
            enemy.printHP();
            System.out.println();
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            if(isEnemyTurn)
                enemy.attack(player.getCharacter(), false);
            else
            {
                int option = getBattleMenu();
                if (option == 2)
                {
                    System.out.println("You trying to counter enemy...");
                    player.getCharacter().tryToCounter(true);
                }
                if (option == 1) player.getCharacter().attack(enemy, false);
                if (option == 0)
                {
                    System.out.println("You trying to escape...");
                    if(new Random().nextInt(101) < 50)
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
            isEnemyTurn = isEnemyTurn? false: true;
        }

        if(player.getCharacter().isDestroyed())
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
