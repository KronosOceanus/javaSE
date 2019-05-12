//为内容添加特定字符
public interface CrazyitProtocol {

    int PORTOCOL_LEN = 2;

    //协议字符串，服务器和客户端交换的信息都应该在前、后增加这种字符串
    /**
     * 公聊
     * 用户名
     * 登陆成功
     * 用户名重复
     * 私聊
     * 分离用户名与内容
     */
    String MSG_ROUND = "&γ";
    String USER_ROUND = "∏∑";
    String LOGIN_SUCCESS = "1";
    String NAME_REP = "-1";
    String PRIVATE_ROUND = "★【";
    String SPLIT_SIGN = "※";
}
