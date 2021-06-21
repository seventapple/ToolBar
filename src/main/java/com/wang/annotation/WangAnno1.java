package com.wang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注解使用范围指定,可复数指定
@Target(value = { ElementType.TYPE, ElementType.METHOD })
//注解保留策略,描述注解生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface WangAnno1 {
	// 注解的字段(类型 名称()),唯一时建议名为value(可省略)
	String name();

	int age() default 0;
}
