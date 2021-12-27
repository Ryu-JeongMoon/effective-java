package chap08.item52;

import java.util.List;

public class Overriding {

    public static void main(String[] args) {
        List<Wine> wines = List.of(new Wine(), new SparklingWine(), new Champagne());

        for (Wine wine : wines) {
            System.out.println("wine = " + wine.name());
        }
    }
}
