import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.ArrayList;

public class ExtensionFileFilter extends FileFilter {
    private String description = "";
    private ArrayList<String> extensions = new ArrayList<>();

    //添加文件扩展名
    public void addExtension(String extension){
        if(! extension.startsWith(".")){
            extension = "." + extension;
            //转换为小写
            extensions.add(extension.toLowerCase());
        }
    }

    //设置过滤器的描述文本
    public void setDescription(String aDescription){
        description = aDescription;
    }

    @Override
    public String getDescription() {
        return description;
    }

    //判断文件过滤器是否接受该文件
    @Override
    public boolean accept(File f) {
        if(f.isDirectory()) return true;
        String name = f.getName().toLowerCase();

        //遍历所有可接受的扩展名
        for(String extension : extensions){
            if(name.endsWith(extension)){
                return true;
            }
        }
        return false;
    }
}
