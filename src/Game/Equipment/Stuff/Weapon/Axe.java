package Game.Equipment.Stuff.Weapon;

public class Axe extends Weapon
{
    public Axe()
    {
        this("Axe", 20);
    }
    protected Axe(String name, int dmgPts)
    {
        super(name, dmgPts);
    }
}
