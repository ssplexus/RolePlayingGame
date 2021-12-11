package Game.Units.Monsters;

import Game.Equipment.Stuff.Armor.Armor;
import Game.Equipment.Stuff.Armor.SimpleArmor;
import Game.Equipment.Stuff.Weapon.Sword;
import Game.Units.Abilities.SwordMaster;
import Game.Units.Unit;
import Game.Units.Abilities.WearArmor;

/**
 *  Класс скелета
 */
public class Skeleton extends Unit implements SwordMaster, WearArmor
{
    // Базовые характеристики гоблина
    public static final int HP = 20; // здоровье
    public static final int FORCE = 1; // сила
    public static final int DEXT = 20; // ловкость

    // Возможное снаряжение для скелета
    private Sword sword; // меч
    private Armor armor; // броня

    /**
     *  Конструктор скелета по умолчанию
     */
    public Skeleton ()
    {
        this("Skeleton", new Sword(), new SimpleArmor());
    }
    public Skeleton(String name, Sword sword, Armor armor)
    {
        this(name, HP, FORCE + sword.getPropertyPoints(), DEXT, armor.getPropertyPoints(),
                0,15,25, sword, armor);
    }
    private Skeleton(String name, int hp, int force, int dext, int defense, int level, int gold, int exp,
                Sword sword, Armor armor)
    {
        super(name, hp, force, dext, defense, level, gold, exp);
        setSword(sword);
        setArmor(armor);
    }

    /** Установить меч
     *
     * @param sword - меч
     * @return - старый меч
     */
    @Override
    public Sword setSword(Sword sword)
    {
        return (Sword) setEquipment(this.sword, this.sword = sword, true);
    }

    /** Установить броню
     *
     * @param armor - броня
     * @return - старая броня
     */
    @Override
    public Armor setArmor(Armor armor)
    {
        return (Armor) setEquipment(this.armor, this.armor = armor,false);
    }

}
