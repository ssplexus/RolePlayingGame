package Game.Player;

import Game.Units.Unit;

public class Player
{
    private Unit playableCharacter;
    public Player(Unit playableCharacter)
    {
        this.playableCharacter = playableCharacter;
    }

    public Unit getCharacter()
    {
        return playableCharacter;
    }
}
