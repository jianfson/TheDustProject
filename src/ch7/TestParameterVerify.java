package ch7;

public class TestParameterVerify {
	public static void main(String[] args) {
		Car myCar = new Car();
		myCar.raiseSpeed(55); // 传递一个合理的参数值
		System.out.print("汽车现在的速度是：");
		System.out.println(myCar.speed);
		myCar.raiseSpeed(-3); // 传递一个不合理的参数值
		System.out.print("汽车现在的速度是：");
		System.out.println(myCar.speed);
	}
}
