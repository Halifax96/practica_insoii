/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.MenuFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import model.Menu;
import model.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author esteb
 */
@Named
@SessionScoped
public class MenuController implements Serializable {
    
    private static final Logger logger = Logger.getLogger(MenuController.class.getName());

    @EJB
    private MenuFacadeLocal EJBMenu;
    private List<Menu> lista;
    private MenuModel model;

    @PostConstruct
    public void init() {
        
        this.listarMenus();
        model = new DefaultMenuModel();
        this.establecerPermisos();
    }

    public void listarMenus() {

        try {
            lista = EJBMenu.findAll();
        } catch (Exception e) {
            //mensaje JSF
        }

    }

    public MenuModel getModel() {
        return model;
    }
    
    
        
    public void setModel(MenuModel model) {
        this.model = model;
    }

    
    public void establecerPermisos() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        for(Menu m: lista){
            if(m.getTipo().equals("S") && m.getTipoUsuario().equals(us.getTipo())){
                //logger.log(Level.INFO, "Tipo de usuario de m: {0}", m.getTipoUsuario());
                //logger.log(Level.INFO, "Tipo de usuario de us: {0}", us.getTipo());
                DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label(m.getNombre()).build();
                for(Menu i : lista){
                   Menu submenu = i.getSubmenu();
                   if(submenu!=null){
                       if(submenu.getIdmenu() == m.getIdmenu()){
                             DefaultMenuItem item = DefaultMenuItem.builder().value(i.getNombre()).build();
                             item.setUrl(i.getUrl());
                             firstSubmenu.getElements().add(item);
                       }
                   }
                }
                model.getElements().add(firstSubmenu);
            }else{
                if(m.getSubmenu()==null && m.getTipoUsuario().equals(us.getTipo())){
                    DefaultMenuItem item = DefaultMenuItem.builder().value(m.getNombre()).build();
                    item.setUrl(m.getUrl());
                    model.getElements().add(item);
                }
            }
        }
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    
    public String mostrarUsuarioLogueado(){
         Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
         return us.getUsuario();
    }
}
