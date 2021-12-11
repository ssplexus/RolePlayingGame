package Game.Equipment.Stuff.Armor;

/**
 * Класс щита
 */
public class Shield extends Armor
{
    public Shield()
    {
        this("Shield", 2);
    }
    private Shield(String name, int defPts)
    {
        super(name, defPts);
    }
}
