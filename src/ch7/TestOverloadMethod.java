package ch7;

public class TestOverloadMethod {
	public static void main(String[] args) {
		CarOverload carOL = new CarOverload();
		carOL.raiseSpeed(9999);
		System.out.print("调用签名为raiseSpeed(int)的加速方法，现在车速为：");
		System.out.println(carOL.speed);
		carOL.speed = 0;			// 将车速清零
		carOL.raiseSpeed(9999,80);
		System.out.print("调用签名为raiseSpeed(int,int)的加速方法，现在车速为：");
		System.out.println(carOL.speed);
	}
}
