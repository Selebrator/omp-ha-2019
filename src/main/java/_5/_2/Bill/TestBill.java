package _5._2.Bill;

import _5._2.CarComponents.*;

public class TestBill {
    public static void main(String[] args) {
        Motor m = new Motor("Motor",null);
        Car c = new Car("Rolls Royce",new CarComponent[] {m});
        Seat s = new Seat("Seat",null);
        Wheel w1 = new Wheel("Wheel",null);
        Wheel w2 = new Wheel("Wheel",null);
        Wheel w3 = new Wheel("Wheel",null);
        Wheel w4 = new Wheel("Wheel",null);

        Bill.Billtem[] billtems = new Bill.Billtem[]{new Bill.Billtem(c, 100000.0), new Bill.Billtem(s,2000.0), new Bill.Billtem(w1, 1000.0), new Bill.Billtem(w2, 1000.0), new Bill.Billtem(w3, 1000.0), new Bill.Billtem(w4, 1000.0)};
        Bill bill = new Bill(billtems);

        String billprice = bill.toString();
        System.out.println(billprice);
    }
}
