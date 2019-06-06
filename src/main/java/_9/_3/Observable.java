package _9._3;

import provided._9.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<Observer> observers = new ArrayList<>();
    private boolean changed = false;

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    protected void setChanged() {
        changed = true;
    }

    private void clearChanged() {
        changed = false;
    }

    public boolean isChanged() {
        return changed;
    }

    public void notifyObservers(ShipEvent what) {
        if (this.isChanged()) {
            for (Observer observer : observers) {
                observer.update(this, what);
            }
            this.clearChanged();
        }
    }
}
