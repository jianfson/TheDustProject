package ch7;

public class TestMultiParameters {
	public static void main(String[] args) {
		Car myCar = new Car(); 				// 创建一个Car对象
		int nextSpeed = 155;
		myCar.setSpeedAndDirection(nextSpeed, "东方");
											// 通过myCar调用driveCar()方法

		System.out.print("现在车速为："); 	// 向控制台输出myCar的属性
		System.out.print(myCar.speed);
		System.out.print("，行驶方向为：");
		System.out.print(myCar.direction);
	}
}
