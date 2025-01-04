package service;

import java.util.Random;

public class UniqueIdGenerator {
    private static final Random rand = new Random();

    // Generate random integers in range 0 to 999

    public static int getNextId(){
        return rand.nextInt(1000);
    }
}
