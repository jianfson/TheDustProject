package ch7;

public class CarStatus {
	public int speed; 				// 汽车的速度
	public String direction;		// 汽车的行驶方向
	
	// 无参数的构造方法，不做任何初始化工作
	public CarStatus(){
	}
	// 初始化速度和形式方向两个变量
	public CarStatus(int p_speed, String p_direction){
		this.speed = p_speed;
		this.direction = p_direction;
	}
}
