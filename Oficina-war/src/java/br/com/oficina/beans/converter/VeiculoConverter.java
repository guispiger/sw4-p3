/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.oficina.beans.converter;

import br.com.oficina.interfaces.VeiculoDAO;
import br.com.oficina.modelo.Veiculo;
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
@Named("VeiculoConverter")
@ApplicationScoped
public class VeiculoConverter implements Converter<Veiculo>{
    
    @EJB
    private VeiculoDAO veiculoDAO;

    @Override
    public Veiculo getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return veiculoDAO.buscar(value);
        } catch (Exception e) {
            return null;
        } 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Veiculo value) {
        if (value == null || value.getPlaca() == null) {
            return null;
        } else {
            return value.getPlaca();
        }
    }
    
}
