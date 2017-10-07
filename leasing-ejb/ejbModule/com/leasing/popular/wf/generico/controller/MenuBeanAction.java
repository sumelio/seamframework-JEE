package com.leasing.popular.wf.generico.controller;

import static org.jboss.seam.ScopeType.SESSION;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;

import com.leasing.popular.wf.table.seguridad.modulo.MsegMenu;
import com.leasing.popular.wf.table.seguridad.modulo.MsegModulos;
import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuarios;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios;


/**
 * @author        Usuario
 */
@Stateful
@Name("menuBeanAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class MenuBeanAction implements MenuBean {

	
	@PersistenceContext
	private EntityManager em;
	
	@In(required=true, scope=ScopeType.SESSION)
	public MsegRoles msegRoles; 
    
	private String current;
	private String ulrs = "seguridad/inicio/blanco.seam";
	
	@Out(required=false, scope = SESSION)
	private List<MsegMenu> listaMenu = new ArrayList<MsegMenu>();
	
    @Out(required=false, scope = SESSION)
    private MsegUsuarios msegUsuarios;
    
    @Out(required=false, scope = SESSION)
    private MsegRolusuarios msegRolusuarios;
 
	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String updateCurrent() {
   	    FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpSession mySession = myRequest.getSession();
        ulrs = (String)myRequest.getParameter("current");
		return ulrs + ".seam";
    }

	public String getParameterMenuItem(String params) {

		HttpServletRequest myRequest = (HttpServletRequest)(FacesContext.getCurrentInstance()).getExternalContext().getRequest();
		//HttpSession mySession = myRequest.getSession();
		return myRequest.getParameter(params);
	}

	public String getUlrs() {
		return ulrs;
	}

	public void setUlrs(String ulrs) {
		this.ulrs = ulrs;
	}
	
	public List<MsegMenu> getListaMenu() {
		 String query = "SELECT u.msegModulos.msegMenu"+
                       " FROM MsegRolmodulos u "+
                       " WHERE u.msegRoles.rolCodigo = :roless "+
                       " AND    ( u.rolActualizar = :par1 OR"+
                       "         u.rolConsulta = :par2    OR"+
                       "         u.rolEliminar = :par3    OR"+
                       "         u.rolInsertar = :par4 " +
                       "        ) "+
                       " ORDER BY u.msegModulos.msegMenu.menCodigo, u.msegModulos.modNombre";
	
		 listaMenu = em.createQuery(query)
		                         .setParameter("roless", msegRoles.getRolCodigo())
		                         .setParameter("par1", new Long(1))
		                         .setParameter("par2", new Long(1))
		                         .setParameter("par3", new Long(1))
		                         .setParameter("par4", new Long(1))
		                         
		                         .getResultList();
		
		 
		 listaMenu = quitarRepetido(listaMenu);
 
		 return listaMenu;
	}
	
	public int getModuloSize(int i){
		String query = " SELECT  u.msegModulos"+
                       " FROM MsegRolmodulos u "+
                       " WHERE u.msegRoles.rolCodigo = :rolCodig"+ 
                       " AND u.msegModulos.msegMenu.menCodigo = :menCodig"+
                       " AND ( u.rolActualizar = :par1 or"+
                       "       u.rolConsulta = :par2   or"+
                       "       u.rolEliminar = :par3   or"+
                       "       u.rolInsertar = :par4 "+
                       "     ) "+
                       " ORDER BY u.msegModulos.msegMenu.menCodigo, u.msegModulos.modNombre";
		
		List<MsegModulos> listaModulos = em.createQuery(query)
		                                       .setParameter("rolCodig",msegRoles.getRolCodigo())
		                                       .setParameter("menCodig",new Long(i))
		                                       .setParameter("par1", new Long(1))
						                       .setParameter("par2", new Long(1))
						                       .setParameter("par3", new Long(1))
						                       .setParameter("par4", new Long(1))
		                                       .getResultList();
		
		return listaModulos.size();
	}
	
	public MsegModulos getModuloItem(int i,int ii){
		
		Long iii = listaMenu.get(i).getMenCodigo();

		String query = " SELECT  u.msegModulos"+
        " FROM MsegRolmodulos u "+
        " WHERE u.msegRoles.rolCodigo = :rolCodig"+ 
        " AND u.msegModulos.msegMenu.menCodigo = :menCodig"+
        " AND ( u.rolActualizar = :par1 or"+
        "       u.rolConsulta = :par2   or"+
        "       u.rolEliminar = :par3   or"+
        "       u.rolInsertar = :par4 "+
        "     ) "+
        " ORDER BY u.msegModulos.msegMenu.menCodigo, u.msegModulos.modNombre";

        List<MsegModulos> listaModulos = em.createQuery(query)
                                .setParameter("rolCodig",msegRoles.getRolCodigo())
                                .setParameter("menCodig",iii)
                                .setParameter("par1", new Long(1))
			                       .setParameter("par2", new Long(1))
			                       .setParameter("par3", new Long(1))
			                       .setParameter("par4", new Long(1))
                                .getResultList();
		return listaModulos.get(ii-1);
	}
	
	 
	private List<MsegMenu> quitarRepetido(List<MsegMenu> listaM){
		boolean flag;
		List<MsegMenu> listas = new ArrayList<MsegMenu>(0);
		for (MsegMenu msegMenu : listaM) {
			flag = false;
			for (MsegMenu msegMenu2 : listas) { 
				if(msegMenu2.getMenCodigo() == msegMenu.getMenCodigo()){
					flag = true;
				}
			}
			if(!flag){
			listas.add(msegMenu);	
			}
		}
		
		return listas;
	}

	 
	
	   @Remove
	   public void destroy() {}
	

}


