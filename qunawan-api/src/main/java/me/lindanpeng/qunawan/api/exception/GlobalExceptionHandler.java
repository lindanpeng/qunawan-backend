package me.lindanpeng.qunawan.api.exception;

import me.lindanpeng.qunawan.api.protocol.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler  {
    private final static Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ApiResponse<Object> handleServiceException(HttpServletRequest request,ServiceException ex){
        return ApiResponse.ERROR(ex.getCodeMsg());
    }
}
