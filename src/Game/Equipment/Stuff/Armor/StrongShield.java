package Game.Equipment.Stuff.Armor;

public class StrongShield extends Armor
{
    public StrongShield()
    {
        this("Strong shield", 5);
    }
    private StrongShield(String name, int defPts)
    {
        super(name, defPts);
    }
}