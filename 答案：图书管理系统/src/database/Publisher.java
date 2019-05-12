package database;

public class Publisher extends ValueObject {
    //名称，电话，联系人，简介
    private String pub_name;
    private String pub_tel;
    private String pub_link_man;
    private String pub_intro;

    public Publisher(){}
    public Publisher(String pub_name,String pub_tel,
                     String pub_link_man,String pub_intro){
        this.pub_name = pub_name;
        this.pub_tel = pub_tel;
        this.pub_link_man = pub_link_man;
        this.pub_intro = pub_intro;
    }

    public String getPub_name() {
        return pub_name;
    }

    public void setPub_name(String pub_name) {
        this.pub_name = pub_name;
    }

    public String getPub_tel() {
        return pub_tel;
    }

    public void setPub_tel(String pub_tel) {
        this.pub_tel = pub_tel;
    }

    public String getPub_link_man() {
        return pub_link_man;
    }

    public void setPub_link_man(String pub_link_man) {
        this.pub_link_man = pub_link_man;
    }

    public String getPub_intro() {
        return pub_intro;
    }

    public void setPub_intro(String pub_intro) {
        this.pub_intro = pub_intro;
    }
}
