package validation;

public class CheckUserPass{
	
	public static String checkUserPass(String user, String pass){	
	String result = "";
	
	String usercheck = UserNameRecognizer.checkForValidUserName(user);
	String passwordcheck = PasswordEvaluator.evaluatePassword(pass);
	
	if(!usercheck.equals("")){
		result += "USERNAME ISSUES:" + usercheck + "\n";
	}
	
	if(!passwordcheck.equals("")){
		result += "PASSWORD ISSUES:" + passwordcheck + "\n";
	}
	
	
	return result;
	}
}
