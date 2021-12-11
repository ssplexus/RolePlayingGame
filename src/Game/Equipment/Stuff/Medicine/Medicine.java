package Game.Equipment.Stuff.Medicine;

import Game.Equipment.Equipment;

/**
 * Базовый абстрактный класс медикамента
 */
public abstract class Medicine extends Equipment
{
    protected Medicine(String name, int healPts)
    {
        this(name, "Medicine", "Healing (pts.)", healPts);
    }
    private Medicine(String name, String eqType, String propertyName, int propertyPoints)
    {
        super(name, eqType, propertyName, propertyPoints);
    }
}
