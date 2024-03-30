/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.oficina.ejb;

import br.com.oficina.exceptions.EspecialidadeLotadaException;
import br.com.oficina.interfaces.AgendamentoDAO;
import br.com.oficina.interfaces.EspecialidadesDAO;
import br.com.oficina.modelo.Agendamento;
import br.com.oficina.modelo.Especialidade;
import br.com.oficina.modelo.Mecanico;
import static com.oracle.wls.shaded.org.apache.xalan.lib.ExsltDatetime.date;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author guispiger
 */
@Stateless
public class AgendamentoService implements AgendamentoDAO {

    @PersistenceContext
    EntityManager em;
    
    @EJB(lookup = "java:global/Oficina-ejb/EspecialidadeService!br.com.oficina.interfaces.EspecialidadesDAO")
    private EspecialidadesDAO EspecialidadesDAO;

    @Override
    public List<Agendamento> listar() {
        return em.createQuery("SELECT a FROM Agendamento a ORDER BY a.data").getResultList();
    }

    @Override
    public List<Agendamento> listarPorEspecialidade(Especialidade e) {
        return em.createQuery("SELECT a FROM Agendamento a WHERE a.especialidade = :especialidade ORDER BY a.data")
                .setParameter("especialidade", e)
                .getResultList();
    }

    @Override
    public List<Agendamento> listarPorMecanico(Mecanico m) {
        return em.createQuery("SELECT a FROM Agendamento a WHERE a.mecanico = :mecanico ORDER BY a.data")
                .setParameter("mecanico", m)
                .getResultList();
    }

    @Override
    public List<Agendamento> listarPorEspecMecanico(Especialidade e, Mecanico m) {
        return em.createQuery("SELECT a "
                + "              FROM Agendamento a "
                + "             WHERE a.mecanico = :mecanico "
                + "               AND a.especialidade = :especialidade "
                + "             ORDER BY a.data")
                .setParameter("mecanico", m)
                .setParameter("especialidade", e)
                .getResultList();
    }
    
    @Override
    public List<Agendamento> listarPorData(Date data) {
        return em.createQuery("SELECT a FROM Agendamento a WHERE a.data = :data")
                .setParameter("data", data)
                .getResultList();
                
    }

    @Override
    public Agendamento buscar(Long id) {
        return em.find(Agendamento.class, id);
    }

    @Override
    public void remover(Agendamento a) {
        em.remove(em.merge(a));
    }

    @Override
    public void inserir(Agendamento a) throws EspecialidadeLotadaException {
        int vagas = EspecialidadesDAO.buscar(a.getEspecialidade().getId()).getVagas();
        long numAgendEspec;
        StringBuilder sb = new StringBuilder(300);
        sb.append("SELECT COUNT(a) FROM Agendamento a WHERE a.data = :data");      
        if (a.getPeriodo() != 'I') {
            sb.append(" AND a.periodo IN ('I', :periodo)");
            numAgendEspec = em.createQuery(sb.toString(), Long.class)
                                            .setParameter("data", a.getData())
                                            .setParameter("periodo", a.getPeriodo())
                                            .getSingleResult();
        } else {
            sb.append(" AND a.periodo IN ('I', 'M', 'V')");
            numAgendEspec = em.createQuery(sb.toString(), Long.class)
                                            .setParameter("data", a.getData())
                                            .getSingleResult();
        }
        if (numAgendEspec >= vagas) {
            throw new EspecialidadeLotadaException();
        }

        em.persist(a);
    }

}
