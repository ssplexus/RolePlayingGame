package Game.Equipment.Stuff.Armor;

import Game.Equipment.Equipment;

/**
 *  Базовый абстрактный класс снаряжения для защиты
 */
public abstract class Armor extends Equipment
{
    protected Armor(String name, int defPts)
    {
        this(name, "Armor", "Defense(pts.)", defPts);
    }
    private Armor(String name, String eqType, String propertyName, int propertyPoints)
    {
        super(name, eqType, propertyName, propertyPoints);
    }
}
