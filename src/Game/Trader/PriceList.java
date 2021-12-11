package Game.Trader;

import Game.Equipment.Equipment;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Класс списка цен
 */
public class PriceList
{
    private LinkedHashMap<Equipment, Integer> priceList; // список пар товар-цена

    /**
     * Конструктор списка цен
     */
    public PriceList()
    {
        priceList = new LinkedHashMap<>();
    }

    /** Метод добавления в список
     *
     * @param product - товар
     * @param price - цена
     */
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

    /** Метод формирования строкового представления объекта списка цен
     *
     * @return строковое представление объекта списка цен
     */
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
            append(" [sell - " + price + " gold]"). // цена продажи торговцу
            append("[buy - ").append((int)(price - price * 0.1)).append(" gold]\n"); // цена покупки у торговца
        }
        return res.toString();
    }

    /** Метод получения цены на товар
     *
     * @param equipment - товар
     * @param sell - признак цена продажи или цена покупки
     * @return - цена нат товар
     */
    public int getPrice(Equipment equipment, boolean sell)
    {
        if(equipment == null) return 0;
        int price = Optional.ofNullable(priceList.get(equipment)).isPresent() ? priceList.get(equipment): 0;
        if (sell) price -= (int)(price * 0.1); // цена покупки на 10% меньше цены продажи
        return price;
    }
}
