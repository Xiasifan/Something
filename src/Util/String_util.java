package Util;

public class String_util {

	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
			
		}else
			return false;
	}
	public static boolean isNotEmpty(String str){
		if(str!=null&&!"".equals(str.trim())){
			return true;
		}else
			return false;
	}
}
