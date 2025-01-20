package models;
import exceptions.InvalidBotCountException;
import exceptions.InvalidPlayerCountException;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;


public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerTurnIndex;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;
    /*
    across the rows
    across the columns
     */

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.nextPlayerTurnIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
    }

    public void printBoard(){
        board.print();
    }

    public void makeMove(){

    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        //Game game = Game.getBuilder().setdimension(12).setplayers().setWinning().build();

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validateBotCount() throws InvalidBotCountException {
            int botCount = 0;
            for(Player player: players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount > 1){
                throw new InvalidBotCountException("The game has more than one bot players which is not allowed");
            }
        }

        private void validatePlayerCount() throws InvalidPlayerCountException {
            if(players.size() != dimension - 1){
                throw new InvalidPlayerCountException("No of players should be equal to one dimension - 1");
            }
        }

        private void validateUniqueSymbolsForAllPlayers(){

        }

        private void validate() throws InvalidBotCountException, InvalidPlayerCountException {
            validateBotCount();
            validatePlayerCount();
            validateUniqueSymbolsForAllPlayers();
        }

        public Game build() throws InvalidBotCountException, InvalidPlayerCountException {
            validate();
            return new Game(dimension, players, winningStrategies);
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerTurnIndex() {
        return nextPlayerTurnIndex;
    }

    public void setNextPlayerTurnIndex(int nextPlayerTurnIndex) {
        this.nextPlayerTurnIndex = nextPlayerTurnIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
}

/*
Why builder?
we would want to perform some validations before creating the game.
1. Board
2. Players

N*N board, how many players? => N - 1
 */
