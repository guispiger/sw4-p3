/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.oficina.beans;

import br.com.oficina.interfaces.MecanicoDAO;
import br.com.oficina.modelo.Especialidade;
import br.com.oficina.modelo.Mecanico;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author guispiger
 */
@Named(value = "mecanicoBean")
@ApplicationScoped
public class MecanicoBean implements Serializable {

    private List<Mecanico> mecanicos;

    private Especialidade especialidade = null;

    @EJB(lookup = "java:global/Oficina-ejb/MecanicoService!br.com.oficina.interfaces.MecanicoDAO")
    private MecanicoDAO mecanicoDao;

    /**
     * Creates a new instance of MecanicoBean
     */
    public MecanicoBean() {
    }

    @PostConstruct
    public void init() {
        carregaMecanicos();
    }

    public void carregaMecanicos() {
        if(this.especialidade == null){
            this.mecanicos = mecanicoDao.listar();
        } else{
            this.mecanicos = mecanicoDao.listarPorEspecialidade(this.especialidade);
        }
    }
    
    public void carregaMecanicos(Especialidade e) {
        if(e == null){
            this.mecanicos = mecanicoDao.listar();
        } else{
            this.mecanicos = mecanicoDao.listarPorEspecialidade(e);
        }
    }
    
    public List<SelectItem> getItensMecanico() {
        LinkedList<SelectItem> items = new LinkedList<>();
        items.add(new SelectItem(null, "Selecione..."));
        try {
            for (Mecanico m : mecanicos) {
                items.add(new SelectItem(m, m.getNome()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public List<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(List<Mecanico> mecanicos) {
        this.mecanicos = mecanicos;
    }

    public MecanicoDAO getMecanicoDao() {
        return mecanicoDao;
    }

    public void setMecanicoDao(MecanicoDAO mecanicoDao) {
        this.mecanicoDao = mecanicoDao;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

}
