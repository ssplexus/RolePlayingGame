package Game.Units.Heroes;

import Game.Equipment.Equipment;
import Game.Equipment.Stuff.Medicine.Medicine;
import Game.Equipment.Stuff.Armor.Armor;
import Game.Equipment.Stuff.Armor.Shield;
import Game.Equipment.Stuff.Weapon.Axe;
import Game.Equipment.Stuff.Weapon.Sword;
import Game.Units.Abilities.*;
import Game.Units.Unit;

/**
 * Класс гнома
 */
public class Dwarf extends Unit implements AxeMaster, WearArmor, ApplyMedicine, GainExperience, Buyer
{
    // Базовые характеристики гнома
    public static final int HP = 70;
    public static final int FORCE = 10;
    public static final int DEXT = 10;

    // Возможное снаряжение для гнома
    private Axe axe; // топор
    private Armor armor; // броня

    /** Конструктор гнома
     *
     * @param name - имя
     * @param axe - топор
     * @param armor - броня
     */
    public Dwarf(String name, Axe axe, Armor armor)
    {
        this(name, HP, FORCE + axe.getPropertyPoints(),DEXT,armor.getPropertyPoints(), 0, 0, 0 , axe, armor);
    }

    private Dwarf(String name, int hp, int force, int dext, int defense, int level, int gold, int exp, Axe axe, Armor armor) {
        super(name, hp, force, dext, defense, level, gold, exp);
        setAxe(axe);
        setArmor(armor);
    }

    /** Купить снаряжение
     *
     * @param equipment - новое снаряжение
     * @return - старое снаряжение
     */
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

    /** Метод применимо ли снаряжение к данному классу персонажа
     *
     * @param equipment
     * @return
     */
    public static boolean isApplicable(Equipment equipment)
    {
        if(equipment instanceof Sword || equipment instanceof  Armor ||
                equipment instanceof Shield || equipment instanceof  Medicine) return true;
        return false;
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
     * @return -старая броня
     */
    @Override
    public Armor setArmor(Armor armor)
    {
        return (Armor) setEquipment(this.armor, this.armor = armor, false);
    }

    /** Применить медикамент
     *
     * @param medicine - медикамент
     * @return - результат
     */
    @Override
    public boolean applyMedicine(Medicine medicine)
    {
        if(medicine == null) return false;
        addHp(medicine.getPropertyPoints());
        return true;
    }

    /** Получить строку характеристик класса
     *
     * @return
     */
    public static String getClassDefaultCharacteristics()
    {
        return String.format("Dwarf : HP = %d | Force = %d | Dexterity =%d", HP, FORCE, DEXT);
    }

    /** Получить опыт
     *
     * @param exp - получаемый опыт
     */
    @Override
    public void gainExperience(int exp)
    {
        super.gainExperience(exp);
    }
}
