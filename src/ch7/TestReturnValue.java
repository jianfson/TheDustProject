package ch7;

public class TestReturnValue {
	public static void main(String[] args) {
		Car myCar = new Car(); 					// 创建一个Car对象
		myCar.raiseSpeed(70);					// 加速70
		boolean isOverspeed = myCar.isOverspeed();	// 调用方法，得到返回值
		if(isOverspeed){						// 根据返回值向控制台输出结果
			System.out.println("汽车超速行驶中");
		}else{
			System.out.println("汽车没有超速");
		}
		
		myCar.raiseSpeed(70);					// 再次加速70
		isOverspeed = myCar.isOverspeed();		// 调用方法，得到返回值
		if(isOverspeed){						// 根据返回值向控制台输出结果
			System.out.println("汽车超速行驶中");
		}else{
			System.out.println("汽车没有超速");
		}
	}
}
