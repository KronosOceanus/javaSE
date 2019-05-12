import java.util.*;

public class Main {

    public static void main(String[] args) {
        Collection books = new ArrayList();
        books.add("轻量级java EE企业应用实战");
        books.add("疯狂java讲义");
        books.add("疯狂android讲义");

        //利用Stream（流）遍历集合
        /**
         * 用stream方法将集合转换为Stream对象
         * 用filter方法过滤掉不符合条件的
         * 用count方法输出流中所有元素的数量
         */
        System.out.println(books.stream().filter(ele -> ((String) ele).contains("疯狂")).count()+"\n");

        /**
         * 用mapToInt方法将Stream转化为IntStream
         * 对流中的元素用lambda表达式进行一一对换
         * 因为mapToXxx是中间方法，所以可以继续调用forEach方法
         *System.out::println引用println方法（lambda表达式方法引用）
         */
        books.stream().mapToInt(ele -> ((String)ele).length()).forEach(System.out::println);
    }
}