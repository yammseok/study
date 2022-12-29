package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHandler {
	
	private static final Logger log = LoggerFactory.getLogger(FileHandler.class);
	
	public int deleteFile(String imageFileName, String savePath) {
		boolean isDel = true; //삭제가 잘 이루어졌는지 체크하는 변수
		log.info(">>> deleteFile method 접근");
		log.info(">>> imageFileName"+imageFileName);
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+File.separator+imageFileName);
		File removeThumbFile = new File(fileDir+File.separator+"th_"+imageFileName); 
		
		//파일이 있어야 삭제
		if(removeFile.exists() || removeThumbFile.exists()) {
			isDel = removeFile.delete(); //boolean 형태로 리턴
			log.info(">>>FileHandler remove : "+(isDel?"OK":"FAIL"));
			if(isDel) {
				isDel = removeThumbFile.delete();
				log.info(">>>FileHandler remove Thumbnail : "+(isDel?"OK":"fail"));
			}
		}
		log.info(">>> FileHandler remove OK");
		return isDel ? 1 : 0;
	}

}
