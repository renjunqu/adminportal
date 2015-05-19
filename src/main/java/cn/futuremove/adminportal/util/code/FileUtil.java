package cn.futuremove.adminportal.util.code;

import java.io.*;

/**
 * Created by figoxu on 15/4/13.
 */
public class FileUtil {

    public static void writeToFile(String content, String fullPath)throws IOException{
        writeToFile(content,fullPath,false,"UTF-8");
    }

    /**
     * 将一个字符串写入一个文本文件中
     * @param content String 要写入的字符串
     * @param fullPath
     * @param append boolean 是追加还是覆盖，true为追加
     * @param encoding String 文本文件的编码
     * @throws IOException
     */
    public static void writeToFile(String content, String fullPath, boolean append, String encoding)throws IOException {
        File parent = new File(new File(fullPath).getParent()); //得到父文件夹
        if(!parent.exists()){
            parent.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(new File(fullPath).getAbsolutePath(),append);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos,encoding));
        pw.print(content);
        pw.close();
        fos.close();
    }


}