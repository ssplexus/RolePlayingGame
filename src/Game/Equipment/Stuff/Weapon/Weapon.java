package Game.Equipment.Stuff.Weapon;

import Game.Equipment.Equipment;

public class Weapon extends Equipment
{
    public Weapon(String name, int dmgPts)
    {
        this(name, "Weapon", "Damage(pts.)", dmgPts);
    }

    private Weapon(String name, String eqType, String propertyName, int propertyPoints)
    {
        super(name, eqType, propertyName, propertyPoints);
    }
}
