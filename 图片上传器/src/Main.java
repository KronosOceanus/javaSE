import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Main {

    JFrame jf = new JFrame("图片管理程序");
    private static Connection conn;
    private static PreparedStatement insert;
    private static PreparedStatement query;
    private static PreparedStatement queryAll;
    private DefaultListModel<ImageHolder> imageModel =
        new DefaultListModel<>();
    private JList<ImageHolder> imageList = new JList<>(imageModel);
    private JTextField filePath = new JTextField(26);
    private JButton browserBn = new JButton("...");
    private JButton uploadBn = new JButton("上传");
    private JLabel imageLabel = new JLabel();

    //创建文件选择器与过滤器
    JFileChooser chooser = new JFileChooser(".");
    ExtensionFileFilter filter = new ExtensionFileFilter();

    static{
        try
        {
            Properties props = new Properties();
            props.load(new FileInputStream("mysql.ini"));
            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String pass = props.getProperty("pass");

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,user,pass);
            //插入后可以返回自动生成的主键
            insert = conn.prepareStatement("insert into img_test"
                + "values(null,?,?)",Statement.RETURN_GENERATED_KEYS);
            //查询指定图片，所有图片
            query = conn.prepareStatement("select  img_data from img_test"
                + "where img_id = ?");
            queryAll = conn.prepareStatement("select img_id,"
                +"img_name from img_test");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void init() throws Exception{

        //初始化文件过滤器
        filter.addExtension("jpg");
        filter.addExtension("jpeg");
        filter.addExtension("gif");
        filter.addExtension("png");
        filter.setDescription("图片文件(*.jpg,*.jpeg,*.gif,*.png)");
        //禁止文件列表下拉显示“所有文件”选项
        chooser.setAcceptAllFileFilterUsed(false);

        //初始化程序界面
        fillListModel();
        filePath.setEditable(false);
        //只能单选
        imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JPanel jp = new JPanel();
        jp.add(filePath);
        jp.add(browserBn);
        browserBn.addActionListener(event -> {
            //显示文件对话框
            int result = chooser.showDialog(jf,"浏览图片文件上传");
            if (result == JFileChooser.APPROVE_OPTION){
                filePath.setText(chooser.getSelectedFile().getParent());
            }
        });
        jp.add(uploadBn);
        uploadBn.addActionListener(avt -> {
            //如果上传文件的文本框有内容
            if (filePath.getText().trim().length() > 0){
                //将指定文件保存到数据库
                upload(filePath.getText());
                filePath.setText("");
            }
        });
        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());
        left.add(new JScrollPane(imageLabel),BorderLayout.CENTER);
        left.add(jp,BorderLayout.SOUTH);
        jf.add(left);
        imageList.setFixedCellWidth(160);
        jf.add(new JScrollPane(imageList),BorderLayout.EAST);

        imageList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 2){
                    ImageHolder cur = (ImageHolder)imageList.getSelectedValue();
                    try{
                        //显示选中项对应的Image
                        showImage(cur.getId());
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });

        jf.setSize(620,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    //查找img_test填充ListModel
    public void fillListModel() throws SQLException{
        try(
                ResultSet rs = queryAll.executeQuery();
                ){
            //清除所有元素
            imageModel.clear();
            //把查询记录添加到ListModel中
            while(rs.next()){
                imageModel.addElement(new ImageHolder(rs.getInt(1)
                        ,rs.getString(2)));
            }
        }
    }

    //将指定图片放入数据库
    public void upload(String fileName){
        //截取文件名
        String imageName = fileName.substring(fileName.lastIndexOf('\\') + 1
                ,fileName.lastIndexOf('.'));
        File f = new File(fileName);
        try(
                InputStream is = new FileInputStream(f);
                ){
            insert.setString(1,imageName);
            insert.setBinaryStream(2,is,(int)f.length());
            int affect = insert.executeUpdate();
            if (affect == 1){
                //更新ListModel，让JList显示最新的图片列表
                fillListModel();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据ID显示图片
    public void showImage(int id)throws Exception{
        query.setInt(1,id);
        try(
                ResultSet rs = query.executeQuery();
                ){
            if(rs.next()){
                //取出列与其中的数据
                Blob imgBlob = rs.getBlob(1);
                ImageIcon icon = new ImageIcon(imgBlob.getBytes(1L,(int)imgBlob.length()));
                imageLabel.setIcon(icon);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        new Main().init();
    }
}
