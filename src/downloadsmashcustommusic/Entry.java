package download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.regex.Pattern;

/**
 * Main entry point of the application. The whole thing is a revamp of RedStoneMatt's code.
 * So he is credited as author in this class although he/she didn't actively contributed.
 *
 * @author Ogu99
 * @author RedStoneMatt
 * @version 1.0
 */
public final class Entry {

    /**
     * Used to read the user input.
     */
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));

    /**
     * The input pattern for the command.
     */
    private static final Pattern IN_PATTERN = Pattern.compile("(\\d+) (\\d+) ([a-z]+ ){0,3}([a-z]+)");

    /**
     * Minimum amount of arguments.
     */
    private static final int MIN_ARGS = 3;

    /**
     * Block object generation.
     */
    private Entry() {
        throw new IllegalAccessError("you cannot instantiate this class!");
    }

    /**
     * The main method for the application. Executes the code.
     *
     * @param args - Not used.
     */
    public static void main(String[] args) {
        System.out.println("Starting Smash Custom Music Downloader v1.0 \n(c) 2020, Ogu99 originally by (c) 2020 RedStoneMatt");
        do {
            System.out.println("Input format: startId endId music type(s) (up to 4 types: brstm, bcstm, bfstm, sw_bfst)!");
            final String input = read();

            if (IN_PATTERN.matcher(input).matches()) {
                try {
                    final Downloader downloader = new Downloader(input.split("\\s"));

                    System.out.println("Please enter the destination folder: ");

                    final String outdir = read();
                    downloader.execute(outdir);
                } catch (final IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            } else if (input.matches("quit")) {
                break;
            } else {
                System.err.println("You entered an invalid command!");
            }

        } while(true);
    }

    /**
     * Reads a line of text. A line is considered to be terminated by any one of a line feed ('\n'), a carriage return
     * ('\r'), or a carriage return followed immediately by a linefeed.
     *
     * @return a {@code String} containing the contents of the line, not including any line-termination characters, or
     *         {@code null} if the end of the stream has been reached
     */
    private static String read() {
        try {
            return IN.readLine();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
