package Game;

import Game.BattleGround.BattleGround;
import Game.Equipment.Stuff.Medicine.Medicine;
import Game.Equipment.Stuff.Armor.*;
import Game.Equipment.Stuff.Medicine.SmallMedicine;
import Game.Equipment.Stuff.Stuff;
import Game.Equipment.Stuff.Weapon.Axe;
import Game.Equipment.Stuff.Weapon.StrongAxe;
import Game.Equipment.Stuff.Weapon.StrongSword;
import Game.Equipment.Stuff.Weapon.Sword;
import Game.Player.Player;
import Game.Trader.PriceList;
import Game.Trader.Trader;
import Game.Units.Heroes.Dwarf;
import Game.Units.Heroes.Elf;
import Game.Units.Heroes.Knight;
import Game.Units.Unit;

import java.util.Scanner;

public class Game
{

    Player player;
    Trader trader;

    public Game()
    {
        player = null;
        trader = null;
    }

    private Player newPlayer()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println();

        Unit hero = null;

        while( true)
        {
            switch (Menu.getMenu(3, ()->String.format("Choose hero:\n1 - %s\n2 - %s\n3 - %s\n",
                    Knight.getClassDefaultCharacteristics(),
                    Elf.getClassDefaultCharacteristics(),
                    Dwarf.getClassDefaultCharacteristics())))
            {
                case 1: hero = new Knight(name, new Sword(), new Shield(), new SimpleArmor());
                    break;
                case 2: hero = new Elf(name,new Sword(),new SimpleArmor());
                    break;
                case 3: hero = new Dwarf(name, new Axe(), new SimpleArmor());
                    break;
                default:
                    continue;
            }
            break;
        }
        return new Player(hero);
    }

    private Trader createTrader()
    {
        Stuff traderGoods = new Stuff();
        traderGoods.put(new StrongSword());
        traderGoods.put(new StrongSword());
        traderGoods.put(new StrongAxe());
        traderGoods.put(new StrongShield());
        traderGoods.put(new StrongArmor());
        traderGoods.put(new SmallMedicine());
        traderGoods.put(new SmallMedicine());
        traderGoods.put(new SmallMedicine());
        traderGoods.put(new SmallMedicine());

        PriceList priceList = new PriceList();
        priceList.addToList(new Sword(), 30);
        priceList.addToList(new Axe(), 60);
        priceList.addToList(new Shield(), 50);
        priceList.addToList(new SimpleArmor(), 70);
        priceList.addToList(new StrongSword(), 60);
        priceList.addToList(new StrongAxe(), 120);
        priceList.addToList(new StrongShield(), 100);
        priceList.addToList(new StrongArmor(), 140);
        priceList.addToList(new SmallMedicine(), 20);

        return new Trader(traderGoods, priceList, null);
    }

    public void run()
    {
        int option = 0;

        player = newPlayer();
        player.getCharacter().setGold(200);
        trader = createTrader();
        trader.setBuyer(player.getCharacter());

        while (0 != (option = getMainMenu()))
        {
            if(option == 2)
            {
                Thread traderThread = new Thread(trader);
                traderThread.start();
                try
                {
                    traderThread.join();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            if(option == 1)
            {
                Thread battleThread = new Thread(new BattleGround(player));
                battleThread.start();
                try
                {
                    battleThread.join();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if(player.getCharacter().isDestroyed())
                {
                    System.out.println("GAME OVER");
                    break;
                }
            }
        }
    }

    private int getMainMenu()
    {
        return Menu.getMenu(3,()->"Enter command:\n" +
                "2 - Trader\n"+
                "1 - Dark forest\n" +
                "0 - Exit\n");
    }

}
