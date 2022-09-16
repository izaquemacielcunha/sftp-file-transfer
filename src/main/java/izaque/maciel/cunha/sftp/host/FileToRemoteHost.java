package izaque.maciel.cunha.sftp.host;

import izaque.maciel.cunha.sftp.SftpClient;
import izaque.maciel.cunha.sftp.host.model.FileHost;

/*
 * @author Izaque Maciel Cunha
 */

public interface FileToRemoteHost {
	String SFTP_PROTOCOL = "sftp";
	Integer MAX_REQUESTS_AT_TIME = 20;
	
	public void transferFileViaSFTP(SftpClient sftpClient, FileHost fileHost);

}// end of interface
