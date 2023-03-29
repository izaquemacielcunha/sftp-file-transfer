package izaque.maciel.cunha.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import izaque.maciel.cunha.service.FileService;
import izaque.maciel.cunha.sftp.host.model.FileHost;
import izaque.maciel.cunha.sftp.host.model.FileUpload;

/*
 * @author Izaque Maciel Cunha
 */

@RestController
@RequestMapping("/upload")
public class FileController {
	@Autowired
	public FileService fileService;
	
	@Value("${server.sftp.rootpath}")
	private String rootPath;

	@PostMapping("/file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			FileHost fileUpload = new FileUpload(file.getInputStream(), rootPath + file.getOriginalFilename());
			fileService.uploadFileToSftpServer(fileUpload);
		} 
		catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload fail");
		}
		return ResponseEntity.ok("Upload successful");
	}

}// end of class
