package com.radchenko.contact.utils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author radchenko.as
 * @version 1.0
 */
public class ReflectionUtils {
	private static WeakReference<Map<Class<?>, List<Field>>> NOT_STATIC_FIELDS_MAP = 
			new WeakReference<Map<Class<?>,List<Field>>>(new HashMap<Class<?>, List<Field>>());;
	
	/**
	 * Method getCachedAccessibleFields.
	 * @param clazz Class<?>
	
	 * @return List<Field> */
	private static List<Field> getCachedAccessibleFields(Class<?> clazz) {
		Map<Class<?>,List<Field>> map = NOT_STATIC_FIELDS_MAP.get();
		if(map == null) {
			synchronized(ReflectionUtils.class) {
				NOT_STATIC_FIELDS_MAP = new WeakReference<Map<Class<?>,List<Field>>>(new HashMap<Class<?>, List<Field>>());
				map = NOT_STATIC_FIELDS_MAP.get();
			}
		}
		List<Field> res = null;
		if(map != null) {
			res = map.get(clazz);
		}
			
		if(res == null) {
			synchronized(ReflectionUtils.class) {
				res = getAllNotStaticAccessibleFields0(clazz);
				if(map != null) {
					map.put(clazz, res);
				}
			}
		}
		return res;
	}
	
	/**
	 * Method getAllNotStaticAccessibleFields0.
	 * @param clazz Class<?>
	
	 * @return List<Field> */
	private static List<Field> getAllNotStaticAccessibleFields0(Class<?> clazz){
		List<Field> res = getAllDeclaredFields(clazz);
		Iterator<Field> it = res.iterator();
		while(it.hasNext()) {
			Field f = it.next();
			if(Modifier.isStatic(f.getModifiers())) {
				it.remove();
			}
			else{
				f.setAccessible(true);
			}
		}
		return res;
	}
	
	/**
	 * Method addFields.
	 * @param list List<Field>
	 * @param clazz Class<?>
	
	 * @return List<Field> */
	private static List<Field> addFields(List<Field> list, Class<?> clazz) {
		list.addAll(Arrays.asList(clazz.getDeclaredFields()));
		if(clazz.getSuperclass() == Object.class || clazz.getSuperclass() == null) {
			return list;
		}else{
			return addFields(list, clazz.getSuperclass());
		}
	}
	
	/**
	 * Method getAllDeclaredFields.
	 * @param clazz Class<?>
	
	 * @return List<Field> */
	public static List<Field> getAllDeclaredFields(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		return addFields(list, clazz);
	}
	
	/**
	 * Method getAllNotStaticAccessibleFields.
	 * @param clazz Class<?>
	
	 * @return List<Field> */
	public static List<Field> getAllNotStaticAccessibleFields(Class<?> clazz){
		return getCachedAccessibleFields(clazz);
	}
	
	/**
	 * Method getAccessibleField.
	 * @param clazz Class<?>
	 * @param fieldName String
	
	
	
	 * @return Field * @throws SecurityException * @throws NoSuchFieldException * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public static Field getAccessibleField(Class<?> clazz, String fieldName) throws SecurityException, NoSuchFieldException {
		Class<?> cl = clazz;
		while(true) {
			for(Field fi : cl.getDeclaredFields()) {
				if(fi.getName().equals(fieldName)) {
					fi.setAccessible(true);
					return fi;
				}
			}
			cl = cl.getSuperclass();
			if(cl == null) {
				break;
			}
		}
		throw new NoSuchFieldException(fieldName+" for "+clazz.getName()+" class");
	}
	
	/**
	 * Method getField.
	 * @param fs List<Field>
	 * @param name String
	
	 * @return Field */
	public static Field getField(List<Field> fs, String name) {
		for(Field f : fs) {
			if(f.getName().equals(name)) {
				return f;
			}
		}
		return null;
	}
	
	/**
	 * Method copyByFields.
	 * @param srcFields List<Field>
	 * @param destFields List<Field>
	 * @param dest Object
	 * @param src Object
	 */
	public static void copyByFields(List<Field> srcFields, List<Field> destFields, Object dest, Object src) {
		for(Field f : srcFields) {
			Field destField = getField(destFields, f.getName());
			if(destField != null) {
				try {
					destField.set(dest, f.get(src));
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	/**
	 * @author radchenko.as
	 * @version 1.0
	 */
	public static interface IObjectConverter {
		/**
		 * Method convert.
		 * @param source Object
		 * @param destClass Class<?>
		
		
		 * @return Object * @throws IllegalArgumentException * @throws IllegalArgumentException
		 */
		Object convert (Object source, Class<?> destClass) throws IllegalArgumentException;
	}
	
	/**
	 * @author radchenko.as
	 * @version 1.0
	 */
	private static final class SimpleConverter implements IObjectConverter {
		/**
		 * Method convert.
		 * @param source Object
		 * @param destClass Class<?>
		
		
		
		 * @return Object * @throws IllegalArgumentException * @see nedis.htppl.core.utils.ReflectionUtils$IObjectConverter#convert(Object, Class<?>) * @throws IllegalArgumentException
		 * @see nedis.htppl.core.utils.ReflectionUtils$IObjectConverter#convert(Object, Class<?>)
		 */
		@Override
		public Object convert(Object source, Class<?> destClass) throws IllegalArgumentException {
			if(source == null) {
				return null;
			}
			else{
				if(destClass.isAssignableFrom(source.getClass())) {
					return source;
				}
				else{
					return null;
				}
			}
		}	
	}
	
	/**
	 * Method copyBeans.
	 * @param destBean Object
	 * @param sourceBean Object
	 * @param withNullValues boolean
	 * @param converter IObjectConverter
	 */
	public static void copyBeans (Object destBean, Object sourceBean, boolean withNullValues, IObjectConverter converter) {
		Method [] methods = destBean.getClass().getMethods();
		for (Method setter : methods) {
			String setterMethodName = setter.getName();
			if(setterMethodName.startsWith("set")) {
				String getterMethodName = "get"+setterMethodName.substring(3);
				Method getter = null;
				try {
					getter = sourceBean.getClass().getMethod(getterMethodName);
				} catch (NoSuchMethodException e) {
					getterMethodName = "is"+setterMethodName.substring(3);
					try {
						getter = sourceBean.getClass().getMethod(getterMethodName);
					} catch (NoSuchMethodException e2) {
						//do nothing
						getter = null;
					}
				}
				if(getter != null) {
					try {
						Object value = getter.invoke(sourceBean);
						if(value != null) {
							Object res = converter.convert(value, setter.getParameterTypes()[0]);
							if(res != null) {
								setter.invoke(destBean, res);
							}
						}
						else{
							if(withNullValues) {
								Object res = converter.convert(value, setter.getParameterTypes()[0]);
								setter.invoke(destBean, res);
							}
						}
					}  catch (Exception e) {
						throw new RuntimeException(e);
					} 
				}
				
			}
		}
	}
	
	/**
	 * Method copyBeans.
	 * @param destBean Object
	 * @param sourceBean Object
	 * @param withNullValues boolean
	 */
	public static void copyBeans (Object destBean, Object sourceBean, boolean withNullValues) {
		copyBeans(destBean, sourceBean, withNullValues, new SimpleConverter());
	}
	
	/**
	 * Method copyByFields.
	 * @param dest Object
	 * @param src Object
	 */
	public static void copyByFields(Object dest, Object src) {
		List<Field> srcFields = getCachedAccessibleFields(src.getClass());
		List<Field> destFields = getCachedAccessibleFields(dest.getClass());
		copyByFields(srcFields, destFields, dest, src);
	}
	
	/**
	 * Method copyByNotNullFields.
	 * @param srcFields List<Field>
	 * @param destFields List<Field>
	 * @param dest Object
	 * @param src Object
	 */
	public static void copyByNotNullFields(List<Field> srcFields, List<Field> destFields, Object dest, Object src) {
		for(Field f : srcFields) {
			Field destField = getField(destFields, f.getName());
			if(destField != null) {
				try {
					Object val = f.get(src);
					if(val != null) {
						destField.set(dest, val);
					}
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	/**
	 * Method copyByNotNullFields.
	 * @param dest Object
	 * @param src Object
	 */
	public static void copyByNotNullFields(Object dest, Object src) {
		List<Field> srcFields = getCachedAccessibleFields(src.getClass());
		List<Field> destFields = getCachedAccessibleFields(dest.getClass());
		copyByNotNullFields (srcFields, destFields, dest, src);
	}
	
	
	/**
	 * Method set.
	 * @param fieldName String
	 * @param o Object
	 * @param value Object
	 */
	public static void set(String fieldName, Object o, Object value) {
		try {
			Field f = getAccessibleField(o.getClass(), fieldName);
			f.setInt(f, f.getModifiers() & ~Modifier.FINAL);
			f.set(o, value);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Method setFinalField.
	 * @param fieldName String
	 * @param o Object
	 * @param value Object
	 */
	public static void setFinalField (String fieldName, Object o, Object value) {
		try {
			List<Field> fields = getAllNotStaticAccessibleFields(o.getClass());
			Field f = null;
			for(Field fi : fields) {
				if(fi.getName().equals(fieldName)) {
					f = fi;
					break;
				}
			}
			if(f == null) {
				throw new NoSuchFieldException(fieldName);
			}
			
			Field modifiersField = Field.class.getDeclaredField("modifiers");
		    modifiersField.setAccessible(true);
		    modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
		      
			f.set(o, value);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Method set.
	 * @param f Field
	 * @param o Object
	 * @param value Object
	 */
	public static void set(Field f, Object o, Object value) {
		try {
			f.set(o, value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Method getValue.
	 * @param o Object
	 * @param fieldName String
	
	 * @return Object */
	public static Object getValue (Object o, String fieldName) {
		try {
			Field f = getAccessibleField(o.getClass(), fieldName);
			return get(f, o);
		}  catch (NoSuchFieldException e) {
			return null;
		}
	}
	
	/**
	 * Method get.
	 * @param f Field
	 * @param o Object
	
	 * @return Object */
	public static Object get(Field f, Object o) {
		try {
			return f.get(o);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
