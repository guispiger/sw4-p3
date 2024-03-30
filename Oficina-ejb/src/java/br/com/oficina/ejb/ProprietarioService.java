/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.oficina.ejb;

import br.com.oficina.exceptions.ProprietarioExisteException;
import br.com.oficina.interfaces.ProprietarioDAO;
import br.com.oficina.modelo.Proprietario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author guispiger
 */
@Stateless
public class ProprietarioService implements ProprietarioDAO{
    
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void inserir(Proprietario p) throws ProprietarioExisteException{
        List<Proprietario> proprietarios = em.createQuery("SELECT p FROM Proprietario p WHERE p.cpf = :cpf")
                .setParameter("cpf", p.getCpf())
                .getResultList();
        if (proprietarios != null && !proprietarios.isEmpty()) {
            throw new ProprietarioExisteException();
        }
        em.persist(p);
    }

    @Override
    public Proprietario buscar(String cpf){
        try {
            return em.find(Proprietario.class, cpf);
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Proprietario> listar() {
        return em.createQuery("SELECT p FROM Proprietario p ORDER BY p.nome").getResultList();
    }
    
}
