package ch7;

public class TestCarConstructor {
	public static void main(String[] args){
		Car anotherCar = new Car("球状闪电号","银色");		// 调用构造方法
		System.out.print("汽车的名字为：");					// 输出汽车信息
		System.out.println(anotherCar.name);
		System.out.print("汽车的颜色为：");
		System.out.println(anotherCar.color);
	}
}
