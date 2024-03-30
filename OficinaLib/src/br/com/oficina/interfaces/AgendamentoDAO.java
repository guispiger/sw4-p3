/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.oficina.interfaces;

import br.com.oficina.exceptions.EspecialidadeLotadaException;
import br.com.oficina.modelo.Agendamento;
import br.com.oficina.modelo.Especialidade;
import br.com.oficina.modelo.Mecanico;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author guispiger
 */
@Remote
public interface AgendamentoDAO {
    public List<Agendamento> listar();
    
    public List<Agendamento> listarPorEspecialidade(Especialidade e);
    
    public List<Agendamento> listarPorMecanico(Mecanico m);
    
    public List<Agendamento> listarPorEspecMecanico(Especialidade e, Mecanico m);
    
    public List<Agendamento> listarPorData(Date data);
    
    public Agendamento buscar(Long id);
    
    public void remover(Agendamento a);
    
    public void inserir(Agendamento a)throws EspecialidadeLotadaException;
}
