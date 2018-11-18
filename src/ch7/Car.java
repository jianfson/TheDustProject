package ch7;

public class Car {
	int speed; 					// 存储汽车的速度
	String color;				// 汽车颜色编号
	String name;				// 汽车的名字
	String direction; 			// 汽车的行驶方向
	boolean isTrafficAdmin;

	// 构造方法，用来给name和color属性赋初始值
	public Car() {
		this("Java护航者", "咖啡豆色", false);
	}
	// 构造方法，用来初始化汽车的名字和颜色
	public Car(String p_name, String p_color) {
		this(p_name, p_color, false);
	}
	// 构造方法，用来初始化汽车的名字和颜色
	public Car(String p_name, String p_color, boolean p_trafficAdmin) {
		this.name = p_name;
		this.color = p_color;
		this.isTrafficAdmin = p_trafficAdmin;
	}
	// 这是Car类中的一个方法，功能是启动汽车，让它以50的速度，向“南方”行驶
	public void driveCar() {
		speed = 50; 			// 将速度设置为55
		direction = "南方"; 		// 将方向设置为“南方”
	}
	// 这是新增加的汽车加速方法，这个方法中有一个int参数
	public void raiseSpeed(int p_speed) {
		if (p_speed < 0) { 				// 判断参数p_speed的值是否符合要求
			System.out.println("p_speed的值小于0，raiseSpeed()方法将结束");
			return;
		}
		int currentSpeed = speed + p_speed; 	// 计算出当前速度
		speed = currentSpeed; 					// 将当前速度赋值给speed属性
	}
	// 这个方法是与 raiseSpeed(int)重载的
	public void raiseSpeed(byte p_speed) {
		if (p_speed < 0) {				// 判断参数p_speed的值是否符合要求
			System.out.println("p_speed的值小于0，raiseSpeed()方法将结束");
			return;
		}
		int currentSpeed = speed + p_speed; 	// 计算出当前速度
		speed = currentSpeed; 					// 将当前速度赋值给speed属性
	}
	// 设置汽车的速度和方向
	public void setSpeedAndDirection(int p_speed, String p_direction) {
		speed = p_speed; 						// 将速度设置为新的速度
		direction = p_direction;				// 将方向设置为新的方向
	}
	// 判断一辆汽车是否超速（我们规定车速超过80为超速）。超速则返回true，否则返回
	// false
	public boolean isOverspeed() {
		if (speed > 80) { 						// 如果速度超过80
			return true; 						// 表示超速了，应该返回true
			// return是一个方法的结束，方法执行完return后将直接结束，不再向下执行
		} else {
			return false; 				// 否则，表示没有超速，应该返回false
		}
	}
	// 超车方法。本车将通过此方法超越anotherCar，所以将速度设置为anotherCar的速
	// 度然后再加5，将方向与anotherCar设置成一样
	public void overtakeCar(Car anotherCar) {
		speed = anotherCar.speed + 5; 
								// 将车的速度设置为anotherCar的速度然后再加5
		direction = anotherCar.direction;
								// 将方向设置成与anotherCar一样
	}
	// 允许TrafficAdmin设置另一辆车的名字，参数anotherCar为另一辆车，newName
	// 为车的新名字
	public void setNameForAnotherCar(Car anotherCar, String newName) {
		if (!isTrafficAdmin) {
				 	// 如果当前的车不是TrafficAdmin，则打印出错信息并结束本方法
			System.out.println("你不是交通管理员，无权调用此方法！");
			return;
		}
		anotherCar.name = newName;
					// 如果当前的车是TrafficAdmin，则允许修改另一辆车的名字
	}
	// 允许TrafficAdmin将另一辆车的速度设置为0
	public void stopAnotherCar(Car anotherCar) {
		if (!isTrafficAdmin) { 
					// 如果当前的车不是TrafficAdmin，则打印出错信息并结束本方法
			System.out.println("你不是交通管理员，无权调用此方法！");
			return;
		}
		anotherCar.speed = 0;
					// 如果当前的车是TrafficAdmin，则允许它让另一辆车停下来
	}
	// 允许TrafficAdmin得到另一辆车的运行状态，注意这里的返回值类型是CarStatus
	// 而不是一个简单数据类型
	public CarStatus getAnotherCarStatus(Car anotherCar) {
		if (!isTrafficAdmin) { 
					// 如果当前的车不是TrafficAdmin，则打印出错信息并结束本方法
			anotherCar.speed = 0;
		}
		// 根据另一辆车的speed和direction创建一个CarStatus对象并返回
		CarStatus status = new CarStatus(anotherCar.speed, anotherCar. 
		direction);
		return status;
	}
	// 判断一辆车的状态是不是合法（现在我们只看一辆车是不是超速了） 
	public boolean isLegalCarStatus(CarStatus status) {
		if (status.speed <= 80) {
			// 判断汽车是否超速， 其实在这里仅仅用到了CarStatus里的speed属性
			return true;
		} else {
			return false;
		}
	}
	// 将车的速度改变为一个非负的值
	public void setSpeed(int p_speed){
		if(p_speed < 0){
				// 如果新的速度是小于0的，打印出错信息并使用return语句退出方法
			System.out.println("汽车速度不能为负值，方法将退出。");
			return;
		}
		this.speed = p_speed;	// 如果速度是非负数，则将汽车速度设置为新的值
	}
	public void printCarRunningMessage(){
		System.out.print("车名为“" + this.name +"”的汽车行驶速度为：");
		System.out.print(this.speed);
		System.out.println("，汽车行驶方向为：“" + this.direction + "”");
	}
}
