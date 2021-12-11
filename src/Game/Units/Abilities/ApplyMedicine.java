package Game.Units.Abilities;

import Game.Equipment.Stuff.Medicine.Medicine;

/**
 *  Интерфейс способности применять медикамент
 */
@FunctionalInterface
public interface ApplyMedicine
{
    boolean applyMedicine(Medicine medicine);
}
