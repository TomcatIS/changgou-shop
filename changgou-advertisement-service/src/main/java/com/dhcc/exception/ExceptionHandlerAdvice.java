package com.dhcc.exception;

import com.dhcc.dto.CommonResult;
import com.dhcc.enu.ProcessStatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author zhangqi
 * @date 2020-05-07 11:29:46
 */
@RestControllerAdvice(basePackages = "com.dhcc.controller")
public class ExceptionHandlerAdvice implements ResponseBodyAdvice<Object> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果返回类型为CommResult，返回true，不对返回值进行封装
        return !methodParameter.getGenericParameterType().equals(CommonResult.class);
    }

    @ResponseBody
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        // String类型不能直接进行封装，需要额外处理
        if (methodParameter.getGenericParameterType().equals(String.class)) {
            try {
                return objectMapper.writeValueAsString(CommonResult.success(o));
            } catch (JsonProcessingException e) {
                throw new BaseException("返回String类型失败");
            }
        }
        return CommonResult.success(o);
    }

    /**
     * 普通参数校验不通过异常处理
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult<Object> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        logger.error("[constraintViolationExceptionHandler]", ex);
        return CommonResult.error(ProcessStatusEnum.REQUEST_PARAM_INVALID.getCode(),
                ex.getMessage());
    }

    /**
     * 对象类型的参数校验不通过异常处理
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        logger.error("[methodArgumentNotValidExceptionHandler]" + ex);
        return CommonResult.error(ProcessStatusEnum.REQUEST_PARAM_INVALID.getCode(),
                objectError.getDefaultMessage());
    }


    /**
     * 运行时异常处理器
     */
    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public CommonResult<Object> runtimeExceptionHandler(BaseException ex) {
        logger.debug("[runtimeExceptionHandler]", ex);
        return CommonResult.error(ex.getCode(),
                ex.getMessage());
    }

    /**
     * 其他异常处理
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CommonResult<Object> otherExceptionHandler(Exception ex) {
        logger.debug("[otherExceptionHandler]", ex);
        return CommonResult.error(ProcessStatusEnum.OTHER_ERROR.getCode(), ProcessStatusEnum.OTHER_ERROR.getMessage());
    }

}
