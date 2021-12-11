package Game.Equipment.Stuff.Weapon;

/**
 * Класс топора
 */
public class Axe extends Weapon
{
    public Axe()
    {
        this("Axe", 20);
    }
    private Axe(String name, int dmgPts)
    {
        super(name, dmgPts);
    }
}
