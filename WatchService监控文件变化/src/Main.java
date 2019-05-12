import java.nio.file.*;

public class Main {

    public static void main(String[] args) throws Exception{

        //获取文件系统的WatchService对象
        WatchService watchService = FileSystems.getDefault().newWatchService();
        //为C：盘根路径注册监听器
        Paths.get("C:/").register(watchService
                ,StandardWatchEventKinds.ENTRY_CREATE  //对应事件（创建，修改，删除）
                ,StandardWatchEventKinds.ENTRY_MODIFY
                ,StandardWatchEventKinds.ENTRY_DELETE);
        while(true){
                //获取下一个变化事件
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()){
                System.out.println(event.context() + "文件发生了 " + event.kind() + " 事件！");
            }
            //重设WatchKey
            boolean valid = key.reset();
            //如果重设失败
            if (! valid){
                break;
            }
        }
    }
}
