package entity;

public class Type extends ValueObject{
    //名称，简介
    private String type_name;
    private String type_intro;

    public Type(){};
    public Type(String type_name,String type_intro){
        this.type_name = type_name;
        this.type_intro = type_intro;
    };

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getType_intro() {
        return type_intro;
    }

    public void setType_intro(String type_intro) {
        this.type_intro = type_intro;
    }
}
