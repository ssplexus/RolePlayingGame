package Game.Equipment.Stuff.Weapon;

/**
 * Класс крепкого топора
 */
public class StrongAxe extends Weapon
{
    public StrongAxe()
    {
        this("Strong Axe", 30);
    }
    private StrongAxe(String name, int dmgPts)
    {
        super(name, dmgPts);
    }
}
