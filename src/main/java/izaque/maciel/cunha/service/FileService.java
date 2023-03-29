package izaque.maciel.cunha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import izaque.maciel.cunha.sftp.SftpClient;
import izaque.maciel.cunha.sftp.host.FileToRemoteHost;
import izaque.maciel.cunha.sftp.host.model.FileHost;

/*
 * @author Izaque Maciel Cunha
 */

@Service
public class FileService {
	@Autowired
	public SftpClient sftpClient;

	@Autowired
	public FileToRemoteHost fileToRemoteHost;

	public void uploadFileToSftpServer(FileHost file) {
		sftpClient.openSession();
		fileToRemoteHost.transferFileViaSFTP(sftpClient, file);
	}

}// end of class
