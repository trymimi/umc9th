package com.example.umc.global.resolver;

import com.example.umc.global.annotation.ValidPage;
import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.GeneralException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidPage.class) &&
               parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String pageParam = webRequest.getParameter("page");
        
        if (pageParam == null || pageParam.isEmpty()) {
            throw new GeneralException(GeneralErrorCode.PAGE_INVALID);
        }
        
        try {
            int page = Integer.parseInt(pageParam);
            
            // page가 1 미만이면 에러 발생
            if (page < 1) {
                throw new GeneralException(GeneralErrorCode.PAGE_INVALID);
            }
            
            return page;
        } catch (NumberFormatException e) {
            throw new GeneralException(GeneralErrorCode.PAGE_INVALID);
        }
    }
}

