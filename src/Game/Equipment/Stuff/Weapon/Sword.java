package Game.Equipment.Stuff.Weapon;

/**
 * Класс меча
 */
public class Sword extends Weapon
{
    public Sword()
    {
        this("Sword", 10);
    }
    private Sword(String name, int dmgPts)
    {
        super(name, dmgPts);
    }
}
