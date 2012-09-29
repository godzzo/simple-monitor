package org.godzzo.simmon.send;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.godzzo.simmon.util.file.ScanFileNameItemJob;
import org.joda.time.DateTime;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class DataSenderByMail extends ScanFileNameItemJob {
	public static final String DATE_FORMAT = "yyyy-MM-dd hh.mm.ss";
	public static final String SUBJECT_BEGIN = "Data Sender : ";

	private Log log = LogFactory.getLog(getClass());
	
	private JavaMailSender mailSender;
	private String fromEmail;
	private String toEmail;
	private String hostName;
	
	private String cannonicalSourcePath;
	
	private ZipOutputStream zipOut;
	private File zipFile;
	private FileOutputStream fileOutputStream;
	
	private List<File> files = new ArrayList<File>();
	
	@Override
	protected void invokeOnStart() throws Exception {
		String path = new File(getSourcePath()).getCanonicalPath();
		
		log.debug("INVOKE ON START: "+path);
		
		if (!path.endsWith(File.separator)) {
			path += File.separator;
		}
		
		setCannonicalSourcePath(path);
		
		setZipFile(File.createTempFile("data-sender-", ".zip"));
		
		log.debug("START ZipFile: "+getZipFile().getCanonicalPath());
		
		setFileOutputStream(new FileOutputStream(getZipFile()));
		
		setZipOut(new ZipOutputStream(getFileOutputStream()));
	}
	
	@Override
	protected void invokeOnFinish() throws Exception {
		if (getFiles().size() > 0) {
			getZipOut().close();
			
			log.debug("FINISH ZipFile: "+getZipFile().getCanonicalPath());
			
			sendMail();
		} else {
			log.debug("File not found!");
			
			getFileOutputStream().close();
		}
		
		deleteFiles();
	}
	
	private void sendMail() throws Exception {
		MimeMessage message = getMailSender().createMimeMessage();
	
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
	
		String now = DateTime.now().toString(DATE_FORMAT);
		String subject = SUBJECT_BEGIN + getHostName() + " : " + now;
		
		helper.setFrom(getFromEmail());
		helper.setTo(getToEmail());
		helper.setSubject(subject);
		helper.setText(subject);

		log.debug(String.format("MAKE MAIL: f:%s, t:%s, s:%s, a:%s", 
				getFromEmail(),
				getToEmail(),
				subject,
				getZipFile().getName()
		));

		helper.addAttachment(getZipFile().getName(), 
				new FileSystemResource(getZipFile()));
		
		getMailSender().send(message);
		
		log.debug("MAIL SENT");
	}

	private void deleteFiles() {
		for (File item : getFiles()) {
			item.delete();
		}
		
		getFiles().clear();
		
		getZipFile().delete();
	}
	
	@Override
	protected void invokeItem(String name, File item) throws Exception {
		String path = item.getCanonicalPath().replace(getCannonicalSourcePath(), "");
		
		log.debug(String.format("INVOKE ITEM (%s): %s", name, path));
		
		getZipOut().putNextEntry(new ZipEntry(path));
		
		FileUtils.copyFile(item, getZipOut());
		
		getFiles().add(item);
		
		getZipOut().closeEntry();
		
		log.debug("ENTRY CLOSED.");
	}

	public ZipOutputStream getZipOut() {
		return zipOut;
	}

	public void setZipOut(ZipOutputStream zipOut) {
		this.zipOut = zipOut;
	}

	public File getZipFile() {
		return zipFile;
	}

	public void setZipFile(File zipFile) {
		this.zipFile = zipFile;
	}

	public String getCannonicalSourcePath() {
		return cannonicalSourcePath;
	}

	public void setCannonicalSourcePath(String cannonicalSourcePath) {
		this.cannonicalSourcePath = cannonicalSourcePath;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public FileOutputStream getFileOutputStream() {
		return fileOutputStream;
	}

	public void setFileOutputStream(FileOutputStream fileOutputStream) {
		this.fileOutputStream = fileOutputStream;
	}
}
