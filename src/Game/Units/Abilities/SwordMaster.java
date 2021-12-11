package Game.Units.Abilities;

import Game.Equipment.Stuff.Weapon.Sword;

/**
 *  Интерфейс способности владеть мечём
 */
@FunctionalInterface
public interface SwordMaster
{
    Sword setSword(Sword sword);
}
