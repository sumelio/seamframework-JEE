/*package com.leasing.popular.wf.generico.view.report;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.jboss.seam.annotations.In;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.pdf.DocumentData;
import org.jboss.seam.pdf.DocumentStore;
import org.jboss.seam.pdf.DocumentData.DocType;
import org.jboss.seam.persistence.HibernateSessionProxy;

import com.snet.entidades.Usuario;
 
public abstract class RelSeam {
       /* @In
        private Usuario usuarioLogado;
        @In
        private HibernateSessionProxy session;
        @In
        private FacesMessages facesMessages;
        @In
        private EntityManager entityManager;
        @In(value="org.jboss.seam.pdf.documentStore", create=true)
        private DocumentStore documentStore;
        
        private ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        private String serverRootpath =  servletContext.getRealPath("/");

        protected abstract String getReportPath();  

        protected abstract Map<String, Object> getParams();

        @SuppressWarnings("deprecation")  
        public String print() {  

                String reportUrl = getReportPath();    

                try {  
                        Map<String, Object> params = new HashMap<String, Object>();  

                        params.putAll(getParams());
                        
                        Connection connection =  session.connection();

                        JasperPrint jasperPrint = JasperFillManager.fillReport(this.getServerRootpath() + this.getReportPath(), params, connection);  
                        connection.close();
                        ByteArrayOutputStream output = new ByteArrayOutputStream();  
                        JasperExportManager.exportReportToPdfStream(jasperPrint, output);  

                        String reportId = documentStore.newId();
                        DocumentData data = new DocumentData("Report #" + reportId , DocType.PDF, output.toByteArray());  
                        documentStore.saveData(reportId, data);  
                        
                        return "/seam-doc?docId=" + reportId;

                } catch (Exception e) { 
                        e.printStackTrace();
                        facesMessages.instance().add("Erro ao criar relatorio :"+e.getMessage());
                        return null;  
                } 
        }*/
      /*Getters and Setters omitted*/
/*}*/
