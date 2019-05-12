package download;

import entity.Resource;
import ctrl.Holder;
import util.ParamUtil;

/**
 * 新建任务类（并下载）
 */
public class NewTask {

    private String url;

    //新建任务并添加到下载
    public NewTask(String url) throws Exception {
        this.url = url;
        Resource r = createResource();
        Holder.ts.resources.add(r);
        Holder.sc.doDownload(r);
    }
    //根据url创建任务对象
    private Resource createResource(){
        //保存路径，文件名，线程数
        String filePath = ParamUtil.DOWNLOAD_PATH;
        String fileName = getSaveFileName(url);
        int threadNum = ParamUtil.THREAD_NUM;
        Resource result = new Resource(url,filePath,fileName,threadNum);
        return result;
    }
    //得到文件名称
    private String getSaveFileName(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

}
