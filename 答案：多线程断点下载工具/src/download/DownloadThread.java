package download;

import ctrl.TaskState;
import entity.*;
import ctrl.TaskScreen;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 * 下载线程
 */
public class DownloadThread extends Thread {

    //属性及初始化
    private static final int MAX_BUFFER_SIZE = 1024;

    private URL url;
    private Resource resource;
    private RandomAccessFile raf;
    private Part part;
    public DownloadThread(Resource resource,RandomAccessFile raf,Part part)
            throws MalformedURLException {
        this.url = createURL(resource.getUrl());
        this.resource = resource;
        this.raf = raf;
        this.part = part;
    }
    //根据url创建URL对象
    private URL createURL(String urlPath) throws MalformedURLException {
        return new URL(urlPath);
    }


    //线程执行体
    @Override
    public void run() {
        int begin = part.getBegin() + part.getCurrentLength();
        int end = part.getBegin() + part.getLength() - 1;
        if (begin >= end){
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ;
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Range","bytes=" + begin + "-" + end);
            conn.connect();
            //下载
            InputStream in = conn.getInputStream();
            byte[] buffer = new byte[MAX_BUFFER_SIZE];
            int hasRead = 0;

            //设置状态为下载
            resource.setState(TaskState.DOWNLOADING);
            raf.seek(part.getCurrentLength());
            while((part.getCurrentLength() < part.getLength()) &&
                        ((hasRead = in.read(buffer)) != -1)){
                raf.write(buffer,0,hasRead);
                part.setCurrentLength(part.getCurrentLength() + hasRead);

                //暂停
                if (resource.getState().equals(TaskState.PAUSE)){
                    closeStream(in,conn,raf);
                    return ;
                }
            }
            closeStream(in,conn,raf);

            //如果下载完成，合并文件，删除.part文件
            if (isFinished(resource.getSize())){
                uniteParts();
                deleteParts();
            }
        } catch (IOException e) {
            resource.setState(TaskState.FAILED);
            e.printStackTrace();
        }
    }


    //判断下载是否完毕
    private boolean isFinished(int fileLength){
        List<Part> parts = resource.getParts();
        int length = 0;
        for (Part p : parts){
            length += p.getCurrentLength();
        }
        return length >= fileLength;
    }
    //合并文件
    private void uniteParts()throws IOException{
        List<Part> parts = resource.getParts();
        //设置属性为不追加
        OutputStream out = new FileOutputStream(resource.getSaveFile(),false);
        for (Part p : parts){
            File partFile = new File(resource.getFilePath() +
                    File.separator + p.getPartName());
            InputStream in = new FileInputStream(partFile);
            byte[] buffer = new byte[1024];
            int hasRead = 0;
            while((hasRead = in.read(buffer)) != -1){
                out.write(buffer,0,hasRead);
            }
            in.close();
        }
        out.close();
        System.out.println("====合并完成！");
    }
    //删除.part文件
    private void deleteParts(){
        List<Part> parts = resource.getParts();
        for (Part p : parts){
            File partFile = new File(resource.getFilePath() +
                    File.separator + p.getPartName());
            partFile.delete();
        }
        System.out.println("====删除.part文件！");
    }
    //关闭流和连接
    private void closeStream(InputStream in,HttpURLConnection conn,
                     RandomAccessFile raf) throws IOException {
        in.close();
        conn.disconnect();
        raf.close();
    }

}
