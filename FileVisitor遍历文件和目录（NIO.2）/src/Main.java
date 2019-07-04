import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {

    public static void main(String[] args) throws Exception{

        Files.walkFileTree(Paths.get("D:","project","javaSE","FileVisitor遍历文件和目录（NIO.2）")
                ,new SimpleFileVisitor<Path>(){  //————————此处匿名内部类

                    //访问文件时触发该方法
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        System.out.println("正在访问 "+file+" 文件");
                        //找到文件
                        if (file.endsWith("FileVisitorTest.java")){
                            System.out.println("==已找到目标文件==");
                            return FileVisitResult.TERMINATE; //terminate终止
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    //开始访问目录时触发该方法
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        System.out.println("正在访问 "+dir+" 路径");
                        return FileVisitResult.CONTINUE;
                    }
                });
    }
}
