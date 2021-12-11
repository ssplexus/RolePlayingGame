package Game.Units.Abilities;

import Game.Equipment.Stuff.Armor.Shield;

/**
 *  Интерфейс способности владеть щитом
 */
@FunctionalInterface
public interface WieldShield
{
    Shield setShield(Shield shield);
}
