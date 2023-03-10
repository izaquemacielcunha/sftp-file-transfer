package izaque.maciel.cunha.sftp;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/*
 * @author Izaque Maciel Cunha
 */

public class SftpClientImp implements SftpClient {
	private JSch jsch;
	private Session session;
	private String remoteHostDnsOrIp;
	private String username;
	private String password;

	public SftpClientImp(String remoteHostDnsOrIp, String username, String password) {
		super();
		this.remoteHostDnsOrIp = remoteHostDnsOrIp;
		this.username = username;
		this.password = password;
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

}// end of class
