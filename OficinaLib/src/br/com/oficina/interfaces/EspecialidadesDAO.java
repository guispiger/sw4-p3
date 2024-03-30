/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.oficina.interfaces;

import br.com.oficina.modelo.Especialidade;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author aluno
 */
@Remote
public interface EspecialidadesDAO {
    public List<Especialidade> listar();
    
    public Especialidade buscar(Long id);
}
