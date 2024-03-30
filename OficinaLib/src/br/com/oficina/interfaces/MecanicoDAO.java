/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.oficina.interfaces;

import br.com.oficina.modelo.Especialidade;
import br.com.oficina.modelo.Mecanico;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author guispiger
 */
@Remote
public interface MecanicoDAO {
    public List<Mecanico> listar();
    
    public List<Mecanico> listarPorEspecialidade(Especialidade especialidade);
    
    public Mecanico buscar(Long id); 
    
}
