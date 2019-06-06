package _9._3;

import _9._3.observer.Observable;

public class Ship extends Observable<Ship, ShipEvent> {
    private boolean sailsSet = false;
    private boolean cannonsLoaded = false;
    private int heading = 0;

    public void setSails() {
        if (!sailsSet) {
            sailsSet = true;
            this.setChanged();
            notifyObservers(ShipEvent.SET_SAILS);
        }
    }

    public boolean isSailsSet() {
        return this.sailsSet;
    }

    public void strikeSails() {
        if (sailsSet) {
            sailsSet = false;
            this.setChanged();
            notifyObservers(ShipEvent.STRIKE_SAILS);
        }
    }

    public void loadCannons() {
        if (!cannonsLoaded) {
            cannonsLoaded = true;
            this.setChanged();
            notifyObservers(ShipEvent.LOAD_CANNONS);
        }
    }

    public void fireCannons() {
        if (cannonsLoaded) {
            cannonsLoaded = false;
            this.setChanged();
            notifyObservers(ShipEvent.FIRE_CANNONS);
        }
    }

    public boolean isCannonsLoaded() {
        return this.cannonsLoaded;
    }

    public void turnLeft() {
        heading = (heading - 90) % 180;
        this.setChanged();
        notifyObservers(ShipEvent.TURN_LEFT);
    }

    public void turnRight() {
        heading = (heading + 90) % 360;
        this.setChanged();
        notifyObservers(ShipEvent.TURN_RIGHT);
    }

    public int getHeading() {
        return this.heading;
    }
}
