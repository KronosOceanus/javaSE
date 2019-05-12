import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class Main {

    JFrame jf = new JFrame("查询执行器");
    private JScrollPane scrollPane;
    private JButton execBn = new JButton("查询");
    //输入查询语句的文本框
    private JTextField sqlField = new JTextField(45);
    private static Connection conn;
    private static Statement stmt;

    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("mysql.ini"));
            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String pass = props.getProperty("pass");

            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        JPanel top = new JPanel();
        top.add(new JLabel("输入查询语句："));
        top.add(sqlField);
        top.add(execBn);
        execBn.addActionListener(new ExceListener());
        jf.add(top, BorderLayout.NORTH);
        jf.setSize(1000, 480);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    class ExceListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (scrollPane != null) {
                jf.remove(scrollPane);
            }
            try (
                    ResultSet rs = stmt.executeQuery(sqlField.getText())
            ) {
                ResultSetMetaData rsmd = rs.getMetaData();
                //数组实现，同步集合，不包括框架方法
                Vector<String> columnNames = new Vector<>();
                Vector<Vector<String>> data = new Vector<>();

                //把ResultSet的所有列名添加到Vector
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    columnNames.add(rsmd.getColumnName(i + 1));
                }

                //把ResultSet的所有记录添加到Vector
                while (rs.next()) {
                    Vector<String> v = new Vector<>();
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        v.add(rs.getString(i + 1));
                    }
                    data.add(v);
                }
                JTable table = new JTable(data, columnNames);
                scrollPane = new JScrollPane(table);
                jf.add(scrollPane);
                jf.validate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Main().init();
    }
}
