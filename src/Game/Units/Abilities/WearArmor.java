package Game.Units.Abilities;

import Game.Equipment.Stuff.Armor.Armor;

/**
 *  Интерфейс способности носить броню
 */
@FunctionalInterface
public interface WearArmor
{
    Armor setArmor(Armor armor);
}
