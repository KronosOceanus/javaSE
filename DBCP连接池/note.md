[TOC]

## 实现数据库连接池的思路
**注意！：数据库连接池是将 Connection 放入 ThreadLocal 实现的！保证了事务**
1. 实现javax.sql.DataSource接口中的getConnection方法
2. 提供一个集合（LinkedList）用来存放连接
3. 为连接池初始化多个连接
4. 使用连接，调用getConnection方法，为保证线程同步，先把连接从List中移除
5. 释放资源，不执行close方法，而是将连接添加到List中

## 数据库连接池处理多线程
* 一个线程在不同时刻（或者在不同地方）获取到的 Connection，肯定不是同一个 Connection，
* 事务操作需要在 service 层完成，却又需要 Connection 对象（本应在 dao 层），而直接在
    service 层获取的 Connection 对象，为了完成事务，还需要作为参数传到 dao 层（为了保
        证事务操作是同一个 Connection 执行）
* 为了解决上述问题，需要编写一个工具类：使用 ThreadLocal 储存当前线程的 Connection 对
    象（从连接池获取），从该工具类可以获取当前线程 Connetion 对象，保证事务 dao 层操作
        使用的是同一个 Connetion
* 例：
```java

```

## 装饰模式
#### 作用
不改变对象结构的情况下，动态得给对象增加一些功能
#### 优缺点
###### 优点：
* 比继承方式更加灵活
* 可以设计出多个不同的具体装饰类，创造出多个不同行为的组合

###### 缺点：
* 派生了许多子类，过度使用会是程序变得复杂

#### 组成
4个角色：
1. 抽象构件（Component）角色：定义一个抽象接口以规范准备接收附加责任的对象。
2. 具体构件（Concrete    Component）角色：实现抽象构件，通过装饰角色为其添加一些职责。
3. 抽象装饰（Decorator）角色：继承抽象构件，并**包含具体构件的实例**，
    可以通过其子类扩展具体构件的功能。
4. 具体装饰（ConcreteDecorator）角色：实现抽象装饰的相关方法，并给具体构件对象添加附加的责任。

#### 例
要改造Connection（具体构件）的close方法，就新建一个MyConnection类（相当于具体装饰，
    省略了抽象装饰 / 包含一个构造器，传入Connection实例）实现Connection接口，并重写改造close
        方法（用到的方法都需要重写/直接调用原对象的方法即可），然后直接使用MyConnection对象。
