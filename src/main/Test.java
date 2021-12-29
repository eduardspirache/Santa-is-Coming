package main;

import checker.Checker;

import java.io.IOException;
import java.util.Scanner;

import static common.Constants.INPUT_PATH;
import static common.Constants.FILE_EXTENSION;
import static common.Constants.OUTPUT_PATH;

import static main.Main.action;

public final class Test {

    private Test() {
        // Constructor for checkstyle
    }

    /**
     * Main
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        Scanner myObj = new Scanner(System.in);
        int i = myObj.nextInt();
        String inputPath = INPUT_PATH + i + FILE_EXTENSION;
        String outputPath = OUTPUT_PATH + i + FILE_EXTENSION;
        action(inputPath, outputPath);

        Checker.calculateScore(i);
    }
}
