package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 100;
    private static final String CONFIG = "config.yml";
    private int min;
    private int max;
    private int attempts;
    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @throws IOException 
     * 
     */
    public DrawNumberApp() throws IOException {
        this.views = new ArrayList<>();
        this.views.add(new DrawNumberViewImpl());
        this.views.add(new ViewToFile());
        this.views.add(new ViewToStdout());
        for (final DrawNumberView i : this.views) {
            i.setObserver(this);
            i.start();
        }
        boolean success = false;
        try {
            this.getSettings(CONFIG);
            success = true;
        } catch (IOException e) {
            for (final DrawNumberView i : this.views) {
                i.displayError(e.getMessage() + "\nUsing default settings.");
            }
        }
        if (!success) {
            this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
        } else {
            this.model = new DrawNumberImpl(this.min, this.max, this.attempts);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView i : this.views) {
                i.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView i : this.views) {
                i.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView i : this.views) {
                i.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        try {
            new DrawNumberApp();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void getSettings(final String path) throws IOException {
        final InputStream in = ClassLoader.getSystemResourceAsStream(path);
        if (in == null) {
            final IOException e = new IOException("Unable to locate settings file.");
            throw e;
        }
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            while ((line = br.readLine()) != null) {
                final String[] setting = line.split(": ");
                if ("minimum".equals(setting[0])) {
                    this.min = Integer.parseInt(setting[1]);
                } else if ("maximum".equals(setting[0])) {
                    this.max = Integer.parseInt(setting[1]);
                } else if ("attempts".equals(setting[0])) {
                    this.attempts = Integer.parseInt(setting[1]);
                }
            }
        }
    }
}
