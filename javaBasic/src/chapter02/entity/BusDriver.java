package chapter02.entity;

public class BusDriver extends People {
	// 버스 차종
		String vehicleModel;
		// 지역
		String busNumber;
		
		public BusDriver() {
			super("John doe", "man");
		}

}
