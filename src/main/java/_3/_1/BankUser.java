package _3._1;

public class BankUser {
	private String name;
	private HomeAddress homeAddress;
	private WorkAddress workAddress;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HomeAddress getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(HomeAddress homeAddress) {
		this.homeAddress = homeAddress;
	}

	public WorkAddress getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(WorkAddress workAddress) {
		this.workAddress = workAddress;
	}
}
