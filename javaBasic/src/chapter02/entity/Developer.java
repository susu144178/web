package chapter02.entity;

public class Developer extends People {
	public String languageSkill;
	public String company;
	public String developPosition;
	
	public Developer(String name, String gender, String company) {
		// super() : 슈퍼 클래스의 생성자
		super(name, gender);
		this.company = company;
	}
	
	void eatBreakfast() {
		System.out.println(super.name + "는 10시에 아침을 먹습니다.");
	}

}
