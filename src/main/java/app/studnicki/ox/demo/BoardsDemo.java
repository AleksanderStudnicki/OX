package app.studnicki.ox.demo;

import java.text.ParseException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BoardsDemo {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                int n = Integer.parseInt(args[0]);
                if (n > 2) {
                    printBoard(n);
                } else{
                    System.out.println("Number must be greater than 2");
                }
            } catch (Exception ex) {
                System.out.println("This is not a valid number");
            }
        } else {
            System.out.println("You must pass one absolute number as a argument to print a game board");
        }
    }

    private static void printBoard(int n) {
        char[][] test = new char[n][n];

        System.out.print("   |");
        IntStream.range(0, n).forEach(i -> {
            if (i < 10) {
                System.out.print(" " + i + " |");
            } else if (i < 100) {
                System.out.print(" " + i + "|");
            } else {
                System.out.print(i + "|");
            }
        });
        System.out.print("\n   ");
        IntStream.range(0, n).forEach(i -> System.out.print("----"));
        System.out.print("-");
        System.out.print("\n");


        for (char[] arr : test) {
            Arrays.fill(arr, ' ');
        }

        IntStream.range(0, n).forEach(i -> {
            char[] arr = test[i];

            if (i < 10) {
                System.out.print(" " + i + " ");
            } else if (i < 100) {
                System.out.print(" " + i + "");
            } else {
                System.out.print(i + "");
            }

            System.out.print("| ");
            for (char c : arr) {
                System.out.print(c + " | ");
            }
            System.out.print("\n   ");
            IntStream.range(0, n).forEach(j -> System.out.print("----"));
            System.out.print("-");
            System.out.print("\n");
        });

        System.out.println("\n\n");
    }
}
