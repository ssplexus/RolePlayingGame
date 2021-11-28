package Game.Units;

import java.util.Random;

public abstract class Unit
{
    private static final int MAX_EXP = 99;
    private static final int MAX_LEVEL = 9;

    private final int MAX_FORCE;
    private final int MAX_HP;
    private final int MAX_DEXT;


    // Имя юнита
    private String name;
    // Параметр здоровья
    private int hp;

    private int force;

    // Параметр ловкости
    private int dext;

    private int gold;

    private int level;

    private int exp;

    // Юнита действующий или уничтожен
    private boolean isDestroyed;

    public Unit(String name, int hp, int force, int dext, int level, int gold, int exp)
    {
        this.name = name;
        MAX_FORCE = force;
        MAX_HP = hp;
        MAX_DEXT = dext;
        this.level = level > MAX_LEVEL? MAX_LEVEL: level;
        this.force = MAX_FORCE * level;
        this.dext = MAX_DEXT * level;
        this.gold = gold;
        this.exp = exp;
    }

    /** Метод обработки получения повроеждения
     *
     * @param damage - значение повреждения
     */
    public void getDamaged(int damage)
    {
        hp -= damage;
        if(hp <= 0) // Если здоровье ниже 0 то юнит уничтожен
        {
            isDestroyed = true;
            System.out.println(String.format("%s get damage %d pts", name, damage));
            System.out.println(String.format("%s destroyed +%d exp pts", name, exp));
        }
        else
            System.out.println(String.format("%s get damage %d pts", name, damage));
    }

    /** Переопределённый метод атаки интрефейса воина
     *
     * @param unit - целевой юнит
     */
    public void attack(Unit unit)
    {
        System.out.println(String.format("%s attacks %s", getName(), unit.getName()));
        unit.getDamaged(force * new Random().nextInt(101) / 100); // вычисление силы повреждения
        if(unit.isDestroyed()) gainExperience(unit.getExp());
    }

    public void gainExperience(int exp)
    {
        if(level >= MAX_LEVEL) return;
        this.exp += exp;
        if(this.exp > MAX_EXP)
        {
            level++;
            System.out.println("Level up!!!");
            this.exp = 0;
            if(exp - MAX_EXP > 0) gainExperience(exp - MAX_EXP);
        }
    }

    /** Добавить здоровья юниту
     *
     * @param hp - количество очков здоровья
     */
    public void addHp(int hp)
    {
        this.hp += hp;
    }

    /** Получить значение здоровья
     *
     * @return hp
     */
    public int getHp()
    {
        return hp;
    }

    /** Проверка уничтожен ли юнит
     *
     * @return флаг isDestroyed
     */
    public boolean isDestroyed() { return isDestroyed; }

    /** Получить имя юнита
     *
     * @return имя юнита
     */
    public String getName()
    {
        return name;
    }

    public int getExp()
    {
        return exp;
    }
}