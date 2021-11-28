package Game.Trader;

import Game.Equipment.Equipment;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PriceList
{
    private HashMap<Equipment, Integer> priseList;

    public PriceList()
    {
        priseList = new HashMap<>();
    }

    public void addToList(Equipment product, int price)
    {
        try
        {
            priseList.put(product, price);
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
            priseList.replace(product,price);
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
        priseList.forEach((equipment, integer) -> res.
                append(equipment.toString() + " - ").
                append(integer.intValue() + " gold\n"));
        return res.toString();
    }
}
