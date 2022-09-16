package izaque.maciel.cunha.sftp.host.model;

import java.io.InputStream;

import lombok.Getter;
import lombok.Setter;

/*
 * @author Izaque Maciel Cunha
 */

@Getter
@Setter
public abstract class FileHost {
	private InputStream fileBytes;
	private String absolutePath;

	public FileHost(InputStream fileBytes, String absolutePath) {
		this.fileBytes = fileBytes;
		this.absolutePath = absolutePath;
	}
}// End of class
