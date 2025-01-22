package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategyAlgo implements WinningStrategy{
    private Map<Symbol, Integer> leftDiagHashmap = new HashMap<>();
    private Map<Symbol, Integer> rightDiagHashmap = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        //check if this cell is part of left diagonal
        if(row == col){
            leftDiagHashmap.put(symbol, leftDiagHashmap.getOrDefault(symbol,0) + 1);

            int count = leftDiagHashmap.get(symbol);
            return count == board.getDimension();
        }

        if(row + col == board.getDimension() - 1){
            rightDiagHashmap.put(symbol, rightDiagHashmap.getOrDefault(symbol,0) + 1);

            int count = rightDiagHashmap.get(symbol);
            return count == board.getDimension();
        }

        return false;
    }

    @Override
    public void handleUndo(Move move) {
        //HW
    }
}

/*
[O O O]
[X .. X]
[X X X]

(0,0) (1,1) (2,2) - left diagonal -> row == col
(0,2) (1,1) (2,0) - right diagonal -> row + col == dimension

(0,1) -??
 */
