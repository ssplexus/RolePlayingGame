package Game.Equipment.Stuff.Armor;

public class SimpleArmor extends Armor
{
    public SimpleArmor()
    {
        this("Simple armor", 5);
    }
    protected SimpleArmor(String name, int defPts)
    {
        super(name, defPts);
    }
}
