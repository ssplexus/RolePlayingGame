package Game.Units.Monsters;

import Game.Equipment.Stuff.Armor.Armor;
import Game.Equipment.Stuff.Armor.SimpleArmor;
import Game.Equipment.Stuff.Weapon.Axe;
import Game.Units.Abilities.AxeMaster;
import Game.Units.Unit;
import Game.Units.Abilities.WearArmor;

public class Goblin extends Unit implements AxeMaster, WearArmor
{
    public static final int HP = 50;
    public static final int FORCE = 7;
    public static final int DEXT = 12;

    private Axe axe;
    private Armor armor;

    public Goblin()
    {
        this("Goblin", new Axe(), new SimpleArmor());
    }

    public Goblin(String name, Axe axe, Armor armor)
    {
        this(name, HP, FORCE + axe.getPropertyPoints(),DEXT, armor.getPropertyPoints(), 0, 30, 40 , axe, armor);
    }

    public Goblin(String name, int hp, int force, int dext, int defense, int level, int gold, int exp, Axe axe, Armor armor) {
        super(name, hp, force, dext, defense, level, gold, exp);
        setAxe(axe);
        setArmor(armor);
    }

    @Override
    public Axe setAxe(Axe axe)
    {
        return (Axe) setEquipment(this.axe, this.axe = axe, true);
    }

    @Override
    public Armor setArmor(Armor armor)
    {
        return (Armor) setEquipment(this.armor, this.armor = armor,false);
    }

    public static String getClassDefaultCharacteristics()
    {
        return String.format("Goblin : HP = %d | Force = %d | Dexterity =%d", HP, FORCE, DEXT);
    }
}
