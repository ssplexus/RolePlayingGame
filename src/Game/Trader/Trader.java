package Game.Trader;

import Game.Equipment.Equipment;
import Game.Equipment.Stuff.Stuff;
import Game.Menu;
import Game.Units.Heroes.Dwarf;
import Game.Units.Heroes.Elf;
import Game.Units.Heroes.Knight;
import Game.Units.Unit;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        System.out.println("Welcome to my trade shop!");
        System.out.println(traderGoods);
        while (0 != (option = Menu.getMenu(priceList.getCount() + 1, () -> priceList.toString() + "0 - for exit")))
        //while (0 != (option = Menu.getMenu(traderGoods.getStuff().size() + 1, () -> goodsWithPrices() + "0 - for exit")))
        {
            if(traderGoods.get(priceList.getGoods(option)) == null)
            {
                System.out.println("This product is out of stock!");
                continue;
            }
            if(priceList.getBuyPrice(option) > buyer.getGold())
            {
                System.out.println("Sorry. You don't have enough money.");
                continue;
            }

            boolean isDeal = false;

            if(buyer instanceof Knight)
            {
                if (Knight.isApplicable(priceList.getGoods(option)))
                {
                    traderGoods.put(((Knight) buyer).
                            buyEquipment(traderGoods.get(priceList.getGoods(option))));
                    isDeal = true;
                }
            }

            if(buyer instanceof Elf)
            {
                if(Elf.isApplicable(priceList.getGoods(option)))
                {
                    traderGoods.put(((Elf) buyer).
                            buyEquipment(traderGoods.get(priceList.getGoods(option))));
                    isDeal = true;
                }
            }

            if(buyer instanceof Dwarf)
            {
                if(Dwarf.isApplicable(priceList.getGoods(option)))
                {
                    traderGoods.put(((Dwarf) buyer).
                            buyEquipment(traderGoods.get(priceList.getGoods(option))));
                    isDeal = true;
                }
            }

            if(isDeal) buyer.setGold(buyer.getGold() - priceList.getBuyPrice(option));
            else System.out.println("Not applicable for you!");
        }
    }

//    @Override
//    public void run()
//    {
//        if(buyer == null || priceList == null || traderGoods == null) throw new IllegalArgumentException();
//        int option = 0;
//        System.out.println("Welcome to my trade shop!");
//        System.out.println(traderGoods);
//        while (0 != (option = Menu.getMenu(priceList.getCount() + 1, () -> priceList.toString() + "0 - for exit")))
//        //while (0 != (option = Menu.getMenu(traderGoods.getStuff().size() + 1, () -> goodsWithPrices() + "0 - for exit")))
//        {
//            if(traderGoods.get(priceList.getGoods(option)) == null)
//            {
//                System.out.println("This product is out of stock!");
//                continue;
//            }
//            if(priceList.getBuyPrice(option) > buyer.getGold())
//            {
//                System.out.println("Sorry. You don't have enough money.");
//                continue;
//            }
//
//            boolean isDeal = false;
//
//            if(buyer instanceof Knight)
//            {
//                if (Knight.isApplicable(priceList.getGoods(option)))
//                {
//                    traderGoods.put(((Knight) buyer).
//                            buyEquipment(traderGoods.get(priceList.getGoods(option))));
//                    isDeal = true;
//                }
//            }
//
//            if(buyer instanceof Elf)
//            {
//                if(Elf.isApplicable(priceList.getGoods(option)))
//                {
//                    traderGoods.put(((Elf) buyer).
//                            buyEquipment(traderGoods.get(priceList.getGoods(option))));
//                    isDeal = true;
//                }
//            }
//
//            if(buyer instanceof Dwarf)
//            {
//                if(Dwarf.isApplicable(priceList.getGoods(option)))
//                {
//                    traderGoods.put(((Dwarf) buyer).
//                            buyEquipment(traderGoods.get(priceList.getGoods(option))));
//                    isDeal = true;
//                }
//            }
//
//            if(isDeal) buyer.setGold(buyer.getGold() - priceList.getBuyPrice(option));
//                else System.out.println("Not applicable for you!");
//        }
//    }

    public Stuff getTraderGoods() {
        return traderGoods;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    private String goodsWithPrices()
    {
        StringBuilder res = new StringBuilder();

        res.append("Goods list:\n");

        Iterator<Equipment> itr = traderGoods.getStuff().keySet().iterator();

        int i = 0;
        while(itr.hasNext())
        {
            Equipment entry = itr.next();

            res.append((++i) +") ").
                    append(entry).
                    append(String.format("[sell - %d gold]\n", priceList.getPrice(entry, true))).
                    append(String.format("[buy - %d gold]\n", priceList.getPrice(entry,false)));
        }
        return res.toString();
    }
}
