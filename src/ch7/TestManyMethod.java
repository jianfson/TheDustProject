package ch7;

public class TestManyMethod {
	public static void main(String[] args) {
		Car normalCar = new Car("普通车","白色");
					// 首先是一辆普通车，以55的速度向“菜市场”行驶
		normalCar.setSpeedAndDirection(55, "菜市场");
		normalCar.printCarRunningMessage();		// 输出汽车信息
		Car crazyCar = new Car("疯狂赛车","黑色");
					// 然后是一辆疯狂赛车，开始很老实，以35的速度向“赛车场”行使
		crazyCar.setSpeedAndDirection(65, "赛车场");
		crazyCar.printCarRunningMessage();		// 输出汽车信息
		crazyCar.overtakeCar(normalCar);// 疯狂赛车超车了，方向也变成了“菜市场”
		crazyCar.printCarRunningMessage();		// 输出汽车信息
		crazyCar.raiseSpeed((byte)50);	 // 使用byte做参数的raiseSpeed
		crazyCar.printCarRunningMessage();		// 输出汽车信息
		crazyCar.raiseSpeed((byte)55);	 // 使用int做参数的raiseSpeed
		Car trafficAdmin = new Car("汽车管理员","红色",true);// 汽车管理员登场
		trafficAdmin.setSpeedAndDirection(55, "交通管理中心");
		CarStatus normalStatus = 
			trafficAdmin.getAnotherCarStatus(normalCar);
									// 得到normalCar的状态
		boolean isLegal = 
			trafficAdmin.isLegalCarStatus(normalStatus);
									// 根据normalStatus判断车的状态是否合法
		if(isLegal){				// 如果合法就为它改名字
			trafficAdmin.setNameForAnotherCar(normalCar, "驾车典范");
		}
		normalCar.printCarRunningMessage();				// 输出汽车信息
		
		CarStatus crazyStatus =
			trafficAdmin.getAnotherCarStatus(crazyCar);
									// 得到crazyCar的状态
		isLegal = trafficAdmin.isLegalCarStatus(crazyStatus);
									// 根据crazyStatus判断车的状态是否合法
		crazyCar.stopAnotherCar(trafficAdmin);
									// crazyCar尝试让交通管理车停下，失败
		if(!isLegal){				// 若状态不合法则改它的名字并让它停下
			trafficAdmin.setNameForAnotherCar(crazyCar, "疯狂超速车");
			trafficAdmin.stopAnotherCar(crazyCar);
			crazyCar.printCarRunningMessage();	// 输出汽车信息
		}
		normalCar.setSpeed(0);					// 普通车到了“菜市场”，停车
		normalCar.printCarRunningMessage();	// 输出汽车信息
	}
}
