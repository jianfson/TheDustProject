package ch7;

public class TestOvertakeCar {
	public static void main(String[] args){
		Car carFront = new Car();
		Car carBehand = new Car();
		carFront.raiseSpeed(80);	// carFront是跑在前面的车，所以速度大一些
		carFront.direction = "东方";	// 让carFront向“东方”跑
		carBehand.raiseSpeed(50);	// carBehand是跑在后面的车，所以速度小一些
		carBehand.direction = "东方";			// 让carBehand向“西方”跑
		System.out.print("carFront的速度为：");	// 查看两车的速度和方向
		System.out.print(carFront.speed);		
		System.out.print("。carFront的行驶方向为：");
		System.out.println(carFront.direction);
		System.out.print("carBehand的速度为：");
		System.out.print(carBehand.speed);
		System.out.print("。carBehand的行驶方向为：");
		System.out.println(carBehand.direction);
		
		System.out.println("carBehand马上要调用overtakeCar()方法了！");
		// carBehand调用overtakeCar()方法，参数是carFront，即carBehand将超
		// 车，被超的是carFront
		carBehand.overtakeCar(carFront);
		System.out.print("carFront的速度为：");	// 再次查看两车的速度和方向
		System.out.print(carFront.speed);		
		System.out.print("。carFront的行驶方向为：");
		System.out.println(carFront.direction);
		System.out.print("carBehand的速度为：");
		System.out.print(carBehand.speed);
		System.out.print("。carBehand的行驶方向为：");
		System.out.println(carBehand.direction);
	}
}
