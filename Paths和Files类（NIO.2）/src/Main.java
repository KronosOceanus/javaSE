import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

//比File类高效
public class Main {

    public static void main(String[] args) throws IOException {

        //Paths类,Path代表一个与平台无关的路径
        System.out.println("====Paths类====");
        Path path1 = Paths.get(".");
        System.out.println("path里包含的路径数量: "+path1.getNameCount());
        System.out.println("path的根路径: "+path1.getRoot());
        Path absolutePath = path1.toAbsolutePath();
        System.out.println("绝对路径: "+absolutePath);
        System.out.println("绝对路径包含路径数量: "+absolutePath.getNameCount());

        //以多个String构建Path对象
        Path path2 = Paths.get("D:","project","Paths和Files类（NIO.2）",".");

        //直接输出构造时的路径
        System.out.println("构造时路径: "+path2);



        //Files类
        //复制文件
        System.out.println("\n====Files类====");
        Files.copy(Paths.get("FilesTest.java"),new FileOutputStream("a.txt"));
        System.out.println("FilesTest.java是否为隐藏文件: "
                + Files.isHidden(Paths.get("FilesTest.java")));

        //一次性读取FileTest.java文件的所有行
        List<String> lines = Files.readAllLines(Paths.get("FilesTest.java"), Charset.forName("gbk"));
        System.out.println(lines);

        //判断文件大小（返回字节数）
        System.out.println("FilesTest.java的大小为: "
                + Files.size(Paths.get("FilesTest.java")));

        //多个字符串直接写入文件
        List<String> poem = new ArrayList<>();
        poem.add("床前明月光");
        poem.add("自挂东南枝");
        Files.write(Paths.get("poem.txt"),poem,Charset.forName("gbk"));

        //列出所有文件和子目录
        System.out.println("当前目录下所有文件和子目录: ");
        Files.list(Paths.get(".")).forEach(path -> System.out.println(path));

        //读取文件内容
        Files.lines(Paths.get("FilesTest.java"),Charset.forName("gbk"))
                .forEach(line -> System.out.println(line));

        //判断C盘空间
        FileStore cStore = Files.getFileStore(Paths.get("C:"));
        System.out.println("C:总空间: "+cStore.getTotalSpace());
        System.out.println("C:可用空间: "+cStore.getUsableSpace());
    }
}
