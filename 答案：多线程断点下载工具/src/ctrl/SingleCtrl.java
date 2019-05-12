package ctrl;

import download.DownloadThread;
import entity.*;
import util.ParamUtil;

import java.io.RandomAccessFile;

/**
 * 单体控制
 */
public class SingleCtrl {

    //开始下载（分割文件）
    public void doDownload(Resource r) throws Exception {
        r.setState(TaskState.CONNECTING);
        //计算每块的长度
        int partLength = r.getSize() /r.getThreadNum() + 1;
        //下载
        for (int i=0;i<r.getThreadNum();i++){
            //为Part赋值
            int begin = i * partLength;
            int length = partLength;
            //最后一块减去前面总和（防止除不尽）
            if (i == (r.getThreadNum() - 1)){
                length = r.getSize() - i * partLength;
            }
            Part p = new Part(begin,length,0);
            r.getParts().add(p);
            RandomAccessFile rav = new RandomAccessFile(ParamUtil.getPartFileName(r,p),"rw");
            DownloadThread t = new DownloadThread(r,rav,p);
            t.setPriority(6);
            t.start();
        }
        System.out.println("====正在连接……");
        //获取下载信息
        Holder.gc.getInformation(r);
    }

    //暂停下载
    public void pauseDownload(Resource r) throws Exception{
        r.setState(TaskState.PAUSE);
        System.out.println("====暂停下载！");
    }

    //继续下载
    public void resumeDownload(Resource r) throws Exception {
        //下载完成
        if (r.getState().equals(TaskState.FINISHED)){
            return ;
        }
        r.setState(TaskState.CONNECTING);
        //继续下载
        for (int i=0;i<r.getThreadNum();i++){
            Part p = r.getParts().get(i);
            RandomAccessFile rav = new RandomAccessFile(ParamUtil.getPartFileName(r,p),"rw");
            DownloadThread t = new DownloadThread(r,rav,p);
            t.setPriority(6);
            t.start();
        }
        System.out.println("====重新连接……");
        Holder.gc.getInformation(r);
    }

}
