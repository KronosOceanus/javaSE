public class Main {

    private String name;
    public int age;
    public Main(String name,int age){
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) throws Exception {

        Main src = new Main("cs",18);
        Main dest = new Main("ky",17);
        //修改age变量的引用，name变量的内容
        ObjectFactoryPool.modifyReference(src,dest,"age");
        ObjectFactoryPool.modifyField(dest,"name","me");
        System.out.println(src.name + " : " + src.age);
        System.out.println(dest.name + " : " + dest.age);

    }
}
