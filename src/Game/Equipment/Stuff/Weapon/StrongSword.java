package Game.Equipment.Stuff.Weapon;

/**
 * Класс крепкого меча
 */
public class StrongSword extends Weapon
{
    public StrongSword()
    {
        this("Strong Sword", 17);
    }
    private StrongSword(String name, int dmgPts)
    {
        super(name, dmgPts);
    }
}
