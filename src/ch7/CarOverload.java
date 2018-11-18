package ch7;

public class CarOverload {
	public int speed; 						// 汽车的速度
	public void raiseSpeed(int p_speed) { 	// 直接提速
		int tempSpeed = speed + p_speed;
		speed = tempSpeed;
	}
	public void raiseSpeed(int p_speed,
			int limited) {					// limited 是最高限制速度
		int tempSpeed = speed + p_speed;
		if (tempSpeed < limited) {			// 如果新的速度低于最高限制速度
			speed = tempSpeed;				// 则正常提速
		} else {
			speed = limited;				// 否则将速度设置为最高限制速度
		}
	}
}
