package ch7;

public class TestThis {
	public static void main(String[] args) {
		SimpleClassToShowThis simple = new SimpleClassToShowThis ();// 创建一个SimpleClass对象
		simple.test();									// 调用test()方法
		System.out.print ("simple对象中a的值为：");		// 输出实例变量a
		System.out.println(simple.a);
	}
}
