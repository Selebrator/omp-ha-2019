package _9._3.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<Who extends Observable<Who, What>, What> {
    private List<Observer<Who, What>> observers = new ArrayList<>();
    private boolean changed = false;

    public void addObserver(Observer<Who, What> o) {
        this.observers.add(o);
    }

    public void removeObserver(Observer<Who, What> o) {
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

    public void notifyObservers(What what) {
        if (this.isChanged()) {
            for (Observer<Who, What> observer : observers) {
                observer.update((Who) this, what);
            }
            this.clearChanged();
        }
    }
}
