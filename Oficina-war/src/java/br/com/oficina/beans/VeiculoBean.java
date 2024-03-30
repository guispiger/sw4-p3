/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.oficina.beans;

import br.com.oficina.exceptions.VeiculoExisteException;
import br.com.oficina.interfaces.VeiculoDAO;
import br.com.oficina.modelo.Veiculo;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author guispiger
 */
@Named(value = "veiculoBean")
@SessionScoped
public class VeiculoBean implements Serializable {

    private Veiculo veiculo;

    private List<Veiculo> veiculos;

    @EJB(lookup = "java:global/Oficina-ejb/VeiculoService!br.com.oficina.interfaces.VeiculoDAO")
    private VeiculoDAO veiculoDAO;

    /**
     * Creates a new instance of VeiculoBean
     */
    public VeiculoBean() {
    }

    @PostConstruct
    public void init() {
        veiculo = new Veiculo();
        carregarVeiculos();
    }
    
    public void cadastrar(){
        try {
            veiculoDAO.inserir(veiculo);
            carregarVeiculos();
            veiculo = new Veiculo();
        } catch (VeiculoExisteException ex) {
            Logger.getLogger(VeiculoBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(),
                            String.format("Não foi possivel cadastrar o veículo Placa: %s pois ele já está cadastrado!",
                                    veiculo.getPlaca())));
        }
    }
    
    public void carregarVeiculos(){
        veiculos = veiculoDAO.listar();
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public VeiculoDAO getVeiculoDAO() {
        return veiculoDAO;
    }

    public void setVeiculoDAO(VeiculoDAO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }
    
    

}
