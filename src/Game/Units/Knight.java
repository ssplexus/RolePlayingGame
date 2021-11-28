package Game.Units;

import Game.Equipment.Stuff.Armor.Armor;
import Game.Equipment.Stuff.Armor.Shield;
import Game.Equipment.Stuff.Weapon.Sword;

public class Knight extends Unit implements SwordMaster, WearArmor, WieldShield
{
    private Sword sword;
    private Shield shield;
    private Armor armor;

    public Knight(String name, Sword sword, Shield shield, Armor armor )
    {
        this(name, 50, 5 + sword.getPropertyPoints(),
                shield.getPropertyPoints() + armor.getPropertyPoints(),
                15, 0, 0, 0,
                sword, shield, armor);
    }
    protected Knight(String name, int hp, int force, int dext, int defence, int level, int gold, int exp, Sword sword, Shield shield, Armor armor) {
        super(name, hp, force, dext, defence, level, gold, exp);
        this.sword = sword;
        this.shield = shield;
        this.armor = armor;
    }

    @Override
    public void setSword(Sword sword)
    {
        int force = getDefaultForce();
        if(this.sword == null)
        {
            this.sword = sword;
            setDefaultForce(force + sword.getPropertyPoints());
            return;
        }
        force -= this.sword.getPropertyPoints() + sword.getPropertyPoints();
        setDefaultForce(force);
    }

    @Override
    public void setArmor(Armor armor)
    {
        this.armor = armor;
    }

    @Override
    public void setShield(Shield shield)
    {
        this.shield = shield;
    }
}
