package cs;

public abstract class Shape
{
	//抽象方法作为模板
	//使能使父类对象调用子类重写方法（方法名相同，优先调用子类重写方法）
	public abstract String getType();
	public abstract double getC();
}