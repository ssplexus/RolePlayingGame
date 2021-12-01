package Game.Equipment.Stuff.Weapon;

public class StrongSword extends Weapon
{
    public StrongSword()
    {
        this("Strong Sword", 17);
    }
    protected StrongSword(String name, int dmgPts)
    {
        super(name, dmgPts);
    }
}
