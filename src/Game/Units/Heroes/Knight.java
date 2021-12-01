package Game.Units.Heroes;

import Game.Equipment.Equipment;
import Game.Equipment.Stuff.Medicine.Medicine;
import Game.Equipment.Stuff.Armor.Armor;
import Game.Equipment.Stuff.Armor.Shield;
import Game.Equipment.Stuff.Weapon.Sword;
import Game.Units.Abilities.ApplyMedicine;
import Game.Units.Abilities.SwordMaster;
import Game.Units.Unit;
import Game.Units.Abilities.WearArmor;
import Game.Units.Abilities.WieldShield;

public class Knight extends Unit implements SwordMaster, WearArmor, WieldShield, ApplyMedicine
{
    public static final int HP = 50;
    public static final int FORCE = 5;
    public static final int DEXT = 15;

    private Sword sword;
    private Shield shield;
    private Armor armor;

    public Knight(String name, Sword sword, Shield shield, Armor armor )
    {
        this(name, HP, FORCE + sword.getPropertyPoints(),
                DEXT,
                shield.getPropertyPoints() + armor.getPropertyPoints(), 0, 0, 0,
                sword, shield, armor);
    }
    protected Knight(String name, int hp, int force, int dext, int defence, int level, int gold, int exp, Sword sword, Shield shield, Armor armor) {
        super(name, hp, force, dext, defence, level, gold, exp);
        this.sword = sword;
        this.shield = shield;
        this.armor = armor;
    }

    public Equipment buyEquipment(Equipment equipment)
    {
        if(equipment instanceof Sword) return setSword((Sword) equipment);
        if(equipment instanceof  Armor) return setArmor((Armor) equipment);
        if(equipment instanceof Shield) return setShield((Shield) equipment);
        if(equipment instanceof  Medicine) applyMedicine((Medicine) equipment);
        return null;
    }

    public static boolean isApplicable(Equipment equipment)
    {
        if(equipment instanceof Sword || equipment instanceof  Armor ||
        equipment instanceof Shield || equipment instanceof  Medicine) return true;
        return false;
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

    @Override
    public Shield setShield(Shield shield)
    {
        return (Shield) setDefenseEquipment(this.shield, shield);
    }

    @Override
    public boolean applyMedicine(Medicine medicine)
    {
        if(medicine == null) return false;
        addHp(medicine.getPropertyPoints());
        return true;
    }

    public static String getClassDefaultCharacteristics()
    {
        return String.format("Knight : HP = %d | Force = %d | Dexterity =%d", HP, FORCE, DEXT);
    }
}