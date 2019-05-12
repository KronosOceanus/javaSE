package entity;

import java.io.Serializable;
import java.util.UUID;

public class Part implements Serializable {

    //起始位置，部分长度，已下载长度，部分名称
    private int begin;
    private int length;
    private int currentLength;
    private String partName;

    public Part(int begin,int length,int currentLength){
        this.begin = begin;
        this.length = length;
        this.currentLength = currentLength;
        //随机生成
        this.partName = UUID.randomUUID() + ".part";
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public void setCurrentLength(int currentLength) {
        this.currentLength = currentLength;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
