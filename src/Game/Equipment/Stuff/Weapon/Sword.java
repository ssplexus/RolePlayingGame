package Game.Equipment.Stuff.Weapon;

public class Sword extends Weapon
{
    public Sword()
    {
        this("Sword", 10);
    }
    protected Sword(String name, int dmgPts)
    {
        super(name, dmgPts);
    }
}
