package org.deepak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
@AllArgsConstructor
public class Board {

    private Cell[][] cells;
    public Board(int boardSize, int numberOfSnake, int numberOfLadders){
        initializeCells(boardSize);
        addSnakesLadders(numberOfSnake, numberOfLadders);
    }

    private void initializeCells(int boardSize){
        this.cells = new Cell[boardSize][boardSize];
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                this.cells[i][j]=new Cell();
            }
        }
    }
    void addSnakesLadders(int numberOfSnake, int numberOfLadders){
        while(numberOfSnake > 0){
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length -1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length -1);
            if(snakeHead<=snakeTail) continue;
            Jump jump = new Jump(snakeHead,snakeTail);
            cells[getCellRow(snakeHead)][getCellColumn(snakeHead)].setJump(jump);
            numberOfSnake--;
        }
        while(numberOfLadders>0){
            int ladderStart = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length -1);
            int ladderEnd = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length -1);
            if(ladderStart>=ladderEnd) continue;
            Jump jump = new Jump(ladderStart,ladderEnd);
            cells[getCellRow(ladderStart)][getCellColumn(ladderStart)].setJump(jump);
            numberOfLadders--;
        }
    }
    public int getCellRow(int playerPosition){
        return playerPosition / cells.length;
    }
    public int getCellColumn(int playerPosition){
        return playerPosition % cells.length;
    }
}

