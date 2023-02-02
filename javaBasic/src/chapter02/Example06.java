package chapter02;

import chapter02.entity.BusDriver;
import chapter02.entity.Developer;
import chapter02.entity.Police;

public class Example06 {

	public static void main(String[] args) {
		
		Developer developer = new Developer("John", null, null);
		Police police = new Police();
		BusDriver busDriver = new BusDriver();
		
		// 참조형 변수에서 데이터가 미정인 상태일 때 null
		String str = null;
		//developer = null;
		
		developer.languageSkill = "";
//		System.out.println(developer.name);
		
//		developer.eatBreakfast();
//		police.eatBreakfast();
//		busDriver.eatBreakfast();
	}

}
