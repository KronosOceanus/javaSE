package util;

import ctrl.*;

import java.io.*;

/**
 * 序列化工具类
 */
public class SerializeUtil {

    //任务暂停，序列化DownloadContext
    public static void  serializable() throws Exception {
        //停止任务
        Holder.gc.pauseAll();
        //序列化
        File serFile = ParamUtil.SERIALIZABLE_FILE;
        if (! serFile.exists()){
            serFile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(serFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Holder.ts);
        fos.close();
        oos.close();
        System.out.println("====序列化完成！");
    }
    //反序列化（必须暂停的时候进行）
    public static void reverseSer() throws Exception{
        File serFile = ParamUtil.SERIALIZABLE_FILE;
        if (! serFile.exists()){
            return ;
        }
        FileInputStream fis = new FileInputStream(serFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Holder.ts = (TaskScreen)ois.readObject();
        fis.close();
        ois.close();
        System.out.println("====反序列化完成！");
    }

}
