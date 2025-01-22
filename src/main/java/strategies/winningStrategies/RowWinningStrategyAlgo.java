package strategies.winningStrategies;

import models.Board;
import models.Cell;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategyAlgo implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> rowHashmaps = new HashMap<>();
    //({(rowNo, ()} , {} , {} )
    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        //0

        //putting a hashmap for the row
        if(!rowHashmaps.containsKey(row)){
            rowHashmaps.put(row, new HashMap<>());
        }

        Map<Symbol, Integer> hm = rowHashmaps.get(row);
        //if symbol doesn't exist, use 0
        hm.put(symbol, hm.getOrDefault(symbol, 0) + 1);

        int count = hm.get(symbol);
        return count == board.getDimension();
    }

    @Override
    public void handleUndo(Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol, Integer> rowMap = rowHashmaps.get(row);
        rowMap.put(symbol, rowMap.get(symbol) - 1);
    }
}

/*
O(1) for checking the winner
Board:-

[X       ] -> ({X,1})
[X   X  O] -> ({X,2} , {O,1})
[O   O  O] -> ({O,3})

Move -> {Player, Cell}
First I'll update the count of symbol played in the current row
then i'll check what's the updated count of the symbol?
If count == N, the current player has won the game

for every row, we need a hashmap<symbol, count>?
 */
