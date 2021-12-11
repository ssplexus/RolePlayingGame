package Game.Trader;

import Game.Equipment.Equipment;
import Game.Equipment.Stuff.Medicine.Medicine;
import Game.Equipment.Stuff.Stuff;
import Game.Menu;
import Game.Units.Abilities.Buyer;
import Game.Units.Unit;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс торговца, имплементирует интерфейс для использования в качестве аргумента потока
 */
public class Trader implements Runnable
{
    private Stuff traderGoods; // хранилище товаров
    private PriceList priceList; // цены на товары
    private Unit buyer; // персонаж-покупатель

    /**
     * Конструктор
     * @param traderGoods - товары
     * @param priceList - цены
     * @param bayer - покупатель
     */
    public Trader(Stuff traderGoods, PriceList priceList, Unit bayer)
    {
        this.traderGoods = traderGoods;
        this.priceList = priceList;
        this.buyer = bayer;
    }

    /**
     * Назначение покупателя
     * @param buyer - персонаж-покупатель
     */
    public void setBuyer(Unit buyer)
    {
        this.buyer = buyer;
    }

    /**
     * Метод запуска торговли
     */
    @Override
    public void run()
    {
        // Если один из аргументов Null, то выбрасываем исключение
        if(buyer == null || priceList == null || traderGoods == null) throw new IllegalArgumentException();

        int option = 0; // для хранения опций
        System.out.println("Welcome to my trade shop!\n");
        System.out.printf("You have %d gold\n", buyer.getGold());
        System.out.printf("You have %d/%d health pts.\n",buyer.getHp(), buyer.getMaxHp());

        buyer.printEquipment(); // вывод текущего снаряжения игрока
        System.out.println();

        // число опций по кол-ву уникальных позиций + 1 для выхода
        while (0 != (option = Menu.getMenu(traderGoods.getStuff().size() + 1,
                () -> goodsWithPrices() + "0 - for exit"))) // меню выбора товаров
        {
            Equipment selectedEquip = traderGoods.getById(option, false); // выбрать товар по номеру в списке
            if(selectedEquip == null)
            {
                System.out.println("This product is out of stock!");
                continue;
            }
            if(priceList.getPrice(selectedEquip, false) > buyer.getGold()) // если недостаточно денег, то к меню
            {
                System.out.println("Sorry. You don't have enough money.");
                continue;
            }

            if(selectedEquip instanceof Medicine) // если покупаем медикамент
            {
                if (((Buyer)buyer).buy(traderGoods.get(selectedEquip)) != null) //если покупка удалась
                    // Уменьшаем кол-во золота игрока
                    buyer.setGold(buyer.getGold() - priceList.getPrice(selectedEquip, false));
                else
                    System.out.println("Not applicable for you!\n");
            }
            else
            {
                // Покупатель покупает новое снаряжения и продаёт старое
                Equipment oldEquip = traderGoods.put(((Buyer)buyer).buy(traderGoods.get(selectedEquip)));
                if(oldEquip != null) // если продажа удалась
                {
                    // Уменьшаем кол-во золота игрока на цену товара
                    buyer.setGold(buyer.getGold() - priceList.getPrice(selectedEquip, false));
                    // Увеличиваем кол-во золота игрока на цену продажи старого снаряжения
                    buyer.setGold(buyer.getGold() + priceList.getPrice(oldEquip, true));

                    System.out.printf("You bought %s [-%d gold]\n",selectedEquip.getName(),
                            priceList.getPrice(selectedEquip, false));
                    System.out.printf("You sold your old %s [+%d gold]\n",
                            oldEquip.getName(), priceList.getPrice(oldEquip, true));
                }
                else
                    System.out.println("Not applicable for you!\n");
            }

            System.out.printf("You have %d gold\n", buyer.getGold());
            System.out.printf("You have %d/%d health pts.\n",buyer.getHp(), buyer.getMaxHp());
            buyer.printEquipment();
            System.out.println();
        }
    }

    // Метод вывода товаров с их характеристиками и ценами
    private String goodsWithPrices()
    {
        StringBuilder res = new StringBuilder();

        res.append("Trader's goods list:\n");

        Iterator<Map.Entry<Equipment, List<Equipment>>> itr = traderGoods.getStuff().entrySet().iterator();

        int i = 0;
        //Формирование списка товаров с ценами продажи и покупки
        while(itr.hasNext())
        {
            Map.Entry<Equipment, List<Equipment>> entry = itr.next();
            Equipment key = entry.getKey();
            List<Equipment> list = entry.getValue();

            res.append((++i) +") ").
                    append(key).
                    append(String.format("[sell - %d gold]", priceList.getPrice(key,true))).
                    append(String.format("[buy - %d gold]", priceList.getPrice(key,false))).
                    append(" x " + (Optional.ofNullable(list.size()).isPresent() ? list.size() : 0)).
                    append("\n");
        }
        return res.toString();
    }
}
