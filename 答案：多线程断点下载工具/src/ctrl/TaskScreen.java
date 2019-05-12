package ctrl;

import entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 保存所有任务信息
 * 任务筛选
 * 序列化
 */
public class TaskScreen implements Serializable {

    //当前所有任务（新建下载加入该集合）
    public List<Resource> resources = new ArrayList<>();

    //筛选任务，得到资源
    public List<Resource> getDownloadings(){
        return getResources(TaskState.DOWNLOADING);
    }
    public List<Resource> getFaileds(){
        return getResources(TaskState.FAILED);
    }
    public List<Resource> getFinisheds(){
        return getResources(TaskState.FINISHED);
    }
    public List<Resource> getPauses(){
        return getResources(TaskState.PAUSE);
    }

    private List<Resource> getResources(String state){
        List<Resource> result = new ArrayList<>();
        for (Resource r : resources){
            //比较字符串判断状态
            if (r.getState().equals(state)){
                result.add(r);
            }
        }
        return result;
    }
}
