package Game.Equipment;

public class Medicine extends Equipment
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
