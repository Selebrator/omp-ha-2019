package _9._3;

import provided._9.Captain;

public class DrunkenPirate extends Captain {

    public DrunkenPirate(Ship ship) {
        super(ship);
    }

    @Override
    public void commandShip() {
        ShipEvent event = ShipEvent.getRandomShipEvent();
        System.out.println(event);

        switch (event) {
            case SET_SAILS:
                this.ship.setSails();
                break;
            case STRIKE_SAILS:
                this.ship.strikeSails();
                break;
            case LOAD_CANNONS:
                this.ship.loadCannons();
                break;
            case FIRE_CANNONS:
                this.ship.fireCannons();
                break;
            case TURN_LEFT:
                this.ship.turnLeft();
                break;
            case TURN_RIGHT:
                this.ship.turnRight();
                break;
        }
    }
}
