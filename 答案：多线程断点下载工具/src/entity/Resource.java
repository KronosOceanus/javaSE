package entity;

import ctrl.TaskScreen;
import ctrl.TaskState;

import java.io.*;
import java.net.*;
import java.util.*;

//资源
public class Resource implements Serializable {

    //id，链接，保存路径，文件名
    private String id;
    private String url;
    private String filePath;
    private String fileName;

    //下载后的File对象，状态，文件大小（默认-1）
    private File saveFile;
    private String state;
    private int size = -1;

    //进度，速度，起始、使用、剩余时间，块列表
    private float process;
    private float speed;
    private int startTime;
    private int costTime;
    private int spareTime;
    private List<Part> parts;

    //线程数，上次下载大小
    private int threadNum;
    private int preLength;

    //url，保存路径，保存文件名，下载线程数
    public Resource(String url,String filePath,String fileName,int threadNum){
        this.url = url;
        this.filePath = filePath;
        this.fileName = fileName;
        this.threadNum = threadNum;

        //直接初始化（剩下再getter方法中初始化）
        this.id = UUID.randomUUID().toString();
        this.saveFile = new File(filePath + File.separator + fileName);
        this.startTime = (int)System.currentTimeMillis()/1000;
        this.parts = new ArrayList<>();
    }

    //文件大小
    public int getSize() throws IOException {
        URL resourceURL = new URL(url);
        if (this.size == -1){
            HttpURLConnection urlConnection =
                    (HttpURLConnection) resourceURL.openConnection();
            urlConnection.connect();
            //得到内容长度
            this.size = urlConnection.getContentLength();
            urlConnection.disconnect();
        }
        return this.size;
    }
    //下载进度
    public float getProcess(){
        //四舍五入
        this.process = Math.round(100.0f * getCurrentLength() / this.size);
        if (process == 100.0F){
            setState(TaskState.FINISHED);
        }
        return process;
    }
    //计算所有块的下载长度（相对核心的方法）
    public int getCurrentLength(){
        int result = 0;
        for (Part p : parts){
            result += p.getCurrentLength();
        }
        return result;
    }
    //单位KB\S
    public float getSpeed(){
        int currentLength = getCurrentLength();
        speed = (currentLength - preLength) / 1024.0f;
        preLength = currentLength;
        speed = Math.round(speed * 100) / 100.0f;
        return speed;
    }
    //已花时间（与speed相似）
    public int getCostTime(){
        costTime = (int)System.currentTimeMillis() / 1000 - startTime;
        return costTime;
    }
    //单位S
    public int getSpareTime(){
        int spareSize = this.size - getCurrentLength();
        //防止除0异常
        if (this.speed == 0){
            return this.spareTime;
        }
        return spareSize / (int)this.speed / 1000;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getSaveFile() {
        return saveFile;
    }

    public void setSaveFile(File saveFile) {
        this.saveFile = saveFile;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setProcess(float process) {
        this.process = process;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setCostTime(int costTime) {
        this.costTime = costTime;
    }

    public void setSpareTime(int spareTime) {
        this.spareTime = spareTime;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public int getPreLength() {
        return preLength;
    }

    public void setPreLength(int preLength) {
        this.preLength = preLength;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
