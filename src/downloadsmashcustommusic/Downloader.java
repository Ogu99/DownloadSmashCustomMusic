package download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.Arrays;

/**
 * This class downloads the requested songs form their respective web sites.
 *
 * @author Ogu99
 * @version 1.0
 */
public class Downloader {

    /**
     * The minimum amount of arguments.
     */
    private static final int MIN_ARGS = 3;

    /**
     * The maximum amount of arguments.
     */
    private static final int MAX_ARGS = 6;

    /**
     * All valid types.
     */
    private static final String[] VALID_TYPES = { "brstm", "bcstm", "bfstm", "sw_bfstm"};

    /**
     * URL to the site. Picked a random date on which the site functions.
     */
    private static final String FIX_URL = "http://web.archive.org/web/20190616143649if_/https://www.smashcustommusic.com/";

    /**
     * The entered arguments.
     */
    private final String args[];

    /**
     * The id to start at.
     */
    private final int startId;

    /**
     * The id to end at.
     */
    private final int endId;

    /**
     * Creates a new downloader object.
     *
     * @param args - The needed arguments.
     */
    public Downloader(final String... args) {
        if (args.length < MIN_ARGS || args.length > MAX_ARGS) {
            throw new IllegalArgumentException("you entered an invalid amount of arguments! " +
                    "Please use between 3 and 6 arguments.");
        } else {
            final String[] givenArgs = Arrays.copyOfRange(args, 2, args.length );
            if (!validTypes(givenArgs)) {
                throw new IllegalArgumentException("you used invalid music file types! " +
                        "Please only use brstm, bcstm, bfstm, sw_bfstm");
            }

            this.args = givenArgs; //Copy the args into the array.
            this.startId = Integer.parseInt(args[0]);
            this.endId = Integer.parseInt(args[1]);
        }
    }

    /**
     * Checks if the given array contains the given string.
     *
     * @param s - The string to check.
     * @param array - The array to search in.
     * @return {@code true} if the array contains the given string otherwise {@code false}.
     */
    private boolean contains(final String s, final String[] array) {
        for (final String element : array) {
            if (element.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the entered types are valid.
     *
     * @param types - The given types for the music files.
     * @return {@code true} if the types are valid otherwise {@code false}.
     */
    private boolean validTypes(final String[] types) {
        for (final String s : types) {
            if (!contains(s, VALID_TYPES)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Downloads the wanted files into the given path of the folder. If the
     * folder does not exist it is created.
     *
     * @param outdir - The directory to save the files.
     */
    public void execute(final String outdir) {
        final File dir = new File(outdir); //Create the output directory if it does not exist.
        if (!dir.exists()) {
            dir.mkdirs();
        }

        /*
         * Now create the sub folders in the given root folder and download the
         * needed files.
         */
        for (final String arg : this.args) {
            final File subdir = new File(dir, arg);
            if (!subdir.exists()) {
                subdir.mkdirs();
            }

            //Download the files if they exist, otherwise show the error message.
            for (int i = this.startId; i <= this.endId; i++) {
                try {
                    InputStream inputStream = new URL(FIX_URL + arg + "/" + i).openStream();
                    Files.copy(inputStream, Paths.get(subdir.getPath() + "\\" + i + "." + arg), StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("Successfully downloaded the song id " + i);
                } catch(final IOException e ) {
                    System.err.println("File for the id " + i + " not found for the file type " + arg);
                }
            }
        }
    }
}