package Game.Units.Monsters;

import Game.Equipment.Stuff.Armor.Armor;
import Game.Equipment.Stuff.Armor.SimpleArmor;
import Game.Equipment.Stuff.Weapon.Axe;
import Game.Units.Abilities.AxeMaster;
import Game.Units.Unit;
import Game.Units.Abilities.WearArmor;

/**
 * Класс гоблина
 */
public class Goblin extends Unit implements AxeMaster, WearArmor
{
    // Базовые характеристики гоблина
    public static final int HP = 50; // здоровье
    public static final int FORCE = 7; // сила
    public static final int DEXT = 12; // ловкость

    // Возможное снаряжение для гоблина
    private Axe axe; // топор
    private Armor armor; // броня

    /**
     *  Конструктор гоблина по умолчанию
     */
    public Goblin()
    {
        this("Goblin", new Axe(), new SimpleArmor());
    }

    public Goblin(String name, Axe axe, Armor armor)
    {
        this(name, HP, FORCE + axe.getPropertyPoints(),DEXT, armor.getPropertyPoints(), 0, 30, 40 , axe, armor);
    }

    private Goblin(String name, int hp, int force, int dext, int defense, int level, int gold, int exp, Axe axe, Armor armor) {
        super(name, hp, force, dext, defense, level, gold, exp);
        setAxe(axe);
        setArmor(armor);
    }

    /** Установить топор
     *
     * @param axe - топор
     * @return - старый топор
     */
    @Override
    public Axe setAxe(Axe axe)
    {
        return (Axe) setEquipment(this.axe, this.axe = axe, true);
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
