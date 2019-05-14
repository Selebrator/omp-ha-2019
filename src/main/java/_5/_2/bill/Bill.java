package _5._2.bill;

import _5._2.car.CarComponent;

import java.util.LinkedList;
import java.util.List;

/*
 * final damit keiner ein BillItem nach draußen gibt
 * und es später wieder als Argument nimmt.
 */
public final class Bill {
	private List<BillItem> items = new LinkedList<>();

	public double getTotalPrice() {
		double sum = 0.0;
		for(BillItem item : this.items) {
			sum += item.getPrice();
		}
		return sum;
	}

	public boolean add(CarComponent item, double price) {
		return this.items.add(new BillItem(item, price));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		double total = 0.0;
		for(BillItem item : this.items) {
			sb.append(item.toString()).append('\n');
			total += item.getPrice();
		}
		sb.append("Total: ").append(total);
		return sb.toString();
	}

	//inner class und private constructor damit eine Instanz nur als Teil einer Rechnung existieren kann.
	public class BillItem {
		private final CarComponent item;
		private final double price;

		private BillItem(CarComponent item, double price) {
			//assert item != null;
			this.item = item;
			this.price = price;
		}

		public double getPrice() {
			return this.price;
		}

		@Override
		public String toString() {
			if(this.item.getComponents().isEmpty()) {
				return this.item.getName() + ": " + this.price;
			}
			return this.item.getName() + this.item.getComponents() + ": " + this.price;
		}
	}
}
