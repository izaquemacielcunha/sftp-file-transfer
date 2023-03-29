package izaque.maciel.cunha.sftp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/*
 * @author Izaque Maciel Cunha
 */
@Component
public class SftpClientImp implements SftpClient {
	private JSch jsch;
	private Session session;
	@Value("${server.sftp.ip}")
	private String remoteHostDnsOrIp;
	@Value("${server.sftp.user}")
	private String username;
	@Value("${server.sftp.password}")
	private String password;
	
	public SftpClientImp() {
		super();
		this.jsch = new JSch();
	}

	@Override
	public void openSession() {
		try {
			openSshSession();
		} 
		catch (JSchException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Session getSession() {
		return session;
	}

	@Override
	public void closeSession() {
		if(session != null) {
			session.disconnect();
		}
	}

	private void setSessionCredentials() {
		session.setPassword(password);
	}

	private void setSessionConfig() {
		session.setConfig(SET_NO_AUTOSAVE_PUBLIC_KEY_FROM_HOST, "NO");
		session.setServerAliveCountMax(MAX_KEEP_ALIVE_MESSAGES_WHITOUT_RESPONSE);
	}

	private void connect() throws JSchException {
		session.connect(TIME_OUT_IN_MILISECONDS);
	}

	private void getConnectSessionInstance() throws JSchException {
		session = jsch.getSession(username, remoteHostDnsOrIp);
	}

	private void openSshSession() throws JSchException {
		getConnectSessionInstance();
		setSessionCredentials();
		setSessionConfig();
		connect();
	}

	@Override
	public String toString() {
		return "SftpClientImp [remoteHostDnsOrIp=" + remoteHostDnsOrIp + ", username=" + username + ", password="
				+ password + "]";
	}
	


}// end of class
