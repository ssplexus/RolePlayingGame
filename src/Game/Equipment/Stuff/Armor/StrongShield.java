package Game.Equipment.Stuff.Armor;

/**
 * Класс крепкого щита
 */
public class StrongShield extends Armor
{
    public StrongShield()
    {
        this("Strong Shield", 5);
    }
    private StrongShield(String name, int defPts)
    {
        super(name, defPts);
    }
}
