/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.oficina.beans;

import br.com.oficina.exceptions.ProprietarioExisteException;
import br.com.oficina.interfaces.ProprietarioDAO;
import br.com.oficina.modelo.Proprietario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author guispiger
 */
@Named(value = "proprietarioBean")
@SessionScoped
public class ProprietarioBean implements Serializable {

    private Proprietario proprietario;

    private List<Proprietario> proprietarios;

    @EJB(lookup = "java:global/Oficina-ejb/ProprietarioService!br.com.oficina.interfaces.ProprietarioDAO")
    private ProprietarioDAO proprietarioDAO;

    /**
     * Creates a new instance of Proprietario
     */
    public ProprietarioBean() {
    }

    @PostConstruct
    public void init() {
        proprietario = new Proprietario();
        carregarProprietarios();
    }

    private void carregarProprietarios() {
        proprietarios = proprietarioDAO.listar();
    }

    public void cadastrar() {
        try {
            proprietarioDAO.inserir(proprietario);
            carregarProprietarios();
            proprietario = new Proprietario();
        } catch (ProprietarioExisteException ex) {
            Logger.getLogger(ProprietarioBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(),
                            String.format("Não foi possivel cadastrar o proprietario com CPF: %s pois ele já está cadastrado!",
                                    proprietario.getCpf())));
        }
    }

    public List<SelectItem> getItensProprietario() {
        LinkedList<SelectItem> items = new LinkedList<>();
        items.add(new SelectItem(null, "Selecione..."));
        try {
            for (Proprietario p : proprietarioDAO.listar()) {
                items.add(new SelectItem(p, p.getNome()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public List<Proprietario> getProprietarios() {
        return proprietarios;
    }

    public void setProprietarios(List<Proprietario> proprietarios) {
        this.proprietarios = proprietarios;
    }

    public ProprietarioDAO getProprietarioDAO() {
        return proprietarioDAO;
    }

    public void setProprietarioDAO(ProprietarioDAO proprietarioDAO) {
        this.proprietarioDAO = proprietarioDAO;
    }

}
