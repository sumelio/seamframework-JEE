package com.leasing.popular.wf.generico.controller;

import java.util.List;

import javax.ejb.Local;

import com.leasing.popular.wf.table.seguridad.modulo.MsegMenu;
import com.leasing.popular.wf.table.seguridad.modulo.MsegModulos;


/**
 * @author           Usuario
 */
@Local
public interface MenuBean {
 
	public String getCurrent();
    public void setCurrent(String current);
    public String updateCurrent();
    public String getParameterMenuItem(String params);
    public String getUlrs();
    public void setUlrs(String ulrs);
    public List<MsegMenu> getListaMenu();
    public int getModuloSize(int i);
    public MsegModulos getModuloItem(int i,int ii);
    public void destroy();
}
