/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
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
public class PlantillaController implements Serializable{
    
    
    
    public void verificarSesion(){
        try{
            
            
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if(us==null){
                context.getExternalContext().redirect("./../permisos.xhtml");
            }
            
        }catch(Exception e){
            //log almacenar registro de error
        }
    }
}
