import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //当前路径新建文件(指定路径名称)
        File file = new File(".");
        file.createNewFile();
        System.out.println(file.getName());
        //获取相对路径的父路径可能会出错（输出null）
        System.out.println(file.getParent());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsoluteFile().getParent());

        //当前路径新建临时文件(文件名，后缀名，在哪个文件指针下)
        File tmpFile = File.createTempFile("aaa",".txt",file);
        //指定jvm退出时删除文件
        tmpFile.deleteOnExit();

        File newFile = new File(System.currentTimeMillis()+"");
        System.out.println("newFile是否存在"+newFile.exists());
        newFile.createNewFile();
        //创建失败，因为newFile已经存在
        System.out.println(newFile.mkdir());
        newFile.deleteOnExit();

        //使用list方法列出当前路径下的所有文件和路径………………………………出错
        String[] fileList = file.list();
        System.out.println("======当前所有文件）和路径如下………………");
        assert fileList != null;
        for(String fileName:fileList){
            System.out.println(fileName);
        }

        //使用listRoots静态方法列出所有的磁盘路径
        File[] roots = File.listRoots();
        System.out.println("======系统所有根路径如下………………");
        for(File root:roots){
            System.out.println(root);
        }

        //文件过滤器
        //list方法里指定过滤规则，参数类型（File,String），条件（.java结尾）或者（文件对应一个路径）
        String[] nameList = file.list((dir,name) -> name.endsWith(".java") || new File(name).isDirectory());
        assert nameList != null;
        for(String name:nameList){
            System.out.println(name);
        }
    }
}