package Game.Equipment.Stuff.Armor;

/**
 * Класс простой брони
 */
public class SimpleArmor extends Armor
{
    public SimpleArmor()
    {
        this("Simple Armor", 5);
    }
    private SimpleArmor(String name, int defPts)
    {
        super(name, defPts);
    }
}
