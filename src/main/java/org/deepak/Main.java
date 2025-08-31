package org.deepak;

import org.deepak.controller.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Starting Snake And Ladder game");
        Game game = new Game();
        game.startGame();
    }

}