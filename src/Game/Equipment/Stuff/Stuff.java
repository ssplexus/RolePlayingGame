package Game.Equipment.Stuff;

import Game.Equipment.Equipment;

import java.util.*;

public class Stuff
{
    HashMap<Equipment, List<Equipment>> stuff;

    public Stuff()
    {
        this.stuff = new HashMap<>();
    }

    protected void put(Equipment equipment)
    {
        try
        {
            if(stuff.containsKey(equipment))
                stuff.put(equipment, new ArrayList<>());
            stuff.get(equipment).add(equipment);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    protected Equipment get(Equipment equipment)
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
            e.printStackTrace();
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
                                : "0")
                ));
        return stringBuilder.toString();
    }
}
