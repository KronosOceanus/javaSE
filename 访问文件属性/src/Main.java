import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{
        Path testPath = Paths.get("Test.java");
        //获取两个对象
        BasicFileAttributeView basicView = Files.getFileAttributeView(  //attribute属性
                testPath,BasicFileAttributeView.class);
        BasicFileAttributes basicAttributes = basicView.readAttributes();
        System.out.println("创建时间: "+new Date(basicAttributes.creationTime().toMillis()));
        System.out.println("最后访问时间: "+new Date(basicAttributes.lastAccessTime().toMillis()));
        System.out.println("最后修改时间: "+new Date(basicAttributes.lastModifiedTime().toMillis()));
        System.out.println("文件大小: "+basicAttributes.size());

        FileOwnerAttributeView ownerView = Files.getFileAttributeView(
                testPath,FileOwnerAttributeView.class);
        System.out.println("文件用户: "+ownerView.getOwner());
        //获取guest对应的用户
        UserPrincipal user = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("guest");
        //修改用户（失败）    ownerView.setOwner(user);
        UserDefinedFileAttributeView userView = Files.getFileAttributeView(testPath,UserDefinedFileAttributeView.class);
        List<String> attrNames = userView.list();
        //遍历自定义属性
        for(String name:attrNames){
            ByteBuffer buf = ByteBuffer.allocate(userView.size(name));
            //读取自定义属性
            userView.read(name,buf);
            buf.flip();
            //简便的解码方法
            String value = Charset.defaultCharset().decode(buf).toString();
            System.out.println(name + " ----> " + value);
        }
        //添加自定义属性
        userView.write("发行者",Charset.defaultCharset().encode("java"));

        DosFileAttributeView dosView = Files.getFileAttributeView(testPath,DosFileAttributeView.class);
        //将文件设为隐藏，只读（第二次运行要取消只读属性）
        dosView.setHidden(true);
        dosView.setReadOnly(true);
    }
}
