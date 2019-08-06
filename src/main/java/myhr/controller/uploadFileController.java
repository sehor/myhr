package myhr.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class uploadFileController {
	private final Log log=LogFactory.getLog(uploadFileController.class);
	@PostMapping("/uploadFile")
	public String uploadFile(MultipartFile uploadFile,HttpServletRequest re) {
		
		SimpleDateFormat sFormat=new SimpleDateFormat("yyyy/MM/dd");
		String tdString=sFormat.format(new Date());
		
		String realPath=re.getSession().getServletContext().getRealPath("/uploadFile/");
		log.info(realPath);
		File  folder=new File(realPath+tdString);
		if(!folder.isDirectory()) {
			folder.mkdirs();
		}
		
		String oldName=uploadFile.getOriginalFilename();
		String newName=UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."), oldName.length());
		
		try {
			uploadFile.transferTo(new File(folder,newName));
			String filePath=re.getScheme()+"://"+re.getServerName()+":"+re.getServerPort()
			+"/uploadFile/"+tdString+"/"+newName;
			log.info(filePath);
			//throw new MaxUploadSizeExceededException(0);
			return filePath;
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "上传失败";
	}

}
