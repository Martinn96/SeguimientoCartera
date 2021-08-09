package seguimientoCartera.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import seguimientoCartera.login.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(loginInterceptor).excludePathPatterns("/usuarios/newuser");
	}

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public SessionInfo getSessionInfo() {
		return new SessionInfo();
	}
}
