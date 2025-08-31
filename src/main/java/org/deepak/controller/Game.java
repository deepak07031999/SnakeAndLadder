package org.deepak.controller;

import org.deepak.dto.Board;
import org.deepak.dto.Cell;
import org.deepak.dto.Dice;
import org.deepak.dto.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class Game {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);
    private static final int BOARD_SIZE = 10;
    private static final int WINNING_POSITION = BOARD_SIZE * BOARD_SIZE - 1;
    private static final String PLAYER_1_NAME = "Player 1";
    private static final String PLAYER_2_NAME = "Player 2";

    Board board;
    Dice dice;
    Deque<Player> players = new LinkedList<>();
    Player winner;

    public Game(){
        initializeGame();
    }

    private  void initializeGame(){
        this.board = new Board(BOARD_SIZE , 5, 4);
        this.dice = new Dice(1);
        this.winner = null;
        addPlayers();
    }

    private void addPlayers(){
        Player player1 = new Player(PLAYER_1_NAME, 0);
        Player player2 = new Player(PLAYER_2_NAME, 0);
        players.add(player1);
        players.add(player2);

    }

    public void startGame(){
        while (winner == null){
            Player playerTurn = players.poll();
            players.addLast(playerTurn);
            assert playerTurn != null;
            logger.info("player turn is:" + playerTurn.getName()
                    + " current position is: " + playerTurn.getPosition());
            int diceNumber = dice.rollDice();
            int playerNewPosition = playerTurn.getPosition() + diceNumber;
            if (playerNewPosition > WINNING_POSITION) {
                playerNewPosition = playerTurn.getPosition();
            } else {
                playerNewPosition = jump(playerNewPosition);
                playerTurn.setPosition(playerNewPosition);
            }
            logger.info("player turn is:" + playerTurn.getName() + " new Position is: " + playerNewPosition);
            if (playerNewPosition == WINNING_POSITION){
                winner = playerTurn;
            }
        }
        logger.info("WINNER IS:" + winner.getName());

    }

    private int jump(int playerPosition){
        Cell cell = board.getCells()[board.getCellRow(playerPosition)][board.getCellColumn(playerPosition)];
        if(cell.getJump()!=null && cell.getJump().getStart()==playerPosition){
            String jumpBy = (cell.getJump().getStart() < cell.getJump().getEnd())? "ladder" : "snake";
            logger.info("jump done by: " + jumpBy);
            return cell.getJump().getEnd();
        }
        return playerPosition;
    }
}
