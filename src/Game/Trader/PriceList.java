package Game.Trader;

import Game.Equipment.Equipment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PriceList
{
    private LinkedHashMap<Equipment, Integer> priceList;

    public PriceList()
    {
        priceList = new LinkedHashMap<>();
    }

    public void addToList(Equipment product, int price)
    {
        try
        {
            priceList.put(product, price);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }
    public void setPrice(Equipment product, int price)
    {
        try
        {
            priceList.replace(product,price);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        StringBuilder res = new StringBuilder();
        res.append("Price list:\n");

        Iterator<Map.Entry<Equipment, Integer>> itr = priceList.entrySet().iterator();
        int i = 0;
        while(itr.hasNext())
        {
            Map.Entry<Equipment, Integer> entry = itr.next();
            Equipment equipment = entry.getKey();
            int price = entry.getValue();
            res.append((++i) +") ").
            append(equipment).
            append(" [sell - " + price + " gold]").
            append("[buy - ").append((int)(price - price * 0.1)).append(" gold]\n");
        }
        return res.toString();
    }

    public int getSellPrice(int idx)
    {
        if(idx < 0 || idx > priceList.size()) return 0;
        return (int)(priceList.values().toArray()[idx]);
    }

    public int getPrice(Equipment equipment, boolean sell)
    {
        Integer price = priceList.get(equipment);
        if (sell) price -= (int)(price * 0.1);
        return price == null ? 0 : price;
    }

    public int getBuyPrice(int idx)
    {
        if(idx < 0 || idx > priceList.size()) return 0;
        int price = (int)(priceList.values().toArray()[idx]);
        return (int)(price - price * 0.1);
    }

    public Equipment getGoods(int idx)
    {
        if(idx < 0 || idx > priceList.size()) return null;
        return (Equipment) (priceList.keySet().toArray()[idx]);
    }

    public int getCount()
    {
        return priceList.size();
    }
}
