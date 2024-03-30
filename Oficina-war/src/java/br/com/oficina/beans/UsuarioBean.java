/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.oficina.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author guispiger
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    private String usuario;

    private String senha;

    private boolean logado;

    private String nome;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public String getNome() {
        return nome;
    }

    public String login() {
        if (usuario.equals("user") && senha.equals("123")) {
            logado = true;
            nome = "Administrador";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Usuário e/ou senha inválidos!",
                                "Tente novamente!"));
        }
        return null;
    }
    
    public String logout(){
        logado = false;
        nome = null;
        usuario = null;
        senha = null;
        
        return "index.jsf";
    }
}
