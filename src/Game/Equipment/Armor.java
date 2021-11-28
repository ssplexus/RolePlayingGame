package Game.Equipment;

public class Armor extends Equipment
{
    public Armor(String name, int defPts)
    {
        this(name, "Armor", "Defense(pts.)", defPts);
    }
    private Armor(String name, String eqType, String propertyName, int propertyPoints)
    {
        super(name, eqType, propertyName, propertyPoints);
    }
}
