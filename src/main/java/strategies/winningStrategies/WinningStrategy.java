package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Player;

public interface WinningStrategy {
    //family of algorithms to check for winner of the game
    //after any move, we are checking if the player made a winning move
    //A has made a move, whether A has won or not?
    public boolean checkWinner(Move move, Board board);

    void handleUndo(Move move);
}
