package _9._3;

import _9._3.observer.Observer;

public class ShipLog implements Observer<Ship, ShipEvent> {
    @Override
    public void update(Ship who, ShipEvent what) {
        switch (what) {
            case SET_SAILS:
                System.out.println("Sails set.");
                break;
            case STRIKE_SAILS:
                System.out.println("Sails struck.");
                break;
            case LOAD_CANNONS:
                System.out.println("Cannons loaded.");
                break;
            case FIRE_CANNONS:
                System.out.println("Cannons fired.");
                break;
            case TURN_LEFT:
                System.out.println("Turned left. New heading " + who.getHeading() + " degrees.");
                break;
            case TURN_RIGHT:
                System.out.println("Turned right. New heading " + who.getHeading() + " degrees.");
                break;
        }
    }
}
