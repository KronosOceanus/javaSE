package util;

import entity.*;

import java.io.File;

/**
 * 参数工具类
 */
public class ParamUtil {

    //下载路径
    public static String DOWNLOAD_PATH = ".\\resource";
    //下载线程数
    public static int THREAD_NUM = 5;
    //序列化文件
    public static File SERIALIZABLE_FILE = new File(".\\serialize\\serialize.txt");
    //得到部分文件名
    public static String getPartFileName(Resource r, Part p){
        String result = r.getFilePath() + File.separator +
                p.getPartName();
        return result;
    }

}
