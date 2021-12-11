package Game.Units.Abilities;

import Game.Equipment.Stuff.Weapon.Axe;

/**
 *  Интерфейс способности владеть топором
 */
@FunctionalInterface
public interface AxeMaster
{
    Axe setAxe(Axe axe);
}
