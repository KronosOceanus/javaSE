package main;

import java.io.*;

public class ImageUtil {

    private final static int MAX_WIDTH = 220;
    private final static int MAX_HIGHT = 240;

    //根据url把图片写入当前路径
    public static void addImage(String url) throws Exception
    {
        String fileName = url.substring(url.lastIndexOf("\\"));
        InputStream in = new FileInputStream(url);
        OutputStream out = new FileOutputStream(".\\" + fileName);
        byte[] bbuf = new byte[256];
        while(in.read(bbuf) != -1){
            out.write(bbuf);
        }
    }
}
