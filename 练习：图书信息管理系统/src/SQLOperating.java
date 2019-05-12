import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

/**
 * 对应表books_table
 * 书名，价格，作者，出版社，封面（图片路径）
 */
public class SQLOperating {

    private String driver;
    private String url;
    private String user;
    private String pass;
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    //加载属性文件
    public void initParam(String paramFile) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(paramFile));
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");
        Class.forName(driver);
        conn = DriverManager.getConnection(url,user,pass);
    }

    //一本书插入（包括封面图片，参数为路径）
    //耦合度较高………………………………
    public void insert(Book book) throws Exception {
        String sql = "insert into books_table values(?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);

        //插入前4条信息
        if (book.getName() != null){
            pstmt.setString(1,book.getName());
        }else {
            throw new RuntimeException("书名（主键列）不能为null！");
        }
        if (book.getPrice() != 0){
            pstmt.setInt(2,book.getPrice());
        }else {
            pstmt.setString(2,null);
        }
        if (book.getAuthor() != null){
            pstmt.setString(3,book.getAuthor());
        }else {
            pstmt.setString(3,null);
        }
        if (book.getPublish() != null){
            pstmt.setString(4,book.getPublish());
        }else {
            pstmt.setString(4,null);
        }
        //设置数量
        pstmt.setInt(6,1);

        //插入图片
        if(book.getFilePath() != null){
            insertImg(5,book.getFilePath());
        }else {
            pstmt.setString(5,null);
        }
        pstmt.executeUpdate();
    }

    //删除行
    public int delete(String... condition)throws Exception{
        String sql = "delete from books_table";
        judgeConditionExist(sql,condition);
        return pstmt.executeUpdate();
    }

    //多列查询
    public ResultSet select(String[] paramColumnNames,String... condition) throws Exception{
        //通过StringBuilder灵活变换sql语句
        StringBuilder sqlB = new StringBuilder("select ?,?,?,?,? from books_table");
        for(int i=paramColumnNames.length;i<5;i++){
            sqlB.delete(sqlB.lastIndexOf("?") - 1,sqlB.lastIndexOf("?") + 1);
        }
        for(int i=0;i<paramColumnNames.length;i++){
            sqlB.replace(sqlB.indexOf("?"),sqlB.indexOf("?") + 1,paramColumnNames[i]);
        }
        String sql = sqlB.toString();
        //设置条件
        pstmt = conn.prepareStatement(sql);
        if (condition.length == 1){
            pstmt = conn.prepareStatement(sql + " where " +condition[0]);
        }
        return pstmt.executeQuery();
    }

    //全表查询
    public ResultSet selects(String... condition) throws Exception {
        String sql = "select * from books_table";
        judgeConditionExist(sql,condition);
        return pstmt.executeQuery();
    }

    //多列修改
    public int update(Map<Integer,String> content, String... condition)
            throws Exception
    {
        StringBuilder sqlB = new StringBuilder("update books_table ");
        if (content.size() != 0){
            sqlB.append("set ");
            for(Integer key : content.keySet()){
                    sqlB.append(ConditionSetter.columnNames[key - 1] + " = ?,");
            }
            sqlB.delete(sqlB.lastIndexOf(","),sqlB.lastIndexOf(",") + 1);
        }
        String sql = sqlB.toString();

        judgeConditionExist(sql,condition);
        //控制set参数
        int paramIndex = 0;
        for(Integer key : content.keySet()) {
            paramIndex ++;
            if (key == 5){
                insertImg(paramIndex,content.get(key));
            }
            else if(key == 2 || key == 6){
                pstmt.setInt(paramIndex,Integer.parseInt(content.get(key)));
            }
            else {
                pstmt.setString(paramIndex,content.get(key));
            }
        }
        return pstmt.executeUpdate();
    }




    //输出结果集
    void printResultSet(ResultSet rs)throws Exception{
        ResultSetMetaData rsmd = rs.getMetaData();
        for(int i=0;i<rsmd.getColumnCount();i++){
            System.out.print(rsmd.getColumnName(i+1) + "\t");
        }
        System.out.println();
        while(rs.next()){
            for(int i=0;i<rsmd.getColumnCount();i++){
                System.out.print(rs.getString(i+1) + "\t");
            }
            System.out.println();
        }
    }

    //判断是否有条件并且得到pstmt
    private void judgeConditionExist(String sql,String[] condition)
        throws Exception
    {
        pstmt = conn.prepareStatement(sql);
        if (condition.length == 1){
            pstmt = conn.prepareStatement(sql + " where " +condition[0]);
        }
    }

    //插入图片
    private void insertImg(int paramIndex,String pathName) throws Exception{
        //截取文件名
        String fileName = pathName.substring(
                (pathName.lastIndexOf('\\') + 1));
        File f = new File(fileName);
        try(
                InputStream is = new FileInputStream(f);
        ){
            pstmt.setBinaryStream(paramIndex,is,(int)f.length());
        }
    }


    /**
     * X
        //condition数组设置where条件
        private String setCondition(String... condition) throws Exception{
            String cond = "";
            if (condition.length != 0) {
                cond += " where ";
                for (int i = 0; i < condition.length; i++) {
                    cond += condition[i];
                    if(i != condition.length -1){
                        cond += ",";
                    }
                }
            }
            return cond;
        }
     */
}
