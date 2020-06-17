package tn.esprit.spring.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tn.esprit.spring.frontcontroller.UserController;


public class LoginFilter implements Filter {
//	Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	Boolean log=false;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {


		
		HttpServletRequest httpServletRequest= (HttpServletRequest) request;
		HttpServletResponse httpServletResponse= (HttpServletResponse) response;

		UserController userController = (UserController) httpServletRequest.getSession().getAttribute("userFController");
		try {
//			logger.info(user.getLoggedIn().toString());
			log=userController.getLoggedIn();
		}
		catch(NullPointerException n) {
//			logger.info("THERE IS NO USER CONNECTED");
		}

		if(log.equals(false)) {
			httpServletResponse.sendRedirect("/login.jsf");
		}
		else{
			chain.doFilter(request, response);
			}
		}		
	}


