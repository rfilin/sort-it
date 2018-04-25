package sort_it;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {

	public WriteFile(String path, String prefix, String fileName, Object[] sortStr) {
		File file = new File(path,prefix + fileName);
		//if (!file.exists()) {
            try {
    			 file.createNewFile();
                FileOutputStream f = new FileOutputStream(file);
                PrintWriter pw = new PrintWriter(f);
                for (int i = 0; i<sortStr.length; i++){
                	pw.println(sortStr[i]);
                }
                	System.out.println("Файл " + path + fileName + " отсортирован " + " и сохранен в " + path + prefix + fileName);
                pw.flush();
                pw.close();
                f.close();
            } catch (IOException e) {
                e.getMessage();
            }
	}

}
