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

public class Trader implements Runnable
{
    private Stuff traderGoods;
    private PriceList priceList;
    private Unit buyer;

    public Trader(Stuff traderGoods, PriceList priceList, Unit bayer)
    {
        this.traderGoods = traderGoods;
        this.priceList = priceList;
        this.buyer = bayer;
    }

    public void setBuyer(Unit buyer)
    {
        this.buyer = buyer;
    }

    @Override
    public void run()
    {
        if(buyer == null || priceList == null || traderGoods == null) throw new IllegalArgumentException();
        int option = 0;
        System.out.println("Welcome to my trade shop!\n");
        System.out.printf("You have %d gold\n", buyer.getGold());
        System.out.printf("You have %d/%d health pts.\n",buyer.getHp(), buyer.getMaxHp());
        buyer.printEquipment();
        System.out.println();
        while (0 != (option = Menu.getMenu(traderGoods.getStuff().size() + 1, () -> goodsWithPrices() + "0 - for exit")))
        {
            Equipment selectedEquip = traderGoods.getById(option, false);
            if(selectedEquip == null)
            {
                System.out.println("This product is out of stock!");
                continue;
            }
            if(priceList.getPrice(selectedEquip, false) > buyer.getGold())
            {
                System.out.println("Sorry. You don't have enough money.");
                continue;
            }

            if(selectedEquip instanceof Medicine)
            {
                if (((Buyer)buyer).buy(traderGoods.get(selectedEquip)) != null)
                    buyer.setGold(buyer.getGold() - priceList.getPrice(selectedEquip, true));
                else
                    System.out.println("Not applicable for you!\n");
            }
            else
            {
                if(traderGoods.put(((Buyer)buyer).buy(traderGoods.get(selectedEquip))))
                    buyer.setGold(buyer.getGold() - priceList.getPrice(selectedEquip, true));
                else
                    System.out.println("Not applicable for you!\n");
            }

            System.out.printf("You have %d gold\n", buyer.getGold());
            System.out.printf("You have %d/%d health pts.\n",buyer.getHp(), buyer.getMaxHp());
            buyer.printEquipment();
            System.out.println();

        }
    }

    public Stuff getTraderGoods() {
        return traderGoods;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    private String goodsWithPrices()
    {
        StringBuilder res = new StringBuilder();

        res.append("Trader's goods list:\n");

        Iterator<Map.Entry<Equipment, List<Equipment>>> itr = traderGoods.getStuff().entrySet().iterator();

        int i = 0;
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
