package logic;

import org.springframework.web.multipart.MultipartFile;

public class FilePath {

	private MultipartFile filePath;

	public MultipartFile getFilePath() {
		return filePath;
	}

	public void setFilePath(MultipartFile filePath) {
		this.filePath = filePath;
	}
}
