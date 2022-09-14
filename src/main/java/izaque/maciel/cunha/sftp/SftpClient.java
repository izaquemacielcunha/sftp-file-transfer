package izaque.maciel.cunha.sftp;

import com.jcraft.jsch.Session;

/*
 * @author Izaque Maciel Cunha
 */

public interface SftpClient {
	String SET_NO_AUTOSAVE_PUBLIC_KEY_FROM_HOST = "StrictHostKeyChecking";
	Integer TIME_OUT_IN_MILISECONDS = 10000;
	Integer MAX_KEEP_ALIVE_MESSAGES_WHITOUT_RESPONSE = 150;
	void openSession();
	Session getSession();
	void closeSession();
}// end of interface
