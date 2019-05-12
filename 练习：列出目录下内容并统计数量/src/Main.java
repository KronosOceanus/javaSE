import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        while(sc.hasNext()){

            //保存两种形式的路径
            String way = sc.next();
            Path path = Paths.get(way);

            //获取路径下的文件夹以及文件数量
            int filesCount = 0;
            int foldersCount = 0;
            File d = new File(way);
            File list[] = d.listFiles();
            for(int i=0;i<list.length;i++){
                if (list[i].isFile()){
                    filesCount ++;
                }
                else{
                    foldersCount ++;
                }
            }
            System.out.println("文件夹数量: "+foldersCount);
            System.out.println("文件数量: "+filesCount);

            //列出目录下的所有
            Files.walkFileTree(path,new SimpleFileVisitor<Path>(){
                @Override
                //列出文件
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                //列出目录
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }
}
