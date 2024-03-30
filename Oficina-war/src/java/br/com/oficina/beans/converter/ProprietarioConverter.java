/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.oficina.beans.converter;

import br.com.oficina.interfaces.ProprietarioDAO;
import br.com.oficina.modelo.Proprietario;
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
@Named("ProprietarioConverter")
@ApplicationScoped
public class ProprietarioConverter implements Converter<Proprietario>{
    
    @EJB
    private ProprietarioDAO proprietarioDAO;

    @Override
    public Proprietario getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return proprietarioDAO.buscar(value);
        } catch (Exception e) {
            return null;
        } 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Proprietario value) {
        if (value == null || value.getCpf() == null) {
            return null;
        } else {
            return value.getCpf();
        }
    }
    
}
