public class Account {

    private ThreadLocal<String> name = new ThreadLocal<>();
    public Account(String str){
        this.name.set(str);
        //访问当前线程name副本
        System.out.println("---" + this.name.get());
    }
    //都是集合方法
    public String getName(){
        return name.get();
    }
    public void setName(String str){
        this.name.set(str);
    }
}
