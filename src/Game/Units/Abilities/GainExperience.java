package Game.Units.Abilities;

/**
 *  Интерфейс способности получать опыт
 */
@FunctionalInterface
public interface GainExperience
{
    void gainExperience(int exp);
}
