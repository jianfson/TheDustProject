package ch7;

public class CarOverLoadHard {
	public double speed;

	public void raiseSpeed(double p_speed) {	// 提速方法，参数类型为double
		System.out.println("签名为raiseSpeed(double)的方法被调用了");
		double tempSpeed = speed + p_speed;	// 计算新的速度
		speed = tempSpeed;						// 增加速度的值
	}
	public void raiseSpeed(int p_speed){		// 提速方法，参数类型为int
		System.out.println("签名为raiseSpeed(int)的方法被调用了");
		double tempSpeed = speed + p_speed; 	// 计算新的速度
		speed = tempSpeed; 						// 增加速度的值
	}
}
