package Game.Units.Abilities;

import Game.Equipment.Equipment;

/**
 *  Интерфейс способности покупать товары
 */
@FunctionalInterface
public interface Buyer
{
    Equipment buy(Equipment equipment);
}
