package com.ktech.utils;

import java.lang.reflect.ParameterizedType;

public class GenericUtils {
	
	/**
	 * Gets the parameterized type of generic super class at position 0
	 * @param <T>
	 * @param instance
	 * @return
	 */
	public static <T> Class<T> getSuperClassParameterizedType(Object instance) {
		return GenericUtils.getSuperClassParameterizedType(instance, 0);
	}
	
	/**
	 * Gets the parameterized type of generic super class at position (starts at 0). 
	 * @param <T>
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassParameterizedType(Object instance, Integer position) {
		return (Class<T>) ((ParameterizedType) instance.getClass().getGenericSuperclass()).getActualTypeArguments()[position];
	}
	
	public static <T> T getInstanceOf(Class<? extends T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		} 
	}

}
