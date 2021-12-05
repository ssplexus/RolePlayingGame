package Game.Units.Abilities;

import Game.Equipment.Equipment;

@FunctionalInterface
public interface Buyer
{
    Equipment buy(Equipment equipment);
}
