package izaque.maciel.cunha.sftp.host.model;

import java.io.InputStream;

/*
 * @author Izaque Maciel Cunha
 */

public class FileUpload extends FileHost {
	public FileUpload(InputStream fileBytes, String absolutePath) {
		super(fileBytes, absolutePath);
	}
}// End of class
