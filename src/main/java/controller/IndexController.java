/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import static javax.faces.application.FacesMessage.SEVERITY_WARN;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Usuario;

/**
 *
 * @author esteb
 */

@Named
@ViewScoped
public class IndexController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal EJBUsuario;
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){
        Usuario us;
        String redireccion = null;
        
        try{
            us = EJBUsuario.iniciarSesion(usuario);
            if(us!=null){
                //Almacenar la sesión en JSF
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "protegido/principal?faces-redirect=true";//No es recomendable que se vean las rutas ya que son vulnerables ?faces-redirect=true
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_WARN, "Aviso", "Usuario o credenciales incorrectas"));
            }
            
           
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "Aviso", "Error"));
        }
        return redireccion;
    }
}
