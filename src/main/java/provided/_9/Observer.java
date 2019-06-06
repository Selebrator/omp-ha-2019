package provided._9;

import _9._3.Observable;
import _9._3.ShipEvent;

public interface Observer {
	
	void update(Observable who, ShipEvent what);

}
