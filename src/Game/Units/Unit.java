package Game.Units;

import Game.Equipment.Equipment;
import Game.Equipment.Stuff.Weapon.Sword;

import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

public abstract class Unit
{
    private final int MAX_EXP = 99;
    private final int MAX_LEVEL = 9;
    private final int MAX_DEXT = 90;

    private final int DEFAULT_HP;
    private final int DEFAULT_DEXT;

    // Имя юнита
    private String name;

    // Параметр здоровья
    private int hp;

    private  int force;

    private int defense;

    private int gold;

    private int level;

    private int exp;

    private boolean isCounter;
    private Function<Unit, Integer> damageAlgorithm;

    // Юнита действующий или уничтожен
    private boolean isDestroyed;

    public Unit(String name, int hp, int force, int dext, int defense, int level, int gold, int exp)
    {
        this.name = name;
        this.force = force;
        DEFAULT_HP = hp;
        DEFAULT_DEXT = dext;
        this.level = level > MAX_LEVEL? MAX_LEVEL: level;
        this.gold = gold;
        this.exp = exp;
        this.defense = defense;
        isCounter = false;
        isDestroyed = false;
        damageAlgorithm = unit->unit.getDext() * 3 > new Random().nextInt(101) ? unit.getForce() : 0;
    }

    public void getDamaged( Unit attacker, Function<Unit, Integer> damageAlgorithm, boolean x2)
    {
        int damage = damageAlgorithm.apply(attacker) * (x2 ? 2 : 1);
        hp -= damage - defense;
        if(hp <= 0) // Если здоровье ниже 0 то юнит уничтожен
        {
            isDestroyed = true;
            System.out.println(String.format("%s get damage %d pts and destroyed!", name, damage));
        }
        else
            System.out.println(String.format("%s get damage %d pts", name, damage));
    }

    public void attack(Unit unit)
    {
        System.out.println(String.format("%s attacks %s", getName(), unit.getName()));
        if(unit.isCounter())
        {
            if(unit.getDext() * 3 > new Random().nextInt(101))
                getDamaged(unit, damageAlgorithm, true);
            else
                unit.getDamaged(this, damageAlgorithm, true);
        }
        else
            unit.getDamaged(this, damageAlgorithm, false);

        if(unit.isDestroyed())
        {
            System.out.format("[+%d exp | +%d gold]\n", unit.getExp(), unit.getGold());
            gainExperience(unit.getExp()  + (int) (unit.getLevel() * unit.getExp() * 0.1));
            gold += unit.getGold();
        }
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
        int maxHp = (int) (DEFAULT_HP + DEFAULT_HP * 0.1 * level);
        int oldHp = this.hp;
        this.hp = this.hp + hp > maxHp ? maxHp : this.hp + hp;
        System.out.printf("Health restored by %d pts", this.hp - oldHp);
    }

    /** Проверка уничтожен ли юнит
     *
     * @return флаг isDestroyed
     */
    public boolean isDestroyed() { return isDestroyed; }

    public boolean isCounter()
    {
        return isCounter;
    }

    protected void  setDefaultForce(int force)
    {
        this.force = force;
    }

    public void setDefaultDefense(int defense)
    {
        this.defense = defense;
    }

    public void restoreHp()
    {
        hp = (int) (DEFAULT_HP + DEFAULT_HP * 0.1 * level);
    }

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

    public int getLevel()
    {
        return level;
    }

    public int getGold()
    {
        return gold;
    }

    public int getExp()
    {
        return exp;
    }

    public int getHp()
    {
        return hp;
    }

    public int getMaxHp()
    {
        return (int) (DEFAULT_HP + DEFAULT_HP * 0.1 * level);
    }

    protected int getDefaultForce()
    {
       return force;
    }

    public int getForce()
    {
        return (int) (force + force * 0.1 * level);
    }

    protected int getDefaultDefense()
    {
        return defense;
    }

    public int getDext()
    {
        int dext = (int) (DEFAULT_DEXT + DEFAULT_DEXT * 0.1 * level);
        return dext > MAX_DEXT ? MAX_DEXT : dext;
    }

    protected Equipment setAtackEquipment(Equipment oldEquipment, Equipment newEquipment)
    {
        setDefaultForce(getDefaultForce() - (oldEquipment != null ? oldEquipment.getPropertyPoints() : 0) +
                newEquipment.getPropertyPoints());
        Equipment oldEq = oldEquipment;
        newEquipment  = oldEquipment;
        return oldEq;
    }

    protected Equipment setDefenseEquipment(Equipment oldEquipment, Equipment newEquipment)
    {
        setDefaultDefense(getDefaultDefense() - (oldEquipment != null ? oldEquipment.getPropertyPoints() : 0) +
                newEquipment.getPropertyPoints());
        Equipment oldEq = oldEquipment;
        newEquipment  = oldEquipment;
        return oldEq;
    }

}