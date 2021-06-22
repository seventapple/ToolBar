package com.wang.annotation.orm;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Iterator;

/*
 * ORM(Object Relationship Mapping)
 * 类与表结构对应
 * 属性和字段对应
 *对象与记录对应
 */
public class Test {
	public static void main(String[] args) {
		try {
			Class clazz = Class.forName("com.wang.annotation.orm.UserBean");
			// 获取全部类的注解
			Annotation[] annos = clazz.getAnnotations();
			for (Annotation annotation : annos) {
				System.out.println(annotation);
			}
			// 获取指定类的注解
			TableAnno anno = (TableAnno) clazz.getAnnotation(TableAnno.class);
			System.out.println(anno);
			System.out.println(anno.value());
			// 获取字段的注解
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Annotation[] fAnnos = field.getAnnotations();
				for (Annotation fa : fAnnos) {
					System.out.println(fa);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
