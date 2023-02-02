package chapter02.entity;

public class Police extends People {
	// 관할
	String jurisdiction;
	// 직책
	String officePosition;

	public Police() {
		super("John doe", "man");
	}

}
