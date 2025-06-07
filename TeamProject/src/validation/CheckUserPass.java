package validation;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CheckUserPass{
	
	public static Boolean checkUserPass(String user, String pass){
	
	Boolean returnable = true;
		
	String result = "";
	
	String usercheck = UserNameRecognizer.checkForValidUserName(user);
	String passwordcheck = PasswordEvaluator.evaluatePassword(pass);
	
	if(!usercheck.equals("") || !passwordcheck.equals("")){
		returnable = false;
		
		result += "USERNAME ISSUES:" + usercheck;
		result += "\nPASSWORD ISSUES:" + passwordcheck;
		
		Alert validation = new Alert(AlertType.INFORMATION);
		validation.setTitle("*** WARNING ***");
		validation.setHeaderText("Password/Username Issue(s):");
		validation.setContentText(result);
		validation.showAndWait();
	}
	
	return returnable;
	}
}
