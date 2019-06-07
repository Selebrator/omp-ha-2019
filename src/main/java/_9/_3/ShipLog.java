package _9._3;

import provided._9.Observer;

public class ShipLog implements Observer {
    private int heading = 0;
    @Override
    public void update(Observable who, ShipEvent what) {
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
                if (heading - 90 < 0) {
                    heading = 360 + ((heading - 90) % 360);
                } else {
                    heading = (heading - 90) % 360;
                }
                System.out.println("Turned left. New heading " + heading + " degrees.");
                break;
            case TURN_RIGHT:
                heading = (heading + 90) % 360;
                System.out.println("Turned right. New heading " + heading + " degrees.");
                break;
        }
    }
}
