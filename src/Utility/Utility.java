package Utility;

import java.util.ArrayList;
import java.util.Random;

public class Utility {
    public ArrayList getRandomNonRepeatingIntegers(int size, int min,
                                                   int max) {
        ArrayList numbers = new ArrayList();
        Random random = new Random();
        while (numbers.size() < size) {
            //Get Random numbers between range
            int randomNumber = random.nextInt((max - min) + 1) + min;
            //Check for duplicate values
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }

        return numbers;
    }

}
