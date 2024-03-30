/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.oficina.beans.converter;

import br.com.oficina.interfaces.MecanicoDAO;
import br.com.oficina.modelo.Mecanico;
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
@Named("MecanicoConverter")
@ApplicationScoped
public class MecanicoConverter implements Converter<Mecanico>{
    
    @EJB
    private MecanicoDAO mecanicoDao;

    @Override
    public Mecanico getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long id = Long.valueOf(value);
            return mecanicoDao.buscar(id);
        } catch (Exception e) {
            return null;
        } 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Mecanico value) {
        if (value == null || value.getId() == null) {
            return null;
        } else {
            return value.getId().toString();
        }
    }
    
}
