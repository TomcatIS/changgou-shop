package com.dhcc.exception;

import com.dhcc.dto.CommonResult;
import com.dhcc.enu.ProcessStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author zhangqi
 * @date 2020-05-07 11:29:46
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 普通参数校验不通过异常处理
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult<Object> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        logger.error("----------参数校验未通过----------" + ex.getMessage());
        return CommonResult.error(ProcessStatusEnum.REQUEST_PARAM_INVALID.getCode(),
                ex.getMessage());
    }

    /**
     * 对象类型的参数校验不通过异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        logger.error("----------参数校验未通过----------" + objectError.getDefaultMessage());
        return CommonResult.error(ProcessStatusEnum.REQUEST_PARAM_INVALID.getCode(),
                objectError.getDefaultMessage());
    }


    /**
     * 运行时异常处理器
     */
    @ExceptionHandler(BaseException.class)
    public CommonResult<Object> runtimeExceptionHandler(BaseException ex) {
        logger.error(ex.getMessage());
        return CommonResult.error(ex.getCode(),
                ex.getMessage());
    }

    /**
     * 其他异常处理
     */
    @ExceptionHandler(Exception.class)
    public CommonResult<Object> otherExceptionHandler() {
        logger.error("----------其他异常----------");
        return CommonResult.error(ProcessStatusEnum.OTHER_ERROR.getCode(), ProcessStatusEnum.OTHER_ERROR.getMessage());
    }
}
