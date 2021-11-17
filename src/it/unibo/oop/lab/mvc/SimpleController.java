package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class SimpleController implements Controller {

    private String current;
    private final List<String> history;

    public SimpleController(final String s) {
        this.current = s;
        this.history = new ArrayList<>();
    }
    public SimpleController() {
        this(null);
    }

    @Override
    public void setCurrentString(final String s) {
        if (s == null) {
            final IllegalArgumentException e = new IllegalArgumentException(s);
            throw e;
        } else {
            this.current = s;
        }
    }

    @Override
    public String getCurrentString() {
        return this.current;
    }

    @Override
    public List<String> getHistory() {
        return new ArrayList<>(this.history);
    }

    @Override
    public void printCurrentString() {
        System.out.println(this.current);
        this.history.add(current);
        this.current = null;
    }

}
