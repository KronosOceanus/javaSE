import java.util.*;

//�����ü���������
public class Main {

    //�������
    private final int PLAY_NUM = 5;
    private String[] types = {"����","�ݻ�","����","����"};
    private String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private List<String> cards = new LinkedList<>();
    private String[] players = new String[PLAY_NUM];
    private List<String>[] playersCards = new List[PLAY_NUM]; //��ʾÿ�������ƣ��������鲻�������﷨��

    /**
     *��ʼ���˿���
     *��ʹ��shuffle����ʹ�����������
     */
    public void initCards()
    {
        for (String type : types) {
            for (String value : values) {
                cards.add(type + value);
            }
        }
        //�������
        Collections.shuffle(cards);
    }

    //��ҳ�ʼ��(�׳��쳣��д)
    public void initPlayer(String... names)
    {
        if(names.length > PLAY_NUM || names.length < 2)
            return ;
        else
            System.arraycopy(names, 0, players, 0, names.length);
    }

    //��ʼ��������Ƽ���
    public void initPlayerCards()
    {
        for(int i=0;i<players.length;i++)
        {
            if(players[i] != null && !players[i].equals(""))
                playersCards[i] = new LinkedList<String>();
        }
    }

    //���ȫ���˿ˣ����ԣ�
    public void showAllCards()
    {
        for(String card:cards)
        {
            System.out.print(card+"\t");
        }
        System.out.println("");
    }

    /**
     * ���˿��ƣ�ÿ��һ�ţ�
     * @param  first �������͸�˭
     */
    public void deliverCard(String first)
    {
        //ȫ�����뼯�ϣ������ü��Ϸ�����ȡ������ֻ��������ȡ������
        List<String> player = new ArrayList<>(Arrays.asList(players));
        int firstPos = player.indexOf(first);

        //���θ�֮���������˿���
        for(int i=firstPos;i<PLAY_NUM;i++)
        {
            if(players[i] != null)
            {
                playersCards[i].add(cards.get(0));
                //����֮��ɾ�������ƣ���һ��Ԫ�������Զ����0
                cards.remove(0);
            }
        }

        //���θ�֮ǰ��������˿���
        for(int i=0;i<firstPos;i++)
        {
            if(players[i] != null)
            {
                playersCards[i].add(cards.get(0));
                cards.remove(0);
            }
        }
    }

    //���������ϵ��˿���
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
        m.initPlayer("�������","�����");
        m.initCards();
        m.initPlayerCards();
        m.showAllCards();
        System.out.println("����������������");
        m.deliverCard("�����");
        m.showPlayerCards();
        /**
         * ������Ϸ����
         */
        //�ٴδӵ�����ҿ�ʼ���˿���
        m.deliverCard("�������");
        m.showPlayerCards();
    }
}
