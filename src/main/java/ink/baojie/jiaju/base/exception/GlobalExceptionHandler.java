package ink.baojie.jiaju.base.exception;

import ink.baojie.jiaju.service.dto.BaseOutDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseOutDTO defaultErrorHandler(HttpServletRequest req, Exception e) {
        log.error("接口 {} 调用失败", req.getRequestURI());
        log.error("异常信息: ", e);
        return new BaseOutDTO().fail(CustomError.SYS_ERR);
    }

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public BaseOutDTO knownErrorHandler(HttpServletRequest req, CustomException e) {
        log.error("接口 {} 调用失败 : {}", req.getRequestURI(), e.getError().getMsg());
        return new BaseOutDTO().fail(e.getError());
    }
}
