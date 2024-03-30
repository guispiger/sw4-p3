/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.oficina.ejb;

import br.com.oficina.interfaces.EspecialidadesDAO;
import br.com.oficina.modelo.Especialidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aluno
 */
@Stateless
public class EspecialidadeService implements EspecialidadesDAO {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Especialidade> listar() {
        return em.createQuery("SELECT e FROM Especialidade e order by e.nome").getResultList();
    }

    @Override
    public Especialidade buscar(Long id) {
        try {
            return em.find(Especialidade.class, id);        
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
