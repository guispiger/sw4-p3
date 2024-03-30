/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.oficina.exceptions;

import java.io.Serializable;

/**
 *
 * @author guispiger
 */
public class VeiculoExisteException extends Exception implements Serializable{

    public VeiculoExisteException() {
        super("Veículo já cadastrado!");
    }
    
}
