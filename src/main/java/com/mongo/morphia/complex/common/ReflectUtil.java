package com.mongo.morphia.complex.common;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author lian_lin
 *@since 用于对象的属性反射操作
 */
public class ReflectUtil {
	private static int METHOD = 1;
	private static int FIELD = 2;

	private static String toUpperFirstChar(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * @see 设置对象的属性值，支持系统通用分隔符 XX
	 *      的长属性；当设置的属性为子对象的属性时，而子对象为空，本方法将自动实例化该对象，以便设置其属性<br>
	 * @see 本方法采用属性对应的set、get方法反射实现
	 * @see //以下代码user的dept对象将被自动实例化并设置dept的id属性值
	 * 
	 * @see 例：User user=new User();<br>
	 *      setAttributeValue(user,"deptXXid",2);
	 * @param c
	 * @param attribute
	 * @param value
	 */
	public static void setAttributeValue(Object c, String attribute,
			Object value) {

		try {
			String[] arbs = attribute.split("XX");
			for (int i = 0; i < arbs.length; i++) {
				Method[] methods = c.getClass().getMethods();
				for (Method method : methods) {
					Class[] parameterTypes = method.getParameterTypes();
					if (i == (arbs.length - 1)) {// 最后一个属性

						String methodName = "set" + toUpperFirstChar(arbs[i]);
						if (method.getName().equals(methodName)
								&& parameterTypes.length == 1) {
							method.invoke(c, value);
							break;
						}
					} else {// 中间属性需先取出对象

						String methodName = "get" + toUpperFirstChar(arbs[i]);
						Class returnType = method.getReturnType();
						if (method.getName().equals(methodName)
								&& !returnType.getName().equals("void")
								&& parameterTypes.length == 0) {
							Object o = method.invoke(c);
							if (o == null) {
								methodName = "set" + toUpperFirstChar(arbs[i]);
								Object v = returnType.newInstance();
								c.getClass().getMethod(methodName, returnType)
										.invoke(c, v);
								c = v;
							}
						}
					}
				}

			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see 设置对象的属性值，支持系统通用分隔符 XX
	 *      的长属性，本次修改不再支持不区分大小写的属性参数，必须精确匹配属性；当设置的属性为子对象的属性时
	 *      ，而子对象为空，本方法将自动实例化该对象，以便设置其属性<br>
	 * @see 本方法采用属性直接反射实现
	 *      ，hibernate环境下由于其使用了Javassist动态AOP，子对象可能为代理对象，属性有可能不存在，涉及子对象属性的操作请慎用。
	 * 
	 * @see //以下代码user的dept对象将被自动实例化并设置dept的id属性值
	 * 
	 * @see 例：User user=new User();<br>
	 *      setClassAttributeValue(user,"deptXXid",2);
	 * @param c
	 * @param attribute
	 * @param value
	 */
	public static void setClassAttributeValue(Object c, String attribute,
			Object value) {
		Field field = null;
		try {
			Object o = null;
			String[] arbs = attribute.split("XX");
			for (int i = 0; i < arbs.length; i++) {
				field = c.getClass().getDeclaredField(arbs[i]);
				if (i == (arbs.length - 1)) {
					field.setAccessible(true);
					field.set(c, value);
					field.setAccessible(false);

					return;
				}

				field.setAccessible(true);
				o = field.get(c);
				field.setAccessible(false);
				if (o == null) {
					o = field.getType().newInstance();
					setClassAttributeValue(c, arbs[i], o);
				}
				c = o;
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println("不能转换的数据类型");
			System.err.println("Value:[" + value.toString() + "]");
			System.err.println("ValueType:[" + value.getClass() + "]");
			System.err.println("FieldName:[" + attribute + "]");
			System.err.println("FieldType:[" + field.getType() + "]");

			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see 返回期望得到的对象属性值，支持系统通用分隔符 XX 的长属性，当取子对象的属性时，子对象为空则直接返回null
	 * @see 本方法采用属性对应的get方法反射实现
	 * @param c
	 * @param attribute
	 * @return 返回期望得到的对象属性值，支持系统通用分隔符 XX 的长属性，当取子对象的属性时，子对象为空则直接返回null
	 */ 
	public static Object getAttributeValue(Object c, String attribute) {
		Object o = null;
		try {

			String[] arbs = attribute.split("XX");
			for (int i = 0; i < arbs.length; i++) {
				Method[] methods = c.getClass().getMethods();
				for (Method method : methods) {
					String methodName = "get" + toUpperFirstChar(arbs[i]);
					Class returnType = method.getReturnType();
					if (method.getName().equals(methodName)
							&& !returnType.getName().equals("void")) {
						o = method.invoke(c);
						if (o == null) {
							return o;
						} else {
							c = o;
							break;
						}
					}

				}
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * @see 返回期望得到的对象属性值，支持系统通用分隔符 XX 的长属性，当取子对象的属性时，子对象为空则直接返回null
	 * @see 重载上面的方法，增加默认值的传入，当反射的属性为null时返回默认值
	 * @see 该方法旨在datatables取属性时使用，其他地方请仔细检查
	 * @see 本方法采用属性对应的get方法反射实现
	 * @param c
	 * @param attributte
	 * @return 返回期望得到的对象属性值，支持系统通用分隔符 XX 的长属性，当取子对象的属性时，子对象为空则直接返回null
	 */
	public static String getAttributeValue(Object c, String attribute,
			String defaultValue) {
		Object o = null;
		try {

			String[] arbs = attribute.split("XX");
			for (int i = 0; i < arbs.length; i++) {
				Method[] methods = c.getClass().getMethods();
				for (Method method : methods) {
					String methodName = "get" + toUpperFirstChar(arbs[i]);
					Class returnType = method.getReturnType();
					if (method.getName().equals(methodName)
							&& !returnType.getName().equals("void")) {
						o = method.invoke(c);
						if (o == null) {
							return defaultValue;
						} else {
							c = o;
							break;
						}
					}

				}
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return String.valueOf(o);
	}

	/**
	 * @see 返回期望得到的对象属性值，支持系统通用分隔符 XX 的长属性，本次修改不再支持不区分大小写的属性参数，必须精确匹配属性；<br>
	 *      例：getClassAttributeValue(user,"deptXXid")
	 * @see 本方法采用属性直接反射实现
	 *      ，hibernate环境下由于其使用了Javassist动态AOP，子对象可能为代理对象，属性有可能不存在，涉及子对象属性的操作请慎用。
	 * @param c
	 * @param attribute
	 * @return 返回期望得到的对象属性值，支持系统通用分隔符 XX 的长属性，本次修改不再支持不区分大小写的属性参数，必须精确匹配属性；<br>
	 */
	public static Object getClassAttributeValue(Object c, String attribute) {
		Object o = null;
		try {
			String[] arbs = attribute.split("XX");
			for (String arb : arbs) {
				Field field = c.getClass().getDeclaredField(arb);
				field.setAccessible(true);
				o = field.get(c);
				field.setAccessible(false);
				if (o == null) {
					break;
				}
				c = o;
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * @see 返回期望得到的对象属性类型，支持系统通用分隔符 XX 的长属性，本次修改不再支持不区分大小写的属性参数，必须精确匹配属性；<br>
	 * @see 例：getAttributeType(user,"deptXXid")
	 * @param c bean 对象
	 * @param attribute
	 * @return 返回期望得到的对象属性类型，支持系统通用分隔符 XX 的长属性，本次修改不再支持不区分大小写的属性参数，必须精确匹配属性；<br>
	 */
	public static Class getAttributeType(Object c, String attribute) {
		Object o = null;
		try {
			String[] arbs = attribute.split("XX");
			for (int i = 0; i < arbs.length; i++) {
				Field field = c.getClass().getDeclaredField(arbs[i]);
				if (i == (arbs.length - 1)) {
					return field.getType();
				}

				field.setAccessible(true);
				o = field.get(c);
				field.setAccessible(false);
				if (o == null) {
					o = field.getType().newInstance();
				}
				c = o;
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see 将集合中对象的id属性以逗号连接为字符串，默认连接符为 半角逗号 [,]
	 * @param collection
	 * @return 将集合中对象的id属性以逗号连接为字符串，默认连接符为 半角逗号 [,]
	 */
	public static String joinCollectionAttributeForId(Collection<?> collection) {
		return joinCollectionAttribute(collection, "id", ",", METHOD);
	}

	/**
	 * @see 将集合中某属性的值以逗号连接为字符串，默认连接符为 半角逗号 [,]
	 * @param collection
	 * @param attribute
	 * @return
	 */
	public static String joinCollectionAttribute(Collection<?> collection,
			String attribute) {
		return joinCollectionAttribute(collection, attribute, ",", METHOD);
	}

	/**
	 * @see 将集合中某属性的值以特定字符连接为字符串，支持系统通用分隔符 XX 的长属性；
	 * @see 例：joinCollectionAttribute(users,"deptXXid",",");
	 * @param collection
	 * @param attribute
	 * @param connector
	 * @param execRule 执行方式，分反射方法或反射属性执行两种
	 * @return 将集合中某属性的值以特定字符连接为字符串，支持系统通用分隔符 XX 的长属性；
	 */
	public static String joinCollectionAttribute(Collection<?> collection,
			String attribute, String connector, int execRule) {
		StringBuilder values = new StringBuilder();
		for (Object o : collection) {
			Object value = null;
			if (execRule == METHOD) {
				value = getAttributeValue(o, attribute);
			} else if (execRule == FIELD) {
				value = getClassAttributeValue(o, attribute);
			}
			values.append(value);
			values.append(connector);
		}

		// 去除最后一个多余的连接符

		if (values.length() > 0) {
			values.setLength(values.length() - connector.length());
		}
		return values.toString();
	}

}
