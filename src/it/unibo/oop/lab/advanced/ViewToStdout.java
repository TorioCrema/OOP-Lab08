package it.unibo.oop.lab.advanced;

public class ViewToStdout implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";

    private DrawNumberViewObserver observer;

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
        this.print("New match started");
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void numberIncorrect() {
        this.print("Incorrect Number.. try again");
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            this.print(res.getDescription());
            return;
        case YOU_WON:
            this.print(res.getDescription() + NEW_GAME);
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
        this.print("You lost" + NEW_GAME);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void displayError(final String message) {
        System.err.println(message);
    }

    /**
     * Prints a {@link String} to standard output.
     * @param message the {@link String} to be printed.
     */
    private void print(final String message) {
        System.out.println(message);
    }

}
