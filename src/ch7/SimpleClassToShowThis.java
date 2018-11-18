package ch7;

public class SimpleClassToShowThis{
	public int a;						// 实例变量a
	public void test() {				// 方法
		int a = 50;							// 局部变量a
		this.a = a + 5;							// 给变量a赋值
					// 输出a的值
	}
	public int countArea(int circumference) {
		// 根据正方形的周长，计算并返回正方形的面积
		int sideLength = circumference / 4;		// 计算正方形边长
		int area = sideLength * sideLength;		// 计算正方形的面积
		return area;							// 返回正方形的面积
	}

}
