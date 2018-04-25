package sort_it;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sort_It {
	public static String path = "";
	public static String prefix = "";
	public static String content_type = "";
	public static String sort_mode = "";
	public static String[] valid_sort_mode = {"a", "d"};
	public static String[] valid_content_type = {"i", "s"};
	
	public static void main(String[] args) {
		int strIdx;
		try {
			if (args.length!=0)
			{
				for(int i=0; i<args.length; i++)
				{
					strIdx = args[i].indexOf("=");
					if (strIdx>=0){
							switch(args[i].substring(0, strIdx)){
							case "--out-prefix":prefix=args[i].substring(strIdx+1);
							break;
							case "--content-type":content_type=args[i].substring(strIdx+1);
							break;
							case "--sort-mode":sort_mode=args[i].substring(strIdx+1);
							break;
						}
					}
					else
					{
						path = args[i];
					}
				}
			}
				if (path.isEmpty()||prefix.isEmpty()||content_type.isEmpty()||sort_mode.isEmpty())
				{
					System.out.println("Не указан один из параметров");
				}
				else{
					if (!validParam(valid_sort_mode, sort_mode)|!validParam(valid_content_type, content_type)){
						System.out.println("Неверное значение параметра --content-type или --sort-mode");
					}
					else {
						fileProcessing();
						}
				}
		}
		catch (Exception a){
			System.out.println(a.toString());
		}
	}
	
	private static void fileProcessing(){
		try{
		File folder = new File(path);
		File[] files = (File[])null;
		final ReadFile readFile = new ReadFile(path, prefix, content_type, sort_mode);
		files = readFile.filesFolder(folder);
		//String[] str = null;
		ExecutorService threadProcessFile = Executors.newFixedThreadPool(files.length);
		//for (int i = 0; i < files.length; i++){
			for (final File f : files) {
		        if (!f.isFile()) {
		            continue;
		        }
	    	if (f.isFile()&&f.getName().contains(prefix)==false){
	    		threadProcessFile.execute(new Runnable() {
	                @Override
	                public void run() {
	                	String[] str = null;
			    		if (sort_mode.equals("a"))
			    		{
			    			System.out.println("Поток: " + Thread.currentThread().getName() + ". Выполняется сортировка файла asc " + f.getAbsolutePath());
			    		}
			    		if (sort_mode.equals("d"))
			    		{
			    			System.out.println("Поток: " + Thread.currentThread().getName() + ". Выполняется сортировка файла desc " + f.getAbsolutePath());
			    		}
				    	str = readFile.readFileStr(f);
				    	DataOrderby dob = new DataOrderby(str, content_type, sort_mode, f.getName());
				    	if (dob.sortObj!=null){
							new WriteFile(path, prefix, f.getName(), dob.sortObj);
				    	}
				    	else {
				    			System.out.println("Поток: " + Thread.currentThread().getName() + ". Нет данных для сортировки");
				    		}
	                }
	    		});
	   		}
		}
			threadProcessFile.shutdown();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	private static boolean validParam(String[] validparam, String valParam){
		boolean valid = false;
			for(int v = 0; v<validparam.length; v++){
				if (validparam[v].equals(valParam)){
					valid = true;
					break;
				}
				else {
					continue;
				}
			}
		return valid;
	}

}
