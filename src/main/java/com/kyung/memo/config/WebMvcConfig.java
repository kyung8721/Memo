package com.kyung.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kyung.memo.common.FileManager;
import com.kyung.memo.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/"); // Mac이나 리눅스는 //, 윈도우는 ///
	}
	
	// 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		PermissionInterceptor interceptor = new PermissionInterceptor();
		
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**") // 이 경로를 거치는 것만 인터셉터를 거치게 하기
		.excludePathPatterns("/static/**", "/images/**", "/user/logout"); // 이 경로들은 인터셉터를 거치지 않고 가도록 함
	}
		
}
