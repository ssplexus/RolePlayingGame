package Game.Units.Abilities;

import Game.Equipment.Stuff.Medicine.Medicine;

@FunctionalInterface
public interface ApplyMedicine
{
    boolean applyMedicine(Medicine medicine);
}
