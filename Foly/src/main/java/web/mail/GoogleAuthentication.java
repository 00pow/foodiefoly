package web.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthentication extends Authenticator{		
		PasswordAuthentication passAuth;
	    
	    public GoogleAuthentication() {
	    	passAuth = new PasswordAuthentication("foodiefoly@gmail.com","eszcvfmozpsnwzai");
	    }
	    
	    public PasswordAuthentication getPasswordAuthentication() {
	        return passAuth;
	    }
	

}
