import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //��ǰ·���½��ļ�(ָ��·������)
        File file = new File(".");
        file.createNewFile();
        System.out.println(file.getName());
        //��ȡ���·���ĸ�·�����ܻ�������null��
        System.out.println(file.getParent());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsoluteFile().getParent());

        //��ǰ·���½���ʱ�ļ�(�ļ�������׺�������ĸ��ļ�ָ����)
        File tmpFile = File.createTempFile("aaa",".txt",file);
        //ָ��jvm�˳�ʱɾ���ļ�
        tmpFile.deleteOnExit();

        File newFile = new File(System.currentTimeMillis()+"");
        System.out.println("newFile�Ƿ����"+newFile.exists());
        newFile.createNewFile();
        //����ʧ�ܣ���ΪnewFile�Ѿ�����
        System.out.println(newFile.mkdir());
        newFile.deleteOnExit();

        //ʹ��list�����г���ǰ·���µ������ļ���·������������������������������
        String[] fileList = file.list();
        System.out.println("======��ǰ�����ļ�����·�����¡�����������");
        assert fileList != null;
        for(String fileName:fileList){
            System.out.println(fileName);
        }

        //ʹ��listRoots��̬�����г����еĴ���·��
        File[] roots = File.listRoots();
        System.out.println("======ϵͳ���и�·�����¡�����������");
        for(File root:roots){
            System.out.println(root);
        }

        //�ļ�������
        //list������ָ�����˹��򣬲������ͣ�File,String����������.java��β�����ߣ��ļ���Ӧһ��·����
        String[] nameList = file.list((dir,name) -> name.endsWith(".java") || new File(name).isDirectory());
        assert nameList != null;
        for(String name:nameList){
            System.out.println(name);
        }
    }
}