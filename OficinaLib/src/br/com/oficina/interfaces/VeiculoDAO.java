/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.oficina.interfaces;

import br.com.oficina.exceptions.VeiculoExisteException;
import br.com.oficina.modelo.Proprietario;
import br.com.oficina.modelo.Veiculo;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author guispiger
 */
@Remote
public interface VeiculoDAO {
    public void inserir(Veiculo v) throws VeiculoExisteException;
    
    public List<Veiculo> listar();
    
    public List<Veiculo> listarPorProprietario(Proprietario p);
    
    public Veiculo buscar(String placa);
}
