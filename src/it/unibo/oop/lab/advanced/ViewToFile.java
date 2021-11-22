package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class ViewToFile implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";


    private DrawNumberViewObserver observer;
    private final PrintStream pStream;

    public ViewToFile() throws IOException {
        final File file = new File("logFile.txt");
        if (!file.createNewFile()) {
            file.delete();
            file.createNewFile();
        }
        this.pStream = new PrintStream(file.getCanonicalPath());
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
        this.pStream.print(logMess);
    }
}
