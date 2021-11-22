package it.unibo.oop.lab.advanced;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ViewToFile implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";
    private static final String SEPARATOR = System.getProperty("path.separator");

    private DrawNumberViewObserver observer;
    private final DataOutputStream outStream;

    public ViewToFile() throws IOException {
        final File file = new File(System.getProperty("user.home") + SEPARATOR + "logFile.txt");
        file.createNewFile();
        this.outStream = new DataOutputStream(new FileOutputStream(file));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void start() {
        this.write("App started" + NEW_GAME);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void numberIncorrect() {
        this.write("Incorrect Number.. try again.");
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            this.write(res.getDescription());
            return;
        case YOU_WON:
            this.write(res.getDescription() + NEW_GAME);
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
        this.observer.resetGame();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void limitsReached() {
        this.write("You lost" + NEW_GAME);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void displayError(final String message) {
        this.write("Error: " + message);
    }

    private void write(final String logMess) {
        try {
            this.outStream.writeChars(logMess);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
