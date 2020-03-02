package com.joey.community.community.advice;

import com.alibaba.fastjson.JSON;
import com.joey.community.community.dto.ResultDTO;
import com.joey.community.community.exception.CustomizeException;
import com.joey.community.community.exception.CustomizeExceptionCode;
import com.sun.xml.internal.ws.encoding.ContentTypeImpl;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handleException(Throwable ex, Model model, HttpServletRequest request, HttpServletResponse response) {

        String contentType = request.getContentType();
        ResultDTO resultDTO;
        if("application/json".equals(contentType)) {
            if(ex instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException)ex);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeExceptionCode.SYSTEM_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
            } catch (IOException e) {

            }
            return null;
        } else {
            if(ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message",CustomizeExceptionCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
