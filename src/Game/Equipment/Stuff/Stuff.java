package Game.Equipment.Stuff;

import Game.Equipment.Equipment;

import java.util.*;

public class Stuff
{
    private HashMap<Equipment, List<Equipment>> stuff;

    public Stuff()
    {
        this.stuff = new HashMap<>();
    }

    public void put(Equipment equipment)
    {
        if(equipment == null) return;
        try
        {
            if(!stuff.containsKey(equipment))
                stuff.put(equipment, new ArrayList<>());
            stuff.get(equipment).add(equipment);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    public Equipment get(Equipment equipment)
    {
        try
        {
            if(stuff.containsKey(equipment))
            {
                if(Optional.ofNullable(stuff.get(equipment)).isPresent())
                    if(stuff.get(equipment).size() > 0)
                        return stuff.get(equipment).remove(stuff.get(equipment).size() - 1);
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Empty equipment element!");
        }

        return null;
    }

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

    public HashMap<Equipment, List<Equipment>> getStuff()
    {
        return stuff;
    }
}
