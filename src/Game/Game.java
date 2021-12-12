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

/**
 * Класс игры
 */
public class Game
{
    private Player player;  // игрок
    private Trader trader;  // торговец

    /**
     * Конструктор
     */
    public Game()
    {
        player = null;
        trader = null;
    }

    // Метод создания игрока
    private Player newPlayer()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println();

        Unit hero = null; // указатель на выбранного героя

        while (true)
        {
            // Выбор героя
            switch (Menu.getMenu(3, ()->String.format("Choose hero:\n1 - %s\n2 - %s\n3 - %s\n",
                    Knight.getClassDefaultCharacteristics(), // характеристики рыцаря
                    Elf.getClassDefaultCharacteristics(), // характеристики эльфа
                    Dwarf.getClassDefaultCharacteristics()))) // характеристики гнома
            {
                case 1: hero = new Knight(name, new Sword(), new Shield(), new SimpleArmor()); // рыцарь
                    break;
                case 2: hero = new Elf(name,new Sword(),new SimpleArmor()); // эльф
                    break;
                case 3: hero = new Dwarf(name, new Axe(), new SimpleArmor()); // гном
                    break;
                default:
                    continue;
            }
            break;
        }
        return new Player(hero); // возврат нового объекта игрока
    }

    // Метод создания торговца
    private Trader createTrader()
    {
        Stuff traderGoods = new Stuff(); // создаём хранилище товаров
        // Добавление товаров
        traderGoods.put(new StrongSword());
        traderGoods.put(new StrongSword());
        traderGoods.put(new StrongAxe());
        traderGoods.put(new StrongShield());
        traderGoods.put(new StrongArmor());
        traderGoods.put(new SmallMedicine());
        traderGoods.put(new SmallMedicine());
        traderGoods.put(new SmallMedicine());
        traderGoods.put(new SmallMedicine());

        PriceList priceList = new PriceList(); // создание списка цен
        // Добавление цен на товары
        priceList.addToList(new Sword(), 30);
        priceList.addToList(new Axe(), 60);
        priceList.addToList(new Shield(), 50);
        priceList.addToList(new SimpleArmor(), 70);
        priceList.addToList(new StrongSword(), 60);
        priceList.addToList(new StrongAxe(), 120);
        priceList.addToList(new StrongShield(), 100);
        priceList.addToList(new StrongArmor(), 140);
        priceList.addToList(new SmallMedicine(), 15);

        return new Trader(traderGoods, priceList, null); // возврат нового объекта торговца
    }

    /**
     *  Метод запуска игры
     */
    public void run()
    {
        int option = 0; // для хранения опции меню

        player = newPlayer(); // создание игрока
        trader = createTrader(); // создание торговца
        trader.setBuyer(player.getCharacter()); // назначаем в качестве покупателя персонажа игрока

        while (0 != (option = getMainMenu())) // цикл пока в меню не выбран 0
        {
            if(option == 2) // вход в торговую лавку
            {
                Thread traderThread = new Thread(trader, "'Trader Thread'");
                traderThread.setUncaughtExceptionHandler(threadExceptionHandler());
                traderThread.start(); // запуск потока торговца
                try
                {
                    traderThread.join(); // ожидание окончания торговли
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            if(option == 1) // вход в тёмный лес
            {
                Thread battleThread = new Thread(new BattleGround(player), "'BattleGround Thread'"); // создание арены, помещаем туда игрока
                battleThread.setUncaughtExceptionHandler(threadExceptionHandler());
                battleThread.start(); // битва началась
                try
                {
                    battleThread.join(); // ожидание окончания битвы
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if(player.getCharacter().isDestroyed()) // если выбранный персонаж убит, то игра окончена
                {
                    System.out.println("GAME OVER");
                    break;
                }
            }
        }
    }

    // Метод главного меню
    private int getMainMenu()
    {
        return Menu.getMenu(3,()->"Enter command:\n" +
                "2 - Trader\n"+
                "1 - Dark forest\n" +
                "0 - Exit\n");
    }

    // Обработчик исключений в потоках
    private Thread.UncaughtExceptionHandler threadExceptionHandler()
    {
        return (t, e) -> System.out.println(e + " in " +  t.getName());
    }

}
