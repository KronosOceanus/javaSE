[TOC]
## 反射
* 创建存放类名，对象的对象池
* 创建Properties文件读取XML等对象文件
#### 方法
* 根据类名创建某个类的实例
```java
    Class<?> clazz = Class.forName(clazzName);
    return clazz.getDeclaredConstructor().newInstance();
```
* 根据属性文件得到类名，初始化对象池
```java
    for(String name : props.stringPropertyNames()){
        if(!name.contains("%")){
            objectPool.put(name,createObject(props.getProperty(name)));
        }
    }
```
* 得到成员变量，取消访问权限检查（可以获取到private修饰的对象），然后设置属性
```java
    Field rootPaneCheckingEnabledField = targetClass.getDeclaredField(
        "rootPaneCheckingEnabled");
     //取消访问权限检查
     rootPaneCheckingEnabledField.setAccessible(true);
     //修改target对象的属性
     rootPaneCheckingEnabledField.setBoolean(target,true);
```
* 得到方法，并执行
```java
    //参数（方法名，形参类型）
    Method method = targetClass.getMethod(mtdName, String.class);
    //参数（调用对象，参数）
    method.invoke(target, props.getProperty(name));
```

