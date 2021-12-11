package Game.Equipment.Stuff;

import Game.Equipment.Equipment;

import java.util.*;

/**
 * Класс склада товаров торговца
 */
public class Stuff
{
    private HashMap<Equipment, List<Equipment>> stuff; // товары

    /**
     * Конструктор склада
     */
    public Stuff()
    {
        this.stuff = new HashMap<>();
    }

    /** Поместить товар на склад
     *
     * @param equipment - товар
     * @return - товар который поместили на склад (или Null в случае неудачи)
     */
    public Equipment put(Equipment equipment)
    {
        if(equipment == null) return null;
        try
        {
            if(!stuff.containsKey(equipment))
                stuff.put(equipment, new ArrayList<>()); // если такого товара нет, то создаём отдел для него
            // Каждый товар добавляется в свой отдел
            return stuff.get(equipment).add(equipment)? equipment: null;
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /** Достать товар со склада по образцу товара
     *
     * @param equipment - образец товара
     * @return - запрашиваемый товар со склада
     */
    public Equipment get(Equipment equipment)
    {
        try
        {
            if(stuff.containsKey(equipment))
            {
                if(Optional.ofNullable(stuff.get(equipment)).isPresent()) // если отдел с таким товаром есть
                    if(stuff.get(equipment).size() > 0) // проверяем есть ли в этом отделе товар
                        return stuff.get(equipment).remove(stuff.get(equipment).size() - 1); //взять товар
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Empty equipment element!");
        }

        return null;
    }

    /** Получить товар по индексу
     *
     * @param idx - индекс
     * @param buy - купить или только посмотреть
     * @return - товар
     */
    public Equipment getById(int idx, boolean buy)
    {
        Iterator<Equipment> itr = stuff.keySet().iterator();
        int i = 0;
        while(itr.hasNext())
        {
            Equipment equipment = itr.next();
            if(idx == ++i)
                 if(Optional.ofNullable(stuff.get(equipment)).isPresent())
                            if(stuff.get(equipment).size() > 0)
                                return buy? stuff.get(equipment).
                                        remove(stuff.get(equipment).size() - 1):
                                        equipment;
        }
        return null;
    }

    /** Строковое представление объекта класса склада товаров
     *
     * @return - строковое представление
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stuff.forEach((equipment, list) -> stringBuilder.append(equipment).
                append(" x " +
                        (Optional.ofNullable(list.size()).isPresent() ? list.size()
                                : "0\n" + "\n")
                ));

        return stringBuilder.toString();
    }

    /** Получить объект товаров
     *
     * @return - товары
     */
    public HashMap<Equipment, List<Equipment>> getStuff()
    {
        return stuff;
    }
}
