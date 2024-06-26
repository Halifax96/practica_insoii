/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Nota;
import model.Persona;

/**
 *
 * @author esteb
 */
@Stateless
public class NotaFacade extends AbstractFacade<Nota> implements NotaFacadeLocal{
    
    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotaFacade() {
        super(Nota.class);
    }
    
}
