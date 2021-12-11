package Game.Equipment.Stuff.Weapon;

import Game.Equipment.Equipment;

/**
 * Базовый абстрактный класс оружия
 */
public abstract class Weapon extends Equipment
{
    protected Weapon(String name, int dmgPts)
    {
        this(name, "Weapon", "Damage(pts.)", dmgPts);
    }

    private Weapon(String name, String eqType, String propertyName, int propertyPoints)
    {
        super(name, eqType, propertyName, propertyPoints);
    }
}
