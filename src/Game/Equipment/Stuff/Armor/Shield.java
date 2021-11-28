package Game.Equipment.Stuff.Armor;

public class Shield extends Armor
{
    public Shield()
    {
        this("Shield", 2);
    }
    protected Shield(String name, int defPts)
    {
        super(name, defPts);
    }
}
