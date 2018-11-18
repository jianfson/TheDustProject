package ch7;

public class SimpleClass{
	public int instanceVariable;
	public void showLocalVariable() {
		int localVariable = 55;
		instanceVariable = localVariable;
	}
	public void anotherMethod() {
		
		int localVariable = 55;
		instanceVariable = localVariable;
	}
}
