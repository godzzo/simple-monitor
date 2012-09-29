package org.godzzo.simmon.receive.util;

import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.mail.Pop3MailReceiver;

public class CustomMailReceiver extends Pop3MailReceiver {
	private Log log = LogFactory.getLog(getClass());

	public CustomMailReceiver() {
		super();
	}

	public CustomMailReceiver(String host, int port, String username,
			String password) {
		super(host, port, username, password);
	}

	public CustomMailReceiver(String host, String username, String password) {
		super(host, username, password);
	}

	public CustomMailReceiver(String url) {
		super(url);
	}

	@Override
	protected void deleteMessages(Message[] messages) throws MessagingException {
		super.deleteMessages(messages);

		// expunge deleted mails, and make sure we've retrieved them before
		// closing the folder
		for (int i = 0; i < messages.length; i++) {
			try {
				new MimeMessage((MimeMessage) messages[i]);
			} catch (MessageRemovedException e) {
				log.error(String.format(
						"error in expunge deleted mail: %d (%s)", i,
						e.getMessage()));
			}
		}
	}
}
