package net.zdsoft.common.service;

public interface MailService {

	public void send(String email,String subject,String content);
	
	public void send(String[] emails,String subject,String content);
}
