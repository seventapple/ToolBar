package com.wang.io;

//装饰设计模式
//基础业务类Voice
//装饰类Amplifier
//类与类关系:
//1.依赖:类的形参|局部变量(体现在某个类的方法使用另一个类作为参数)
//2.关联:属性,根据强度分为
//         聚合(是“has-a”关系),整体与部分,生命周期不一致,人与手
//         组合(是“contains-a”关系),整体与部分,生命周期一致,人与大脑
//3.继承:父子类
//4.实现:接口与实现类
public class Demo09 {
	public static void main(String[] args) {
		Voice base = new Voice();
		Amplifier amplifier = new Amplifier(base);
		base.say();
		amplifier.say();
	}
}
