package controllers;

import exceptions.InvalidBotCountException;
import exceptions.InvalidPlayerCountException;
import models.Game;
import models.GameState;
import models.Player;
import strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimension,
                          List<Player> players,
                          List<WinningStrategy> winningStrategies) throws InvalidBotCountException, InvalidPlayerCountException {
        //same basic validations in the controller
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void unDo(Game game){
        //
    }





}
