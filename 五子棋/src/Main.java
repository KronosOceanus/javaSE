import java.io.*;
import java.util.*;

public class Main {

    //定义棋盘大小
    private static int BOARD_SIZE = 15;
    //棋盘
    private String[][] board;
    //初始化
    public void initBoard()
    {
        board = new String[BOARD_SIZE][BOARD_SIZE];
        for(int i=0;i<BOARD_SIZE;i++)
        {
            for(int j=0;j<BOARD_SIZE;j++)
                board[i][j] = "╋";
        }
    }
    //输出棋盘
    public void printBoard()
    {
        for(int i=0;i<BOARD_SIZE;i++)
        {
            String s = "";
            for(int j=0;j<BOARD_SIZE;j++) {
                s = s + board[i][j];
            }
            System.out.println(s);
        }
    }
    //电脑下棋
    public void computer()
    {
        int cxPos,cyPos;
        //随机数用法
        Random rand = new Random(System.currentTimeMillis());
        do {
            //生成0-14的随机数
            cxPos = rand.nextInt(14);
            cyPos = rand.nextInt(14);
            this.board[cxPos][cyPos] = "◯";
        }
        while(this.board[cxPos][cyPos].equals("●"));
    }

    //异常处理有待改进
    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.initBoard();
        m.printBoard();
        //获取系统输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = null;
        while((inputStr = br.readLine()) != null)
        {
            //以空格为分隔符
            try
            {
                String[] posStrArr = inputStr.split(" ");
                int xPos = Integer.parseInt(posStrArr[0]);
                int yPos = Integer.parseInt(posStrArr[1]);
                if(m.board[xPos - 1][yPos - 1].equals("●")) {
                    System.out.println("该坐标已经有棋了，请重新输入");
                    continue;
                }
                m.board[xPos - 1][yPos - 1] = "●";
                m.computer(); //电脑下棋
                m.printBoard();
                System.out.println("输入下棋的坐标x y");
            }
            catch (ArrayIndexOutOfBoundsException aiofbe)
            {
                System.out.println("输入数据异常,请重新输入");
                continue;
            }
            catch(Exception e)
            {
                System.out.println("输入应该为数字坐标x y，请重新输入");
                continue;
            }
        }
    }
}
