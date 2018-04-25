package sort_it;

public class DataOrderby {
	String[] str;
	public long[] sortInt;
	public String[] sortStr;
	public Object[] sortObj;
	
	public DataOrderby(String[] str, String content_type,String sort_mode, String fileName) {
		try{
			long[] num;
				if (content_type.equals("i"))
				{
					if (validInt(str)){
					num = new long[str.length];
					for (int j = 0; j < str.length; j++){
						num[j] = Long.parseLong(str[j].trim()); // Double.parseDouble(str[j].trim());// Integer.parseInt(str[j].trim());
					}
					sortInt = intSort(num, sort_mode);
					sortObj = new Object[sortInt.length];
			    		for (int n = 0; n < sortInt.length; n++){
			    			sortObj[n] = (Object) sortInt[n];
			    		}
					}
					else {
						System.out.println("Строки из файла " + fileName + " не преобразуется в число");
					}
				}
		}
		catch (NumberFormatException n){
			System.out.println(n.toString());
		}
		if (content_type.equals("s"))
			{
				sortStr = strSort(str, sort_mode);
				sortObj = new Object[sortStr.length];
	    		for (int n = 0; n < sortStr.length; n++){
	    			sortObj[n] = (Object) sortStr[n];
	    		}
			}
	}
	
	//Метод для сортировки строк
	public static String[] strSort(String[] strsort, String sort_mode) {
		String temp;
		if (sort_mode.equals("a")){
		for(int j = 0; j < strsort.length - 1; j++){
			for (int i = j + 1; i < strsort.length;i++){
				if (strsort[i].compareToIgnoreCase(strsort[j]) < 0){
					temp = strsort[j];
					strsort[j] = strsort[i];
					strsort[i] = temp;
				}
			}
		}
		}
		else {
			for(int j = 0; j < strsort.length - 1; j++){
				for (int i = j + 1; i < strsort.length;i++){
					if (strsort[i].compareToIgnoreCase(strsort[j]) > 0){
						temp = strsort[j];
						strsort[j] = strsort[i];
						strsort[i] = temp;
					}
				}
		}
	    }
		return strsort;
	}
	
	//Метод для сортировки чисел
	public static long[] intSort(long[] strsort,String sort_mode) {
		long temp;
		int j;
	    if (sort_mode.equals("a")){
	    for(int i = 0; i < strsort.length - 1; i++){
	        if (strsort[i] > strsort[i + 1]) {
	           temp = strsort[i + 1];
	           strsort[i + 1] = strsort[i];      
	           j = i;
	           while (j > 0 && temp < strsort[j - 1]) {
	        	   strsort[j] = strsort[j - 1];               
	               j--;
	           }
	           strsort[j] = temp;             
	        }        
	    }
	    }
	    else {
		    for(int i = 0; i < strsort.length - 1; i++){
		        if (strsort[i] < strsort[i + 1]) {
		           temp = strsort[i + 1];
		           strsort[i + 1] = strsort[i];      
		           j = i;
		           while (j > 0 && temp > strsort[j - 1]) {
		        	   strsort[j] = strsort[j - 1];               
		               j--;
		           }
		           strsort[j] = temp;             
		        }        
		    }
	    }

	    return strsort;
	}
	
	private static boolean validInt(String[] str)
	{
		boolean valid = true;
		String regex;
		regex = "((-|\\+)?[0-9]+(\\.[0-9]+)?)+";
		for (int s = 0; s < str.length; s++){
			valid = str[s].trim().matches(regex);
			if (!valid){
				break;
			}
			else {
				continue;
			}
		}
		return valid;
	}
}
