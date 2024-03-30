/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.oficina.beans;

import br.com.oficina.interfaces.EspecialidadesDAO;
import br.com.oficina.modelo.Especialidade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author aluno
 */
@Named(value = "especialidadeBean")
@ApplicationScoped
public class EspecialidadeBean implements Serializable {

    private List<Especialidade> especialidades;

    @EJB(lookup = "java:global/Oficina-ejb/EspecialidadeService!br.com.oficina.interfaces.EspecialidadesDAO")
    EspecialidadesDAO especialidadeDao;

    /**
     * Creates a new instance of EspecialidadeBean
     */
    public EspecialidadeBean() {
    }

    @PostConstruct
    public void init() {
        especialidades = especialidadeDao.listar();
    }

    public List<SelectItem> getItensEspecialidade() {
        LinkedList<SelectItem> items = new LinkedList<>();
        items.add(new SelectItem(null, "Selecione..."));
        try {
            for (Especialidade e : especialidadeDao.listar()) {
                items.add(new SelectItem(e, e.getNome()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return items;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public EspecialidadesDAO getEspecialidadeDao() {
        return especialidadeDao;
    }

    public void setEspecialidadeDao(EspecialidadesDAO especialidadeDao) {
        this.especialidadeDao = especialidadeDao;
    }

}
