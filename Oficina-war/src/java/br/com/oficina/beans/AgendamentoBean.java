/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.oficina.beans;

import br.com.oficina.exceptions.EspecialidadeLotadaException;
import br.com.oficina.interfaces.AgendamentoDAO;
import br.com.oficina.interfaces.VeiculoDAO;
import br.com.oficina.modelo.Agendamento;
import br.com.oficina.modelo.Especialidade;
import br.com.oficina.modelo.Mecanico;
import br.com.oficina.modelo.Proprietario;
import br.com.oficina.modelo.Veiculo;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
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
@Named(value = "agendamentoBean")
@SessionScoped
public class AgendamentoBean implements Serializable {

    private List<Agendamento> agendamentos;

    private Especialidade especialidade = null;

    private Mecanico mecanico = null;

    private Agendamento agendamento = null;

    private Proprietario proprietario;

    private List<Veiculo> veiculos;

    @EJB(lookup = "java:global/Oficina-ejb/VeiculoService!br.com.oficina.interfaces.VeiculoDAO")
    private VeiculoDAO veiculoDAO;

    @EJB(lookup = "java:global/Oficina-ejb/AgendamentoService!br.com.oficina.interfaces.AgendamentoDAO")
    private AgendamentoDAO agendamentoDAO;

    /**
     * Creates a new instance of AgendamentoBean
     */
    public AgendamentoBean() {
    }

    @PostConstruct
    public void init() {
        agendamento = new Agendamento();
        carregaAgendamentos();
    }

    public void carregaAgendamentos() {
        if (mecanico != null && especialidade == null) {
            agendamentos = agendamentoDAO.listarPorMecanico(mecanico);
        } else if (mecanico == null && especialidade != null) {
            agendamentos = agendamentoDAO.listarPorEspecialidade(especialidade);
        } else if (mecanico != null && especialidade != null) {
            agendamentos = agendamentoDAO.listarPorEspecMecanico(especialidade, mecanico);
        } else {
            agendamentos = agendamentoDAO.listar();
        }
    }

    public void cancelar(Agendamento a) {
        agendamentoDAO.remover(a);
        carregaAgendamentos();
    }

    public String agendar() {
        try {
            if (proprietario == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Proprietário não cadastrado!",
                                "Cadastre o proprietário antes de fazer um agendamento!"));
                return "proprietarios.jsf";
            } else if (agendamento.getVeiculo() == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Veículo não cadastrado!",
                                "Cadastre o veículo antes de fazer um agendamento!"));
                return "veiculos.jsf";
            } else {
                veiculos = veiculoDAO.listarPorProprietario(proprietario);
                if (!veiculos.contains(agendamento.getVeiculo())) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Veículo não localizado!",
                                    "O Veículo não está associado a este proprietário"));
                    agendamento.setVeiculo(null);
                    return null;
                }
            }

            if (agendamento.getData() != null && !agendamento.getData().after(Date.from(Instant.now()))) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Data inválida!",
                                "A data deve ser futura"));
                return null;
            }

            if (agendamento.getData() == null ||
                agendamento.getPeriodo() == 0 ||
                agendamento.getReclamacao() == null ||
                agendamento.getMecanico() == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Informe os demais dados!"));
                return null;
            }
            agendamentoDAO.inserir(agendamento);
            agendamento = new Agendamento();
            proprietario = null;
            carregaAgendamentos();
            return "agendamentos.jsf";
        } catch (EspecialidadeLotadaException ex) {
            Logger.getLogger(AgendamentoBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ex.getMessage(),
                            String.format("Não foi possivel realizar o agendamento, não há mais vagas para a especialidade %s, no dia %s",
                                    agendamento.getEspecialidade().getNome(),
                                    new SimpleDateFormat("dd/MM/YYYY").format(agendamento.getData()))));
            return null;

        }
    }

    public void limpar() {
        proprietario = null;
        agendamento.setVeiculo(null);
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public AgendamentoDAO getAgendamentoDAO() {
        return agendamentoDAO;
    }

    public void setAgendamentoDAO(AgendamentoDAO agendamentoDAO) {
        this.agendamentoDAO = agendamentoDAO;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
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
