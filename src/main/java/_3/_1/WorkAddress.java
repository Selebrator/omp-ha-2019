package _3._1;

public class WorkAddress extends Address {
	private String companyName;
	WorkAddress(String companyName){
		super();
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


}
