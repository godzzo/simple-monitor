package org.godzzo.simmon.sampler.output;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.godzzo.simmon.sampler.output.util.FileHandler;
import org.junit.Assert;

public class FileOutput implements Output {
	private Log log = LogFactory.getLog(getClass());

	private String openPath;
	private String closedPath;
	private String archivePath;
	private String timeMask = "yyyy.MM.dd.hh.mm.ss";

	private Map<String, FileHandler> data = new HashMap<String, FileHandler>();

	@Override
	public void write(String name, String line) throws Exception {
		FileHandler handler;

		handler = checkHandler(name);

		handler.write(line);

		log.debug(name + " - write: " + line);
	}

	@Override
	public void cleanUp() throws Exception {
		checkHandlers();
	}

	private void checkHandlers() throws Exception {
		for (Map.Entry<String, FileHandler> item : data.entrySet()) {
			checkHandler(item.getKey(), false);
		}
	}

	private FileHandler checkHandler(String name) throws Exception {
		return checkHandler(name, true);
	}

	private FileHandler checkHandler(String name, boolean create)
			throws Exception {
		FileHandler handler = null;

		if (!data.containsKey(name)) {
			moveRemaining(name);

			if (create) {
				handler = createHandler(name);
			}
		} else {
			handler = data.get(name);
		}

		Assert.assertNotNull("Handler is not initialized!", handler);

		if (isFilled(handler)) {
			closeHandler(name, handler);

			if (create) {
				handler = createHandler(name);
			}
		}

		return handler;
	}

	protected boolean isFilled(FileHandler handler) {
		return false;
	}

	private FileHandler createHandler(String name) {
		String path = makeOpenPath(name);

		FileHandler handler = new FileHandler(path, getTimeMask());

		data.put(name, handler);

		return handler;
	}

	private void closeHandler(String name, FileHandler handler)
			throws Exception {
		String path = makeClosedPath(name);

		String fileName = handler.getFile().getName();
		
		path += fileName;

		copyToArchive(fileName, name, handler);

		File destFile = new File(path);
		
		if (destFile.exists()) {
			destFile.delete();
		}
		
		FileUtils.moveFile(handler.getFile(), destFile);

		data.remove(name);
	}

	private void copyToArchive(String fileName, String name, FileHandler handler)
			throws Exception {
		if (getArchivePath() != null) {
			File tempFile = createArchiveTemp();

			FileUtils.copyFile(handler.getFile(), tempFile);

			String archiveFile = makeArchivePath(name) + fileName;
			
			FileUtils.moveFile(tempFile, new File(archiveFile));
		}
	}

	private void moveRemaining(String name) throws Exception {
		String closedPath = makeClosedPath(name);
		String archivePath = makeArchivePath(name);
		String openPath = makeOpenPath(name);

		File[] openedFiles = new File(openPath).listFiles();

		for (File openedFile : openedFiles) {
			String fileName = openedFile.getName();
			
			moveToArchive(archivePath, openedFile, fileName);
			
			FileUtils.moveFile(openedFile, new File(closedPath+fileName));
		}
	}

	private void moveToArchive(String archivePath, File openedFile,
			String fileName) throws Exception {
		if (archivePath != null) {
			File tempFile = createArchiveTemp();
			
			FileUtils.copyFile(openedFile, tempFile);
			
			FileUtils.moveFile(tempFile, new File(archivePath+fileName));
		}
	}

	private File createArchiveTemp() throws IOException {
		File tempFile = File.createTempFile("simmon-archive-", ".txt");
		return tempFile;
	}

	private String makeOpenPath(String name) {
		String path = getOpenPath() + File.separator + name + File.separator;

		(new File(path)).mkdirs();

		return path;
	}

	private String makeClosedPath(String name) {
		String path = getClosedPath() + File.separator + name + File.separator;

		(new File(path)).mkdirs();

		return path;
	}

	private String makeArchivePath(String name) {
		if (getArchivePath() == null) {
			return null;
		}

		String path = getArchivePath() + File.separator + name + File.separator;

		(new File(path)).mkdirs();

		return path;
	}

	public String getOpenPath() {
		return openPath;
	}

	public void setOpenPath(String openPath) {
		this.openPath = openPath;
	}

	public String getClosedPath() {
		return closedPath;
	}

	public void setClosedPath(String closedPath) {
		this.closedPath = closedPath;
	}

	public String getTimeMask() {
		return timeMask;
	}

	public void setTimeMask(String timeMask) {
		this.timeMask = timeMask;
	}

	public String getArchivePath() {
		return archivePath;
	}

	public void setArchivePath(String archivePath) {
		this.archivePath = archivePath;
	}
}
