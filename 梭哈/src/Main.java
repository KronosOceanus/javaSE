import java.util.*;

//手牌用集合来储存
public class Main {

    //最多人数
    private final int PLAY_NUM = 5;
    private String[] types = {"方块","草花","红心","黑桃"};
    private String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private List<String> cards = new LinkedList<>();
    private String[] players = new String[PLAY_NUM];
    private List<String>[] playersCards = new List[PLAY_NUM]; //表示每个人手牌（集合数组不用菱形语法）

    /**
     *初始化扑克牌
     *并使用shuffle方法使他们随机排序
     */
    public void initCards()
    {
        for (String type : types) {
            for (String value : values) {
                cards.add(type + value);
            }
        }
        //随机排序
        Collections.shuffle(cards);
    }

    //玩家初始化(抛出异常改写)
    public void initPlayer(String... names)
    {
        if(names.length > PLAY_NUM || names.length < 2)
            return ;
        else
            System.arraycopy(names, 0, players, 0, names.length);
    }

    //初始化玩家手牌集合
    public void initPlayerCards()
    {
        for(int i=0;i<players.length;i++)
        {
            if(players[i] != null && !players[i].equals(""))
                playersCards[i] = new LinkedList<String>();
        }
    }

    //输出全部扑克（测试）
    public void showAllCards()
    {
        for(String card:cards)
        {
            System.out.print(card+"\t");
        }
        System.out.println("");
    }

    /**
     * 派扑克牌（每人一张）
     * @param  first 最先派送给谁
     */
    public void deliverCard(String first)
    {
        //全部放入集合，再利用集合方法获取索引（只是用来获取索引）
        List<String> player = new ArrayList<>(Arrays.asList(players));
        int firstPos = player.indexOf(first);

        //依次给之后的玩家派扑克牌
        for(int i=firstPos;i<PLAY_NUM;i++)
        {
            if(players[i] != null)
            {
                playersCards[i].add(cards.get(0));
                //派牌之后删除该张牌，下一个元素索引自动变成0
                cards.remove(0);
            }
        }

        //依次给之前的玩家派扑克牌
        for(int i=0;i<firstPos;i++)
        {
            if(players[i] != null)
            {
                playersCards[i].add(cards.get(0));
                cards.remove(0);
            }
        }
    }

    //输出玩家手上的扑克牌
    public void showPlayerCards()
    {
        for(int i=0;i<PLAY_NUM;i++)
        {
            if(players[i] != null)
            {
                System.out.print(players[i]+":");
                for(String card:playersCards[i])
                {
                    System.out.print(card+"\t");
                }
                System.out.println("");
            }
        }
    }

    public static void main(String[] args)
    {
        Main m = new Main();
        m.initPlayer("电脑玩家","孙悟空");
        m.initCards();
        m.initPlayerCards();
        m.showAllCards();
        System.out.println("……………………");
        m.deliverCard("孙悟空");
        m.showPlayerCards();
        /**
         * 增加游戏规则
         */
        //再次从电脑玩家开始派扑克牌
        m.deliverCard("电脑玩家");
        m.showPlayerCards();
    }
}
