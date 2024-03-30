/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.oficina.ejb;

import br.com.oficina.interfaces.MecanicoDAO;
import br.com.oficina.modelo.Especialidade;
import br.com.oficina.modelo.Mecanico;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author guispiger
 */
@Stateless
public class MecanicoService implements MecanicoDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Mecanico> listar() {
        return em.createQuery("SELECT m FROM Mecanico m ORDER BY m.nome").getResultList();
    }

    @Override
    public List<Mecanico> listarPorEspecialidade(Especialidade especialidade) {
        return em.createQuery("SELECT m FROM Mecanico m WHERE M.especialidade = :especialidade ORDER BY m.nome")
                .setParameter("especialidade", especialidade)
                .getResultList();
    }

    @Override
    public Mecanico buscar(Long id) {
        return em.find(Mecanico.class, id);
    }

}
