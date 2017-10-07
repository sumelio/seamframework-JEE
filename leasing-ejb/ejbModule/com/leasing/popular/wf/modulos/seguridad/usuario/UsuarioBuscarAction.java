//$Id: UsuarioBuscarAction.java 5509 2007-06-25 16:19:40Z gavin $
package com.leasing.popular.wf.modulos.seguridad.usuario;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;

import com.leasing.popular.wf.table.seguridad.roles.MsegRoles;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuariosActionDAO;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegRolusuariosDAO;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuarios;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuariosActionDAO;
import com.leasing.popular.wf.table.seguridad.usuarios.MsegUsuariosDAO;



/**
 * @author       Usuario
 */
@Stateful
@Name("usuarioBuscarAction")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class UsuarioBuscarAction implements  UsuarioBuscar
{

	@PersistenceContext
	private EntityManager em;

	/**
	 * @uml.property  name="searchString"
	 */
	private String searchString;
	/**
	 * @uml.property  name="pageSize"
	 */
	private int pageSize = 10;
	/**
	 * @uml.property  name="page"
	 */
	private int page;




	/**
	 * @uml.property  name="queryCantidadTotal"
	 */
	private int queryCantidadTotal = 0;
	/**
	 * @uml.property  name="msegUsuariosActionDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MsegUsuariosActionDAO msegUsuariosActionDAO;
	/**
	 * @uml.property  name="msegRolusuariosDAO"
	 * @uml.associationEnd  
	 */
	@In
	private MsegRolusuariosActionDAO msegRolusuariosActionDAO;

	/**
	 * @uml.property  name="msegRoles"
	 * @uml.associationEnd  
	 */
	@In(required=true, scope=ScopeType.SESSION)
	public MsegRoles msegRoles; 

	/**
	 * @uml.property  name="permisoConsulta"
	 */
	private boolean permisoConsulta;
	/**
	 * @uml.property  name="permisoInsertar"
	 */
	private boolean permisoInsertar;
	/**
	 * @uml.property  name="permisoEliminar"
	 */
	private boolean permisoEliminar ;
	/**
	 * @uml.property  name="permisoActualizar"
	 */
	private boolean permisoActualizar;



	/**
	 * @uml.property  name="deleteUsuario"
	 */
	private String deleteUsuario;

	@DataModel
	private List<MsegUsuarios> listaUsuarios;



	/**
	 * @uml.property  name="msegUsuariosEditar"
	 * @uml.associationEnd  
	 */
	MsegUsuarios msegUsuariosEditar = new MsegUsuarios();

	public void find(){
		page = 0;
		queryUsuarios();
	}

	/**
	 * @return
	 * @uml.property  name="page"
	 */
	public int getPage() {
		return page;
	}
	public void nextPage(){
		page++;
		queryUsuarios();
	}

	public void firstPage(){
		page = 0;
		queryUsuarios();
	}
	public void lastPage(){
		page = queryCantidadTotal-1;
		queryUsuarios();
	}

	public void backPage(){
		if(page > 0)
			page--;
		queryUsuarios();
	}


	private void queryUsuarios(){

		List lista2= em.createQuery("select h from MsegUsuarios h where lower(h.usuNombres) like #{pattern}")
		.getResultList();

		queryCantidadTotal = lista2==null?0:lista2.size()/pageSize;
		queryCantidadTotal += lista2!=null && lista2.size()>pageSize && lista2.size()%pageSize>0?1:0;

		listaUsuarios = em.createQuery("select h from MsegUsuarios h where lower(h.usuNombres) like #{pattern}")
		.setMaxResults(pageSize)
		.setFirstResult( page * pageSize )
		.getResultList();
	}

	/**
	 * @return
	 * @uml.property  name="queryCantidadTotal"
	 */
	public int getQueryCantidadTotal(){
		return queryCantidadTotal;
	}


	@Factory(value="pattern", scope=ScopeType.EVENT)
	public String getSearchPattern(){
		return searchString==null ?
				"%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
	}

	public boolean isNextPageAvailable()
	{
		return listaUsuarios!=null && listaUsuarios.size()==pageSize;
	}

	/**
	 * @return
	 * @uml.property  name="pageSize"
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param  pageSize
	 * @uml.property  name="pageSize"
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return
	 * @uml.property  name="searchString"
	 */
	public String getSearchString()
	{
		return searchString;
	}

	/**
	 * @param  searchString
	 * @uml.property  name="searchString"
	 */
	public void setSearchString(String searchString)
	{
		this.searchString = searchString;
	}



	public void eliminarUsuario(){
		MsegUsuarios m = null;
		if (deleteUsuario !=null){ 
			m = msegUsuariosActionDAO.findByCedula(  Long.valueOf(deleteUsuario));
			if(m!= null){ 
				msegRolusuariosActionDAO.removeListRol(  m);

				msegUsuariosActionDAO.remove(  m);
				find();
			}else{
				System.out.println("es nulll");
			}
		}

	}







	/**
	 * @return
	 * @uml.property  name="deleteUsuario"
	 */
	public String getDeleteUsuario() {
		return deleteUsuario;
	}
	/**
	 * @param  deleteUsuario
	 * @uml.property  name="deleteUsuario"
	 */
	public void setDeleteUsuario(String deleteUsuario) {
		this.deleteUsuario = deleteUsuario;
	}






	/**
	 * @return
	 * @uml.property  name="permisoConsulta"
	 */
	public boolean getPermisoConsulta() {
		return msegUsuariosActionDAO.isConsulta(  msegRoles);
	}
	/**
	 * @return
	 * @uml.property  name="permisoInsertar"
	 */
	public boolean getPermisoInsertar() {
		return  msegUsuariosActionDAO.isInsertar(  msegRoles);
	}
	/**
	 * @return
	 * @uml.property  name="permisoEliminar"
	 */
	public boolean getPermisoEliminar() {
		return msegUsuariosActionDAO.isElimnar(  msegRoles);
	}
	/**
	 * @return
	 * @uml.property  name="permisoActualizar"
	 */
	public boolean getPermisoActualizar() {
		return msegUsuariosActionDAO.isActualizar(  msegRoles);
	}



	@Remove
	public void destroy() {}
}
