package ctrl;

import entity.Resource;

import java.util.List;

/**
 * 群体控制
 */
public class GroupCtrl {

    //输出任务名称
    public void getResourceName(List<Resource> resources){
        for (Resource r : resources){
            System.out.println(r.getFileName());
        }
    }

    //开始所有任务
    public void startAll() throws Exception {
        for (Resource r : Holder.ts.getPauses()){
            Holder.sc.pauseDownload(r);
        }
    }

    //暂停所有任务
    public void pauseAll() throws Exception{
        for (Resource r : Holder.ts.getDownloadings()){
                Holder.sc.pauseDownload(r);
        }
    }

    //重新下载所有任务
    public void resumeAll() throws Exception{
        for (Resource r : Holder.ts.getPauses()) {
            Holder.sc.resumeDownload(r);
        }
    }

    //输出下载信息
    void getInformation(Resource r){

        Runnable runnable = () -> {
            try {
                System.out.println("====文件大小：" + r.getSize());
                Thread.sleep(2000);
                while (r.getState().equals(TaskState.CONNECTING)){
                    System.out.println("====正在连接……");
                    Thread.sleep(2000);
                }
                while(r.getState().equals(TaskState.DOWNLOADING)){
                    System.out.println("====文件名：" + r.getFileName());
                    System.out.println("====下载进度：" + r.getProcess() + "%");
                    System.out.println("====下载速度：" + r.getSpeed() + "kb\\s");
                    System.out.println("====已花时间：" + r.getCostTime() + "s");
                    System.out.println("====剩余时间：" + r.getSpareTime() + "s");
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }

}
