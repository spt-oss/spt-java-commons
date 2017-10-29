/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spt.java.lang;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.aop.support.AopUtils;
import org.springframework.util.ReflectionUtils;

import lombok.NonNull;
import spt.java.util.XLog;

/**
 * Custom {@link Class}
 */
public class XClass {
	
	/**
	 * Constructor
	 */
	protected XClass() {
		
		/* NOP */
	}
	
	/**
	 * Get {@link Constructor}
	 * 
	 * @param clazz class
	 * @param paramClasses parameter classes
	 * @param <T> constructor type
	 * @return {@link Constructor}
	 * @throws IllegalStateException if failed to get
	 */
	public static <T> Constructor<T> getConstructor(@NonNull Class<T> clazz, Class<?>... paramClasses)
		throws IllegalStateException {
		
		try {
			
			return clazz.getConstructor(paramClasses);
		}
		catch (NoSuchMethodException e) {
			
			throw new IllegalStateException(XLog.of("Constructor not found").value(clazz, paramClasses));
		}
	}
	
	/**
	 * Get accessible {@link Field}
	 * 
	 * @param clazz class
	 * @param name name
	 * @return {@link Field}
	 * @throws IllegalStateException if failed to get
	 */
	public static Field getField(Class<?> clazz, String name) throws IllegalStateException {
		
		Field field = ReflectionUtils.findField(clazz, name);
		
		if (field == null) {
			
			throw new IllegalStateException(XLog.of("Field not found").value(clazz, name));
		}
		
		ReflectionUtils.makeAccessible(field);
		
		return field;
	}
	
	/**
	 * Get null {@link Field}s
	 * 
	 * @param object object
	 * @return null {@link Field}s
	 */
	public static Collection<Field> getNullFields(@NonNull Object object) {
		
		List<Field> fields = new ArrayList<>();
		
		for (Field field : object.getClass().getDeclaredFields()) {
			
			ReflectionUtils.makeAccessible(field);
			
			if (ReflectionUtils.getField(field, object) == null) {
				
				fields.add(field);
			}
		}
		
		return fields;
	}
	
	/**
	 * Get non-null {@link Field}s
	 * 
	 * @param object object
	 * @return non-null {@link Field}s
	 */
	public static Collection<Field> getNonNullFields(@NonNull Object object) {
		
		List<Field> fields = new ArrayList<>();
		
		for (Field field : object.getClass().getDeclaredFields()) {
			
			ReflectionUtils.makeAccessible(field);
			
			if (ReflectionUtils.getField(field, object) != null) {
				
				fields.add(field);
			}
		}
		
		return fields;
	}
	
	/**
	 * Get string constants
	 * 
	 * @param targetClass target class
	 * @return string constants
	 */
	public static String[] getStringConstants(Class<?> targetClass) {
		
		return getConstants(targetClass, String.class).toArray(new String[0]);
	}
	
	/**
	 * Get constants
	 * 
	 * @param targetClass target class
	 * @param constantClass constant class
	 * @param <T> constant type
	 * @return constants
	 */
	protected static <T> Collection<T> getConstants(@NonNull Class<?> targetClass, @NonNull Class<T> constantClass) {
		
		List<T> constants = new ArrayList<>();
		
		for (Field field : targetClass.getDeclaredFields()) {
			
			Object value = ReflectionUtils.getField(field, null);
			
			if (constantClass.isInstance(value)) {
				
				@SuppressWarnings("unchecked")
				T constant = (T) value;
				
				constants.add(constant);
			}
		}
		
		return constants;
	}
	
	/**
	 * Get type parameter class
	 * 
	 * @param object object
	 * @param genericClass generic class
	 * @param index index
	 * @param <T> parameter type
	 * @return parameter class
	 * @throws IllegalStateException if failed to get
	 */
	public static <T> Class<T> getTypeParameterClass(Object object, Class<?> genericClass, int index)
		throws IllegalStateException {
		
		Class<?> targetClass = AopUtils.getTargetClass(object);
		
		Set<Type> genericTypes = new HashSet<>();
		genericTypes.add(targetClass.getGenericSuperclass());
		genericTypes.addAll(Arrays.asList(targetClass.getGenericInterfaces()));
		
		ParameterizedType paramedType = null;
		
		for (Type genericType : genericTypes) {
			
			if (!ParameterizedType.class.isInstance(genericType)) {
				
				continue;
			}
			
			ParameterizedType castType = (ParameterizedType) genericType;
			
			if (castType.getRawType().equals(genericClass)) {
				
				paramedType = castType;
				
				break;
			}
		}
		
		if (paramedType == null) {
			
			throw new IllegalStateException(String.format("Parameter type is not set: %s", genericClass));
		}
		
		Type[] paramTypes = paramedType.getActualTypeArguments();
		
		if (paramTypes.length <= index) {
			
			throw new IllegalStateException(String.format("Invalid parameter index: %s, %d", genericClass, index));
		}
		
		Type paramType = paramTypes[index];
		
		if (!Class.class.isInstance(paramType)) {
			
			throw new IllegalStateException(String.format("Parameter type is not actual: %s", genericClass));
		}
		
		@SuppressWarnings("unchecked")
		Class<T> paramClass = (Class<T>) paramType;
		
		return paramClass;
	}
}
