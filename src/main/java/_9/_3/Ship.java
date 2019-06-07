package _9._3;

public class Ship extends Observable {
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

    public void turnLeft() {
        if (heading - 90 < 0) {
            heading = 360 + ((heading - 90) % 360);
        } else {
            heading = (heading - 90) % 360;
        }
        this.setChanged();
        notifyObservers(ShipEvent.TURN_LEFT);
    }

    public void turnRight() {
        heading = (heading + 90) % 360;
        this.setChanged();
        notifyObservers(ShipEvent.TURN_RIGHT);
    }
}
