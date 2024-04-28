/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.NotaFacadeLocal;
import java.util.List;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Nota;

/**
 *
 * @author esteb
 */
@Named
@RequestScoped
public class ComentarController implements Serializable {
    
    @EJB
    private NotaFacadeLocal notaEJB;
    private List<Nota> notas;
    private Nota nota;

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
    
    

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    @PostConstruct
    public void init() {
       notas =  notaEJB.findAll();
       nota = new Nota();
    }  
    
    public void asignar(Nota nota){
        this.nota = nota;
    }
    
    public void eliminar(Nota n){
        try{
            this.notaEJB.remove(n);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO, "Aviso", "Se ha eliminado correctamente"));
        }catch(Exception e){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "Aviso", "Error"));
        }
    }
}
