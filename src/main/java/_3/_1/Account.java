package _3._1;

public class Account {

	private Customer[] holders;
	private long balance;
	private String iban;

	public Account(String iban) {
		this.iban = iban;
	}

	public Customer[] getHolders() {
		return holders;
	}

	public void setHolders(Customer[] holders) {
		this.holders = holders;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getIban() {
		return iban;
	}

}
