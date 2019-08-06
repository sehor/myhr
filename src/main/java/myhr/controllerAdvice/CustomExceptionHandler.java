package myhr.controllerAdvice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public void uploadException(MaxUploadSizeExceededException e,HttpServletResponse rs) throws IOException {
		rs.setContentType("text/html;charset=utf-8");
		PrintWriter pWriter=rs.getWriter();
		pWriter.write("上传文件大小超出限制（最大1M）");
		pWriter.flush();
		pWriter.close();
	}

}
