package izaque.maciel.cunha.sftp.host;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import izaque.maciel.cunha.sftp.host.model.FileHost;
import izaque.maciel.cunha.sftp.SftpClient;

/*
 * @author Izaque Maciel Cunha
 */
@Component
public class FileToRemoteHostImp implements FileToRemoteHost {
	private ChannelSftp channelSftp;

	@Override
	public void transferFileViaSFTP(SftpClient sftpClient, FileHost fileHost) {
		try {
			synchronized (sftpClient) {
				transferFile(sftpClient, fileHost);
			}
		} 
		catch (SftpException e) {
			e.printStackTrace();
		} 
		catch (JSchException e) {
			e.printStackTrace();
		}
		finally {
			try {
				closeChannel();
				closeSession(sftpClient);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void openChannelSftp(SftpClient sftpClient) throws JSchException {
		channelSftp = (ChannelSftp) sftpClient.getSession().openChannel(SFTP_PROTOCOL);
		setChannelConfig();
		connect();
	}

	private void setChannelConfig() throws JSchException {
		channelSftp.setBulkRequests(MAX_REQUESTS_AT_TIME);
	}

	private void connect() throws JSchException {
		channelSftp.connect();
	}

	private void transferFile(SftpClient sftpClient, FileHost fileHost) throws JSchException, SftpException {
		openChannelSftp(sftpClient);
		writeFile(fileHost);
	}

	private void writeFile(FileHost fileHost) throws SftpException {
		channelSftp.put(fileHost.getFileBytes(), fileHost.getAbsolutePath());
	}

	private void closeChannel() throws IOException {
		if (channelSftp != null) {
			channelSftp.getInputStream().close();
			channelSftp.disconnect();
		}
	}
	
	private void closeSession(SftpClient sftpClient) {
		sftpClient.closeSession();
	}

}// end of class
