/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.oficina.ejb;

import br.com.oficina.exceptions.VeiculoExisteException;
import br.com.oficina.interfaces.VeiculoDAO;
import br.com.oficina.modelo.Proprietario;
import br.com.oficina.modelo.Veiculo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author guispiger
 */
@Stateless
public class VeiculoService implements VeiculoDAO{
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void inserir(Veiculo v) throws VeiculoExisteException{
        List<Veiculo> veiculos = em.createQuery("SELECT v FROM Veiculo v WHERE v.placa = :placa")
                .setParameter("placa", v.getPlaca())
                .getResultList();
        if (veiculos != null && !veiculos.isEmpty()) {
            throw new VeiculoExisteException();
        }
        em.persist(v);
    }

    @Override
    public List<Veiculo> listar() {
        return em.createQuery("SELECT v FROM Veiculo v ORDER BY v.modelo").getResultList();
    }
    
    @Override
    public List<Veiculo> listarPorProprietario(Proprietario p) {
        return em.createQuery("SELECT v FROM Veiculo v WHERE v.proprietario = :proprietario")
                .setParameter("proprietario", p)
                .getResultList();
    }

    @Override
    public Veiculo buscar(String placa) {
        return em.find(Veiculo.class, placa);
    }

    
}
