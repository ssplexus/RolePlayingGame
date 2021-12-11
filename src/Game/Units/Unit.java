package Game.Units;

import Game.Equipment.Equipment;
import Game.Units.Abilities.GainExperience;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * Абстрактный класс персонажей игры
 */
public abstract class Unit
{
    private final int MAX_EXP = 99; // максимальное значение опыта
    private final int MAX_LEVEL = 9; // максимальное значение уровня
    private final int MAX_DEXT = 90; // максимальное значение ловкости

    private final int DEFAULT_HP; // базовое здоровье (без учёта уровней)
    private final int DEFAULT_DEXT; // базовая ловкость (без учёта уровней)

    private String name; // имя юнита
    private int hp; // параметр здоровья
    private  int force; // сила атаки юнита
    private int defense; // очки защиты юнита
    private int gold; // кол-во золота юнита
    private int level; // уровень юнита
    private int exp; // опыт юнита
    private boolean isCounter; // признак контратаки
    private boolean isDestroyed; // признак юнит действующий или уничтожен

    private List<Equipment> equipmentList; // снаряжение юнита
    private Function<Unit, Integer> damageAlgorithm; // алгоритм урона

    /**
     * Конструктор
     * @param name - имя персонажа
     * @param hp - базовое здоровье
     * @param force - сила атаки
     * @param dext - базовая ловкость
     * @param defense - защита
     * @param level - уровень персонажа
     * @param gold - золото персонажа (столько он отдаст в случае уничтожения)
     * @param exp - опыт персонажа (столько он отдаст в случае уничтожения)
     */
    protected Unit(String name, int hp, int force, int dext, int defense, int level, int gold, int exp)
    {
        this.name = name;
        this.force = force;
        DEFAULT_HP = hp;
        DEFAULT_DEXT = dext;
        this.level = level > MAX_LEVEL? MAX_LEVEL: level; // уровень не может быть выше максимального
        this.gold = gold;
        this.exp = exp;
        this.defense = defense;
        this.hp = getMaxHp(); // инициализируем здоровье макс возможным значением здоровья для текущего уровня персонажа
        isCounter = false;
        isDestroyed = false;
        // Алгоритм получения урона - урон засчитывается если случайное число в пределах 100 < 3 х ловкость
        damageAlgorithm = unit->unit.getDext() * 3 > new Random().nextInt(101) ? unit.getForce() : 0;
        equipmentList = new ArrayList<>();
    }

    /**
     * Метод получения урона
     * @param attacker - атакующий юнит
     * @param damageAlgorithm - алгоритм урона
     * @param x2 -  признак удвоенной атаки
     */
    protected void getDamaged( Unit attacker, Function<Unit, Integer> damageAlgorithm, boolean x2)
    {
        int damage = damageAlgorithm.apply(attacker) * (x2 ? 2 : 1); // вычисляем урон
        int totalDmg = (damage - defense < 0) ? 0 : damage - defense; // итоговый урон за вычетом очков защиты
        hp -= totalDmg; // уменьшение здоровья на итоговый урон
        if(hp <= 0) // Если здоровье ниже 0, то юнит уничтожен
        {
            isDestroyed = true;
            System.out.println(String.format("%s get damage %d pts and destroyed!", name, totalDmg));
        }
        else
            System.out.println(String.format("%s get damage %d pts", name, totalDmg));
    }

    /**
     * Метод акаки
     * @param unit - юнит которого атакуем
     * @param x2 - признак удвоенной атаки
     */
    public void attack(Unit unit, boolean x2)
    {
        System.out.println(String.format("%s attacks %s", getName(), unit.getName()));
        if(unit.isCounter()) // если противник подготовил контратаку
        {
            unit.tryToCounter(false);
            if(unit.getDext() * 3 > new Random().nextInt(101)) // вычисление успеха контратаки
            {
                System.out.printf("%s was counterattacked!\n", getName());
                unit.attack(this, true); // удвоенная атака в случае успеха контратаки
                return;
            }
            else
            {
                System.out.printf("%s's counter failed!\n", unit.getName());
                unit.getDamaged(this, damageAlgorithm, true); // удвоенный ответный урон в случае промаха
            }
        }
        else
            unit.getDamaged(this, damageAlgorithm, x2); // нанесение урона противнику

        if(unit.isDestroyed()) // если вражеский юнит повержен
        {
            if(this instanceof GainExperience) // получить опыт и золото если юнит умеет получать опыт
            {
                System.out.printf("[+%d exp | +%d gold]\n", unit.getExp(), unit.getGold());
                gainExperience(unit.getExp()  + (int) (getLevel() * unit.getExp() * 0.1));
                gold += unit.getGold();
            }
        }
    }

    /** Метод получения опыта и повышения уровня
     *
     * @param exp - получаемый опыт
     */
    protected void gainExperience(int exp)
    {
        if(level >= MAX_LEVEL) return;
        this.exp += exp;
        if(this.exp > MAX_EXP)
        {
            level++;
            System.out.println("Level up!!!");
            this.exp = 0;
            this.restoreHp();
            if(exp - MAX_EXP > 0) gainExperience(exp - MAX_EXP);
        }
    }

    /** Добавить здоровья юниту
     *
     * @param hp - количество очков здоровья
     */
    public void addHp(int hp)
    {
        int maxHp = getMaxHp();
        int oldHp = this.hp;
        this.hp = this.hp + hp > maxHp ? maxHp : this.hp + hp;
        System.out.printf("Health restored by %d pts\n", this.hp - oldHp);
    }

    /** Признак уничтожен ли юнит
     *
     * @return флаг isDestroyed
     */
    public boolean isDestroyed() { return isDestroyed; }

    /** Признак контратаки
     *
     * @return
     */
    public boolean isCounter()
    {
        return isCounter;
    }

    /**
     * Установить признак "юнит уничтожен"
     */
    public void setDestroyed()
    {
        hp = 0;
        isDestroyed = true;
    }

    /** Установить/снять признак попытки контратаковать
     *
     * @param isCounter - флаг контратаки
     */
    public void tryToCounter(boolean isCounter)
    {
        this.isCounter = isCounter;
    }

    /** Установить силу по умолчанию (без учёта уровня персонажа)
     *
     * @param force - сила по умолчанию
     */
    protected void  setDefaultForce(int force)
    {
        this.force = force;
    }

    /** Установить защиту по умолчанию (без учёта уровня персонажа)
     *
     * @param defense - сила по умолчанию
     */
    protected void setDefaultDefense(int defense)
    {
        this.defense = defense;
    }

    /**
     * Метод восстановить здоровью к максимальному значению
     */
    public void restoreHp()
    {
        //мак здоровье = базовое здоровье +10% от базового здоровья x на уровень персонажа
        hp = (int) (DEFAULT_HP + DEFAULT_HP * 0.1 * level);
    }

    /** Установить количество золота для юнита
     *
     * @param gold
     */
    public void setGold(int gold)
    {
        this.gold = gold;
    }

    /** Получить имя юнита
     *
     * @return имя юнита
     */
    public String getName()
    {
        return name;
    }

    /** Получить уровень юнита
     *
     * @return уровень
     */
    public int getLevel()
    {
        return level;
    }

    /** Получить кол-во золота у юнита
     *
     * @return золото
     */
    public int getGold()
    {
        return gold;
    }

    /** Получить кол-во опыта юнита
     *
     * @return опыт
     */
    public int getExp()
    {
        return exp;
    }

    /** Получить текущее здоровье юнита
     *
     * @return золото
     */
    public int getHp()
    {
        return hp;
    }

    /** Получить максимально возможное здоровье юнита
     *
     * @return макс здоровье
     */
    public int getMaxHp()
    {
        return (int) (DEFAULT_HP + DEFAULT_HP * 0.1 * level);
    }

    /** Получить силу атаки по умолчанию (без учёта уровня)
     *
     * @return
     */
    protected int getDefaultForce()
    {
       return force;
    }

    /** Получить текущую силу атаки (с учётом уровня)
     *
     * @return сила атаки
     */
    public int getForce()
    {
        return (int) (force + force * 0.1 * level);
    }

    /** Получить защиту по умолчанию (без учёта уровня)
     *
     * @return
     */
    protected int getDefaultDefense()
    {
        return defense;
    }

    /** Получить защиту (с учётом уровня)
     *
     * @return
     */
    public int getDefense() {return (int) (defense + defense * 0.1 * level);}

    /** Получить ловкость (с учётом уровня)
     *
     * @return
     */
    public int getDext()
    {
        int dext = (int) (DEFAULT_DEXT + DEFAULT_DEXT * 0.1 * level);
        return dext > MAX_DEXT ? MAX_DEXT : dext;
    }

    /** Замена элемениа снаряжения
     *
     * @param oldEquipment - старое снаряжение
     * @param newEquipment - новое снаряжение
     * @param isAtack - это снаряжение для атаки или нет
     * @return - старое снаряжение
     */
    protected Equipment setEquipment(Equipment oldEquipment, Equipment newEquipment, boolean isAtack)
    {
        if(isAtack) // если это снаряжение для атаки, то изменяем очки силы атаки
            setDefaultForce(getDefaultForce() - (oldEquipment != null ? oldEquipment.getPropertyPoints() : 0) +
                    newEquipment.getPropertyPoints());
        else // если это снаряжение для защиты, то изменяем очки защиты
            setDefaultDefense(getDefaultDefense() - (oldEquipment != null ? oldEquipment.getPropertyPoints() : 0) +
                    newEquipment.getPropertyPoints());

        // Удаляем старое снаряжение (если было) и устанавливаем новое
        if(equipmentList.contains(oldEquipment)) equipmentList.remove(oldEquipment);
        equipmentList.add(newEquipment);
        return oldEquipment;
    }

    /**
     * Вывести список снаряжения персонажа
     */
    public void printEquipment()
    {
        StringBuilder res = new StringBuilder();
        res.append(String.format("%s's equipment: ", name));
        if(!equipmentList.isEmpty())
            equipmentList.forEach(e->res.append(e.toString() + " "));
        else res.append("Empty");
        System.out.println(res);
    }

    /**
     * Вывести здоровье персонажа
     */
    public void printHP()
    {
        System.out.printf("%s's hp: %d/%d\n", name, getHp(), getMaxHp());
    }

    /** Строковое представление класса юнита
     *
     * @return
     */
    @Override
    public String toString()
    {
        return String.format("%s: [level = %d][force = %d pts.][defense = %d pts.][dext = %d pts.][hp = %d/%d pts.]",
                getName(), getLevel(), getForce(), getDefense(), getDext(), getHp(), getMaxHp());
    }

}