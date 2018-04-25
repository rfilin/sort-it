package sort_it;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadFile {
	static String path;
	
	public ReadFile(String parPath,String parPrefix,String parContent_type, String parSort_mode) {
		path = parPath;
	}
	
	public static File[] filesFolder (File folder){
		File[] files = (File[])null;
	    		files= new File(path).listFiles();
		return files;
	}
	
	public static String[] readFileStr(File files){
	    String[] str = null;
	    String line = null;
	    try{
	    
		if (files != null)
          {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(files),"cp1251"));
			int i = 0;
			while ((reader.readLine()) != null)
	    		{
					++i;
	    		}
			str = new String[i];
			int j = 0;
			reader.close();
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(files),"cp1251"));
			while ((line = reader2.readLine()) != null)
    		{
				str[j] = line.toString();
				++j;
    		}
			reader.close();
          }
	    }
	    catch (FileNotFoundException f)
	    {
	    	System.out.println(f.getMessage());
	    }
	    catch (UnsupportedEncodingException u)
	    {
	    	System.out.println(u.getMessage());
	    }
	    catch (IOException i)
	    {
	    	System.out.println(i.getMessage());
	    }
         return str;
	}

}
