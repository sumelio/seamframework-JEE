//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.reportes;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

 

@Name("printSomething")
@Scope(ScopeType.SESSION)
public class ReporteAction extends HttpServlet{


       
	@In
	private EntityManager entityManager;
	private static final Log log = LogFactory.getLog(ReporteAction.class);

	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException,
	ServletException {
		File reportFile = null;
		reportFile = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/reports/Reports/reporte2.jasper"));
		System.out.println("ReporteAction:service() After Getting Report File 1");

		File tmpFile = null;
		String fileName ="";

		try {

			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath()); //reportFile.getPath()
			System.out.println("ReporteAction:service() After Getting Report File 2");

			Map<String, Object> params=new HashMap<String, Object>();
			convertRequestParameters(jasperReport,request.getParameterMap(),params);
			//params.put("SUBREPORT_DIR","reports/");
			/* EntityManagerFactory emf = Persistence.createEntityManagerFactory("leasingDatabase", new HashMap());
				EntityManager em2 = emf.createEntityManager();*/
				
				
			params.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,entityManager);
			//setSeamManagedEntityManager(params);
			List list =  entityManager.createQuery("select u from McomEstudio u ").getResultList();
			System.out.println("ReporteAction:service() After Params Put");
			//System.out.println("entyty manage si " + list.size() );
			
			
			
			//Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","HLPDSK","HLPDSK");
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,params/*, c*/);
			
			
			response.setContentType("application/vnd.ms-excel");
			System.out.println("ReporteAction:service() Contenttype set");
			java.io.OutputStream out=response.getOutputStream();
			System.out.println("ReporteAction:service()response.getOutputStream()");
			System.out.println("entyty manage si " + list.size() );
			/*JRPdfExporter exporter=new JRPdfExporter();
			System.out.println("ReporteAction:service():exporter created");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,out);
			System.out.println("ReporteAction:service() exporter.setParameter Done");
			exporter.exportReport();
			System.out.println("ReporteAction:service() exporter.exportReport()");*/
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,out);
			//exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\disco_D");
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
			exporter.exportReport();
		}

		catch (Exception e) {
			e.getStackTrace();
			System.out.println("Message -> "+e.getMessage());
			System.out.println("Cause -> "+e.getCause());

		}
		finally {
			//out.close();
		}
	}


	public static void convertRequestParameters(JasperReport jasperReport,Map<String, String[]> requestParameterMap,
			Map<String, Object> jrParameterMap) {
		JRParameter[] params=jasperReport.getParameters();
		System.out.println("ReporteAction:convertRequestParameters()1");
		for(JRParameter p : params) {
			System.out.println("ReporteAction:convertRequestParameters() inside For");
			String[] values=requestParameterMap.get(p.getName());
			if(values==null||values.length==0) continue; // parameter not provided
			Object o;
			if(!p.getValueClass().equals(String.class)) // convert string to desired type
				o=ConvertUtils.convert(values[0],p.getValueClass());
			else o=values[0]; // String is expected and we have string, should be ok
			jrParameterMap.put(p.getName(),o);
		}
	}
	
	/**
	 * Adds the Seam managed EntityManager to the given parameters if we have Seam on the classpath.
	 * 
	 * @param parameters
	 *            the parameters Map passed to JasperReports when filling the report.
	 */
	private static void setSeamManagedEntityManager(Map<String, Object> parameters) {
		try { // perform org.jboss.seam.Component.getInstance("entityManager") to get the EntityManager
			Class<?> clazz=Class.forName("org.jboss.seam.Component");
			Method getInstance=clazz.getMethod("getInstance",String.class);
			Object entityManager=getInstance.invoke(null,"entityManager");
			parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,entityManager);
			log.info("setting EntityManager "+entityManager);
		}
		catch(Exception e) {
			log.info("Seam not in the classpath or other problem, EntityManager not set");
			// silently ignore exception, probably "Seamless" environment
		}
	}




}

