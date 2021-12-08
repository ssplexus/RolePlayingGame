package Game.Equipment.Stuff.Weapon;

public class StrongAxe extends Axe
{
    public StrongAxe()
    {
        this("Strong Axe", 30);
    }
    protected StrongAxe(String name, int dmgPts)
    {
        super(name, dmgPts);
    }
}
