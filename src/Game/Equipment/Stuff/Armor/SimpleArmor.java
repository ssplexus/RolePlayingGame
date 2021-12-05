package Game.Equipment.Stuff.Armor;

public class SimpleArmor extends Armor
{
    public SimpleArmor()
    {
        this("Simple Armor", 5);
    }
    protected SimpleArmor(String name, int defPts)
    {
        super(name, defPts);
    }
}
