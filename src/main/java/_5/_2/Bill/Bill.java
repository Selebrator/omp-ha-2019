package _5._2.Bill;

import _5._2.CarComponents.*;

public class Bill {
    private Billtem[] items;

    public Bill(Billtem[] items) {
        this.items = items;
    }

    public double getTotalPrice() {
        double price = 0;
        for (Billtem i : items) {
            price += i.getPrice();
        }

        return price;
    }

    public String toString() {
        String string = "";
        for (Billtem i : items) {
            string += i.toString() + "\n";
        }
        double price = this.getTotalPrice();
        string += "Total: " + price;
        return string;
    }


    public static class Billtem {
        private double price;
        private CarComponent item;

        public Billtem(CarComponent item, double price) {
            this.item = item;
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public String toString() {
            return item.getName() + getComponents() + ": " + price;
        }


        public String getComponents() {
            CarComponent[] components = item.getComponents();
            String names = "";

            if (components != null) {
                names += "(";
                for (CarComponent i : components) {
                    names += i.getName() + " ";
                }
                names += ")";
            }
            return names;
        }
    }
}