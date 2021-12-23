package main;

import checker.Checker;

import java.io.IOError;
import java.io.IOException;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) {

        Checker.calculateScore();
    }

    public static void action(final String filePath1, final String filePath2) throws IOException {

    }
}
