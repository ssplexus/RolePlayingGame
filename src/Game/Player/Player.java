package Game.Player;

import Game.Units.Unit;

/**
 *  Класс игрока
 */
public class Player
{
    // Игровой персонаж
    private Unit playableCharacter;

    /** Конструктор игрока
     *
     * @param playableCharacter - игровой персонаж
     */
    public Player(Unit playableCharacter)
    {
        this.playableCharacter = playableCharacter;
    }

    /** Получить текущего игрового персонажа
     *
     * @return игровой персонаж
     */
    public Unit getCharacter()
    {
        return playableCharacter;
    }
}
