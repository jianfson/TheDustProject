package ch7;

public class TestParameterOfMethod {
	public static void main(String[] args)  {
		Car myCar = new Car();				// 创建一个Car对象
		myCar.driveCar();					// 通过myCar调用driveCar()方法
		myCar.raiseSpeed (5);				// 通过myCar调用driveCar()方法

		System.out.print("现在车速为：");	// 向控制台输出myCar的属性
		System.out.print(myCar.speed); 
		System.out.print("，行驶方向为：");
		System.out.print(myCar.direction);
	}
}
