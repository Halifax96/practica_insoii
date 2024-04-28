/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.CategoriaFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Categoria;

/**
 *
 * @author esteb
 */

@Named
@ViewScoped
public class CategoriaController  implements Serializable{
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    @Inject
    private Categoria categoria;
    
    @PostConstruct
    public void init(){
        //categoria = new Categoria();
    }
    
    
    
    public void registrar(){
        try{
    
             categoriaEJB.create(categoria);
        }catch(Exception e){
            System.out.println("Error al registrar usuario " + e.getMessage());
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}
    
    

