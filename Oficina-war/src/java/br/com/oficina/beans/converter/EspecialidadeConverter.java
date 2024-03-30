/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.oficina.beans.converter;

import br.com.oficina.interfaces.EspecialidadesDAO;
import br.com.oficina.modelo.Especialidade;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author guispiger
 */
@Named("EspecConverter")
@ApplicationScoped
public class EspecialidadeConverter implements Converter<Especialidade> {

    @EJB(lookup = "java:global/Oficina-ejb/EspecialidadeService!br.com.oficina.interfaces.EspecialidadesDAO")
    private EspecialidadesDAO especialidadeDao;

    @Override
    public Especialidade getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long id = Long.valueOf(value);
            return especialidadeDao.buscar(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Especialidade value) {
        if (value == null || value.getId() == null) {
            return null;
        } else {
            return value.getId().toString();
        }
    }

}
