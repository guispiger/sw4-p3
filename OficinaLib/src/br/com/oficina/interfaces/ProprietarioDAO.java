/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.oficina.interfaces;

import br.com.oficina.exceptions.ProprietarioExisteException;
import br.com.oficina.modelo.Proprietario;
import java.util.List;
import javax.ejb.Remote;
import javax.persistence.NoResultException;

/**
 *
 * @author guispiger
 */
@Remote
public interface ProprietarioDAO {
    public void inserir(Proprietario p) throws ProprietarioExisteException;
    
    public Proprietario buscar(String cpf);
    
    public List<Proprietario> listar();
}
