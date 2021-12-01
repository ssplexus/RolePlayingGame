package Game.Units.Monsters;

import Game.Equipment.Stuff.Armor.Armor;
import Game.Equipment.Stuff.Weapon.Sword;
import Game.Units.Abilities.SwordMaster;
import Game.Units.Unit;
import Game.Units.Abilities.WearArmor;

public class Skeleton extends Unit implements SwordMaster, WearArmor
{
    public static final int HP = 20;
    public static final int FORCE = 1;
    public static final int DEXT = 20;

    private Sword sword;
    private Armor armor;

    public Skeleton(String name, Sword sword, Armor armor)
    {
        this(name, HP, FORCE + sword.getPropertyPoints(), DEXT, armor.getPropertyPoints(),
                0,10,20, sword, armor);
    }
    private Skeleton(String name, int hp, int force, int dext, int defense, int level, int gold, int exp,
                Sword sword, Armor armor)
    {
        super(name, hp, force, dext, defense, level, gold, exp);
        this.sword = sword;
        this.armor =armor;
    }

    @Override
    public Sword setSword(Sword sword)
    {
        return (Sword) setAtackEquipment(this.sword, sword);
    }

    @Override
    public Armor setArmor(Armor armor)
    {
        return (Armor) setDefenseEquipment(this.armor, armor);
    }

}