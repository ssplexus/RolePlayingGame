package Game.Units.Heroes;

import Game.Equipment.Equipment;
import Game.Equipment.Stuff.Medicine.Medicine;
import Game.Equipment.Stuff.Armor.Armor;
import Game.Equipment.Stuff.Armor.Shield;
import Game.Equipment.Stuff.Weapon.Sword;
import Game.Units.Abilities.*;
import Game.Units.Unit;

/**
 * Класс рыцаря
 */
public class Knight extends Unit implements SwordMaster, WearArmor, WieldShield, ApplyMedicine, GainExperience, Buyer
{
    // Базовые характеристики рыцаря
    public static final int HP = 50; // здоровье
    public static final int FORCE = 5; // сила атаки
    public static final int DEXT = 15; // ловкость

    // Возможное снаряжение для рыцаря
    private Sword sword; // меч
    private Shield shield; // щит
    private Armor armor; // броня

    /** Конструктор рыцаря
     *
     * @param name - имя
     * @param sword - меч
     * @param shield - щит
     * @param armor - броня
     */
    public Knight(String name, Sword sword, Shield shield, Armor armor )
    {
        this(name, HP, FORCE + sword.getPropertyPoints(),
                DEXT,
                shield.getPropertyPoints() + armor.getPropertyPoints(), 0, 0, 0,
                sword, shield, armor);
    }

    private Knight(String name, int hp, int force, int dext, int defence, int level, int gold, int exp, Sword sword, Shield shield, Armor armor) {
        super(name, hp, force, dext, defence, level, gold, exp);
        setSword(sword);
        setShield(shield);
        setArmor(armor);
    }

    /** Купить снаряжение
     *
     * @param equipment - новое снаряжение
     * @return - старое снаряжение
     */
    public Equipment buy(Equipment equipment)
    {
        if(equipment instanceof Sword) return setSword((Sword) equipment);
        if(equipment instanceof  Armor) return setArmor((Armor) equipment);
        if(equipment instanceof Shield) return setShield((Shield) equipment);
        if(equipment instanceof  Medicine)
        {
            applyMedicine((Medicine) equipment); // применить медикамент
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
     * @return -старая броня
     */
    @Override
    public Armor setArmor(Armor armor)
    {
        return (Armor) setEquipment(this.armor, this.armor = armor, false);
    }

    /** Установить щит
     *
     * @param shield - щит
     * @return - старый щит
     */
    @Override
    public Shield setShield(Shield shield)
    {
        return (Shield) setEquipment(this.shield, this.shield = shield,false);
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
        return String.format("Knight : HP = %d | Force = %d | Dexterity =%d", HP, FORCE, DEXT);
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
