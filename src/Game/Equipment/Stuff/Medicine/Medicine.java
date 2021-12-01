package Game.Equipment.Stuff.Medicine;

import Game.Equipment.Equipment;

public abstract class Medicine extends Equipment
{
    public Medicine(String name, int healPts)
    {
        this(name, "Medicine", "Healing (pts.)", healPts);
    }
    private Medicine(String name, String eqType, String propertyName, int propertyPoints)
    {
        super(name, eqType, propertyName, propertyPoints);
    }
}
