package util;

import javax.mail.*;
public class Gmail extends Authenticator{

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new javax.mail.PasswordAuthentication("shdlfgh147","rih9592!@#" );
	}
}
