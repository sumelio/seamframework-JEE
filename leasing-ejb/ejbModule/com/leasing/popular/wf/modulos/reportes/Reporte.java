//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.reportes;

import java.io.IOException;

import javax.ejb.Local;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

/**
 * @author          Usuario
 */
@Local
public interface Reporte
{
	public void service(HttpServletRequest request,HttpServletResponse response) throws JRException,IOException,
	ServletException;
	public void destroy();

}