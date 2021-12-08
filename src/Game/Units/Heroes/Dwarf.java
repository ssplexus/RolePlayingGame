package Game.Units.Heroes;

import Game.Equipment.Equipment;
import Game.Equipment.Stuff.Medicine.Medicine;
import Game.Equipment.Stuff.Armor.Armor;
import Game.Equipment.Stuff.Armor.Shield;
import Game.Equipment.Stuff.Weapon.Axe;
import Game.Equipment.Stuff.Weapon.Sword;
import Game.Units.Abilities.*;
import Game.Units.Unit;

public class Dwarf extends Unit implements AxeMaster, WearArmor, ApplyMedicine, GainExperience, Buyer
{
    public static final int HP = 70;
    public static final int FORCE = 10;
    public static final int DEXT = 10;

    private Axe axe;
    private Armor armor;

    public Dwarf(String name, Axe axe, Armor armor)
    {
        this(name, HP, FORCE + axe.getPropertyPoints(),DEXT,armor.getPropertyPoints(), 0, 0, 0 , axe, armor);
    }

    public Dwarf(String name, int hp, int force, int dext, int defense, int level, int gold, int exp, Axe axe, Armor armor) {
        super(name, hp, force, dext, defense, level, gold, exp);
        setAxe(axe);
        setArmor(armor);
    }

    public Equipment buy(Equipment equipment)
    {
        if(equipment instanceof Axe) return setAxe((Axe) equipment);
        if(equipment instanceof  Armor) return setArmor((Armor) equipment);
        if(equipment instanceof  Medicine)
        {
            applyMedicine((Medicine) equipment);
            return equipment;
        }
        return null;
    }

    public static boolean isApplicable(Equipment equipment)
    {
        if(equipment instanceof Sword || equipment instanceof  Armor ||
                equipment instanceof Shield || equipment instanceof  Medicine) return true;
        return false;
    }

    @Override
    public Axe setAxe(Axe axe)
    {
        return (Axe) setEquipment(this.axe, this.axe = axe, true);
    }

    @Override
    public Armor setArmor(Armor armor)
    {
        return (Armor) setEquipment(this.armor, this.armor = armor, false);
    }

    public static String getClassDefaultCharacteristics()
    {
        return String.format("Dwarf : HP = %d | Force = %d | Dexterity =%d", HP, FORCE, DEXT);
    }

    @Override
    public boolean applyMedicine(Medicine medicine)
    {
        if(medicine == null) return false;
        addHp(medicine.getPropertyPoints());
        return true;
    }
}
