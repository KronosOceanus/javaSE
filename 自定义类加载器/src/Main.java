/**
 * 在dos命令中用java CompileClassLoader Main [参数...]运行
 */
public class Main{
    public static void main(String[] args) {
        for(String arg : args){
            System.out.println("运行Main的参数：" + arg);
        }
    }
}
