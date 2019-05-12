import java.io.*;
import java.lang.reflect.Method;

/**
 * 读取文件内容得到字节流
 * 用javac指令编译
 * 重写findClass方法自定义加载方式
 */
public class CompileClassLoader extends ClassLoader{

    //读取一个文件的内容
    private byte[] getBytes(String fileName) throws IOException{
        File file = new File(fileName);
        long len = file.length();
        byte[] raw = new byte[(int)len];
        try(
                FileInputStream fin = new FileInputStream(file);
        ){
            //读取内容的长度
            int r = fin.read(raw);
            //文件太大
            if(r != len){
                throw new IOException("无法读取全部文件"
                        + r + "!=" + len);
            }
            return raw;
        }
    }

    //定义编译指定java文件的方法
    private boolean compile(String javaFile) throws IOException{
        System.out.println("CompileClassLoader：正在编译 "
                + javaFile + "...");
        //记得javac后面的空格
        Process p = Runtime.getRuntime().exec("javac " + javaFile);
        try{
            //使当前线程等待Process
            p.waitFor();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        //进程的退出值（0表示正常退出，否则抛出IllegalThreadStateException异常）
        int ret = p.exitValue();
        return ret == 0;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        Class clazz = null;
        //将包路径中的.替换为/
        String fileStub = name.replace(".","/");
        String javaFilename = fileStub + ".java";
        String classFilename = fileStub + ".class";
        File javaFile = new File(javaFilename);
        File classFile = new File(classFilename);

        //java文件存在，Class文件不存在，或者编译后修改过java文件（根据最后修改时间），重新编译
        if(javaFile.exists() && (!classFile.exists()
                || javaFile.lastModified() > classFile.lastModified()));
        {
            try{
                //编译失败，或者Class文件不存在
                if(!compile(javaFilename) || !classFile.exists()){
                    throw new ClassNotFoundException("ClassNotFoundException："
                            + javaFilename);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //如果Class文件存在，转化为Class对象
        if(classFile.exists()){
            try {
                //读取Class文件内容
                byte[] raw = getBytes(classFilename);
                //将二进制数据转换成Class对象
                clazz = defineClass(name,raw,0,raw.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //加载失败
        if(clazz == null){
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    public static void main(String[] args) throws Exception{
        //运行时没有目标类
        if(args.length < 1){
            System.out.println("缺少目标类，请按如下格式运行java源文件：");
            System.out.println("java CompileClassLoader ClassName");
        }
        //第一个参数是要运行的类
        String progClass = args[0];
        //剩下是运行目标类时的参数
        String[] proArgs = new String[args.length - 1];
        //复制到一个新的数组，参数（数组A，起始位置，数组B，起始位置，内容长度）
        System.arraycopy(args,1,proArgs,0,proArgs.length);

       CompileClassLoader ccl = new CompileClassLoader();
        //加载需要运行的类（ClassLoader的入口）
        Class<?> clazz = ccl.loadClass(progClass);
        //反射调用方法
        Method main = clazz.getMethod("main", String[].class);
        Object[] argsArray = {proArgs};
        main.invoke(null,argsArray);
    }
}