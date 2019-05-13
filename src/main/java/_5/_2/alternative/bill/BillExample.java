package _5._2.alternative.bill;

import _5._2.alternative.car.Motor;
import _5._2.alternative.car.Seat;
import _5._2.alternative.car.Wheel;

public class BillExample {
	public static void main(String[] args) {
		Bill bill = new Bill();
		bill.add(new Motor("Rolls Royce (Motor)"), 100000.0);
		bill.add(new Seat(), 2000.0);
		bill.add(new Wheel(), 1000.0);
		bill.add(new Wheel(), 1000.0);
		bill.add(new Wheel(), 1000.0);
		bill.add(new Wheel(), 1000.0);
		System.out.println(bill);
	}
}
