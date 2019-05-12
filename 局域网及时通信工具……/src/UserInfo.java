import java.net.*;

/**
 * 封装用户信息
 * 用户名，图标，对应的SocketAddress，失去联系的次数
 */
public class UserInfo {

    private String icon;
    private String name;
    private SocketAddress address;
    private int lost;

    //根据address唯一标识用户，重写equals，hashCode方法
    public boolean equals(Object obj){
        if (obj != null && obj.getClass() == UserInfo.class){
            UserInfo target = (UserInfo)obj;
            return this.address.equals(target.address);
        }
        return false;
    }
    public int hashCode(){
        return address.hashCode();
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SocketAddress getAddress() {
        return address;
    }

    public void setAddress(SocketAddress address) {
        this.address = address;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }
}
