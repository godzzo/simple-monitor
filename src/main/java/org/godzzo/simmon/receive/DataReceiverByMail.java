package org.godzzo.simmon.receive;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.godzzo.simmon.send.DataSenderByMail;
import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.integration.mail.MailReceiver;
import org.springframework.scheduling.quartz.QuartzJobBean;

/*
 * receive mail
 * find host stage slot
 * unzip it to the stage
 * move to the host close area
 */
public class DataReceiverByMail extends QuartzJobBean {
	private Log log = LogFactory.getLog(getClass());

	private String closedPath;
	private String workPath;

	private MailReceiver receiver;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		try {
			recieve();
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}

	public void recieve() throws Exception {
		Message[] messages = getReceiver().receive();

		for (Message message : messages) {
			Object content = message.getContent();

			SubjectInfo info = new SubjectInfo(message.getSubject());

			if (info.isValid()) {
				if (content instanceof Multipart) {
					Multipart multipart = (Multipart) content;

					log.debug("COUNT: " + multipart.getCount());
					log.debug("Multipart.ContentType: "
							+ multipart.getContentType());

					receiveMultipart(info, multipart);
				}
			}

			log.debug(message.getSubject());
		}
	}

	private void receiveMultipart(SubjectInfo info, Multipart multipart)
			throws Exception {
		for (int i = 0; i < multipart.getCount(); i++) {
			BodyPart bodyPart = multipart.getBodyPart(i);

			if (bodyPart.getFileName() != null
					&& bodyPart.getFileName().endsWith(".zip")) {
				log.debug(i + ". FileName: " + bodyPart.getFileName());

				parseZipStream(info,
						new ZipInputStream(bodyPart.getInputStream()));
			}
		}
	}

	private void parseZipStream(SubjectInfo info, ZipInputStream zipStream)
			throws Exception {
		ZipEntry entry;

		while ((entry = zipStream.getNextEntry()) != null) {
			String workPath = makeWorkPath(info, entry);
			String closePath = makeClosePath(info, entry);

			File workFile = new File(workPath);
			File closeFile = new File(closePath);

			workFile.getParentFile().mkdirs();

			FileOutputStream workOutput = new FileOutputStream(workFile);
			IOUtils.copy(zipStream, workOutput);
			workOutput.close();

			FileUtils.moveFile(workFile, closeFile);
		}

		zipStream.close();
	}

	private String makeClosePath(SubjectInfo info, ZipEntry entry) {
		String name = normalizeEntryName(entry);
		
		return getClosedPath() + File.separator + info.getHost()
				+ "_" + name;
	}

	private String makeWorkPath(SubjectInfo info, ZipEntry entry) {
		String name = normalizeEntryName(entry);
		
		return getWorkPath() + File.separator + info.getHost()
				+ "_" + name;
	}

	private String normalizeEntryName(ZipEntry entry) {
		String name = entry.getName().replace('/', File.separatorChar);
		name = name.replace('\\', File.separatorChar);
		return name;
	}

	public String getClosedPath() {
		return closedPath;
	}

	public void setClosedPath(String closedPath) {
		this.closedPath = closedPath;
	}

	public String getWorkPath() {
		return workPath;
	}

	public void setWorkPath(String workPath) {
		this.workPath = workPath;
	}

	public MailReceiver getReceiver() {
		return receiver;
	}

	public void setReceiver(MailReceiver receiver) {
		this.receiver = receiver;
	}

	static public class SubjectInfo {
		private String host;
		private DateTime time;
		private boolean valid = false;

		public SubjectInfo(String subject) throws Exception {
			if (subject.startsWith(DataSenderByMail.SUBJECT_BEGIN)) {
				String[] tags = subject.split(" : ");

				if (tags.length == 3) {
					host = tags[1];

					Date date = new SimpleDateFormat(
							DataSenderByMail.DATE_FORMAT).parse(tags[2]);

					time = new DateTime(date.getTime());

					valid = true;
				}
			}
		}

		public String getHost() {
			return host;
		}

		public DateTime getTime() {
			return time;
		}

		public boolean isValid() {
			return valid;
		}
	}
}
