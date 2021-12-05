package Game.Units;

import Game.Equipment.Equipment;

import java.util.ArrayList;
import java.util.List;
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

    private List<Equipment> equipmentList;

    private boolean isCounter;
    private Function<Unit, Integer> damageAlgorithm;

    // Юнит действующий или уничтожен
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
        this.hp = getMaxHp();
        isCounter = false;
        isDestroyed = false;
        damageAlgorithm = unit->unit.getDext() * 3 > new Random().nextInt(101) ? unit.getForce() : 0;
        equipmentList = new ArrayList<>();
    }

    public void getDamaged( Unit attacker, Function<Unit, Integer> damageAlgorithm, boolean x2)
    {
        int damage = damageAlgorithm.apply(attacker) * (x2 ? 2 : 1);
        int totalDmg = (damage - defense < 0) ? 0 : damage - defense;
        hp -= totalDmg;
        if(hp <= 0) // Если здоровье ниже 0 то юнит уничтожен
        {
            isDestroyed = true;
            System.out.println(String.format("%s get damage %d pts and destroyed!", name, totalDmg));
        }
        else
            System.out.println(String.format("%s get damage %d pts", name, totalDmg));
    }

    public void attack(Unit unit, boolean x2)
    {
        System.out.println(String.format("%s attacks %s", getName(), unit.getName()));
        if(unit.isCounter())
        {
            if(unit.getDext() * 3 > new Random().nextInt(101))
            {
                System.out.printf("%s goblin was counterattacked!", getName());
//                getDamaged(unit, damageAlgorithm, true);
                unit.attack(this, x2);
            }
            else
            {
                System.out.printf("%s's counter failed!");
                System.out.println(String.format("%s attacks %s", unit.getName(), getName()));
                unit.getDamaged(this, damageAlgorithm, true);
            }
        }
        else
            unit.getDamaged(this, damageAlgorithm, false);

        if(unit.isDestroyed())
        {
            System.out.printf("%s is destroyed!\n", unit.getName());
            System.out.printf("[+%d exp | +%d gold]\n", unit.getExp(), unit.getGold());
            gainExperience(unit.getExp()  + (int) (getLevel() * unit.getExp() * 0.1));
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

    public void setDestroyed()
    {
        hp = 0;
        isDestroyed = true;
    }

    public void tryToCounter()
    {
        isCounter = true;
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

    public int getDefense() {return (int) (defense + defense * 0.1 * level);}

    public int getDext()
    {
        int dext = (int) (DEFAULT_DEXT + DEFAULT_DEXT * 0.1 * level);
        return dext > MAX_DEXT ? MAX_DEXT : dext;
    }

    protected Equipment setEquipment(Equipment oldEquipment, Equipment newEquipment, boolean isAtack)
    {
        if(isAtack)
            setDefaultForce(getDefaultForce() - (oldEquipment != null ? oldEquipment.getPropertyPoints() : 0) +
                    newEquipment.getPropertyPoints());
        else
            setDefaultDefense(getDefaultDefense() - (oldEquipment != null ? oldEquipment.getPropertyPoints() : 0) +
                    newEquipment.getPropertyPoints());

        if(equipmentList.contains(oldEquipment)) equipmentList.remove(oldEquipment);
        equipmentList.add(newEquipment);
        return oldEquipment;
    }

    public void printEquipment()
    {
        StringBuilder res = new StringBuilder();
        res.append(String.format("%s's equipment: ", name));
        if(!equipmentList.isEmpty())
            equipmentList.forEach(e->res.append(e.toString() + " "));
        else res.append("Empty");
        System.out.println(res);
    }

    public void printHP()
    {
        System.out.printf("%s's hp: %d/%d\n", name, getHp(), getMaxHp());
    }

    @Override
    public String toString()
    {
        return String.format("%s : [force = %d pts.][defense = %d pts.][dext = %d pts.][hp = %d/%d pts.]",
                getName(), getForce(), getDefense(), getDext(), getHp(), getMaxHp());
    }

}