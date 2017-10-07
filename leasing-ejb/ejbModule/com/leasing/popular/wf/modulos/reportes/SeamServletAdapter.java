package com.leasing.popular.wf.modulos.reportes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.Component;




public class SeamServletAdapter extends HttpServlet {
	private static final long serialVersionUID=8830027561411226041L;
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		String componentName=request.getParameter("component");
		if(componentName==null) {
			String path=request.getRequestURI();
			int index=path.lastIndexOf('/');
			if(index>=0)
				componentName=path.substring(index+1);
			if(componentName.endsWith(".seam"))
				componentName=componentName.substring(0,componentName.length()-".seam".length());
		}
		try {
			delegateToSeamComponent(request,response,componentName);
		}
		catch(Exception e) {
			if(IOException.class.isAssignableFrom(e.getClass()))
				throw (IOException)e;
			if(ServletException.class.isAssignableFrom(e.getClass()))
				throw (ServletException)e;
			throw new ServletException("could not call Seam component "+componentName,e);
		}
	}

	private void delegateToSeamComponent(HttpServletRequest request,HttpServletResponse response,String componentName)
			throws NoSuchMethodException,IllegalAccessException,InvocationTargetException {
		Object component=Component.getInstance(componentName);
		Method method=component.getClass().getMethod("service",HttpServletRequest.class,HttpServletResponse.class);
		method.invoke(component,request,response);
	}
}
