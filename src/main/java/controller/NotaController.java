/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.CategoriaFacadeLocal;
import ejb.NotaFacade;
import ejb.NotaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Categoria;
import model.Nota;
import model.Persona;
import model.Usuario;

/**
 *
 * @author esteb
 */

@Named
@ViewScoped
public class NotaController implements Serializable{
   
    
    @EJB
    private NotaFacadeLocal notaEJB;
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @Inject
    private Nota nota;
    @Inject
    private Categoria categoria;
    
    private List<Categoria> categorias;


    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    @PostConstruct
    public void init(){
       categorias = categoriaEJB.findAll();
    }
    
    
    public void registrar(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        nota.setCategoria(categoria);
        nota.setPersona(us.getCodigo());
        notaEJB.create(nota);
    }
    
}
