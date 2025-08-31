package org.deepak.dto;

import java.util.concurrent.ThreadLocalRandom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dice {
    private static int min =1;
    private static int max = 6;
    private int diceCount;

    public int rollDice(){
        int totalSum=0;
        for(int i=0;i<diceCount;i++){
            totalSum+=ThreadLocalRandom.current().nextInt(min,max+1);
        }
        return totalSum;
    }

}
