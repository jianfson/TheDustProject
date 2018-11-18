package ch7;

import java.util.ArrayList;

public class TestCar {
	public static void main(String[] args)  {
		double [] Data = {1.5,5.4,5.5,5.6,5.7,5.8,5.9,5.9,6.0,15};
		int i = 0;
		//Car myCar = new Car();				// 创建一个Car对象
		//myCar.driveCar();					// 通过myCar调用driveCar()方法
		ArrayList<Double> dataArrayList = new ArrayList<Double>();
		for(;i<10;i++) {
		dataArrayList.add(Data[i]);
		}
		Grubbs Grb;
		Grb = new Grubbs(dataArrayList);
		dataArrayList = Grb.calc();
		System.out.print(dataArrayList.size()); 
		//System.out.print("现在车速为：");	// 向控制台输出myCar的属性
		//System.out.print(myCar.speed); 
		//System.out.print("，行驶方向为：");
		//System.out.print(myCar.direction);
	}
}
