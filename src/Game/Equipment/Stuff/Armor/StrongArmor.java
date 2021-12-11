package Game.Equipment.Stuff.Armor;

/**
 * Класс крепкой брони
 */
public class StrongArmor extends Armor
{
    public StrongArmor()
    {
        this("Strong Armor", 10);
    }
    private StrongArmor(String name, int defPts)
    {
        super(name, defPts);
    }
}
