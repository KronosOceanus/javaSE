import ctrl.Holder;
import download.NewTask;
import util.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        NewTask nt1 = new NewTask("http://issuecdn.baidupcs.com/issue/netdisk/" +
                "yunguanjia/BaiduNetdisk_6.7.2.16.exe");
        NewTask nt2 = new NewTask("https://dldir1.qq.com/qqfile/qq/" +
                "QQ9.1.0/24712/QQ9.1.0.24712.exe");

        Scanner sc = new Scanner(System.in);
        String line = null;
        label:
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            switch (line) {
                case "p":
                    Holder.gc.pauseAll();
                    break;
                case "r":
                    Holder.gc.resumeAll();
                    break;
                case "s":
                    SerializeUtil.serializable();
                    break;
                case "rs":
                    SerializeUtil.reverseSer();
                    break;
                case "cs":
                    Holder.gc.getResourceName(Holder.ts.getDownloadings());
                    break;
                case "ok":
                    break label;
            }
        }
    }

}
