package br.com.fiap.service;

import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.Servico;
import br.com.fiap.dao.ServicoDAO;

public class ServicoService {
    private ServicoDAO servicoDAO;
    
    public ServicoService() throws SQLException, ClassNotFoundException {
        this.servicoDAO = new ServicoDAO();
    }

    public boolean cadastrarServico(Servico servico) throws SQLException {
        try {
            return servicoDAO.inserir(servico);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar serviço: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletarServico(int servicoId) throws SQLException {
        try {
            return servicoDAO.deletar(servicoId);
        } catch (SQLException e) {
            System.out.println("Erro ao deletar serviço: " + e.getMessage());
            throw e;
        }
    }

    public String atualizarServico(Servico servico) throws SQLException {
        try {
            return servicoDAO.atualizar(servico);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar serviço: " + e.getMessage());
            throw e;
        }
    }

    public Servico selecionarServico(int servicoId) throws SQLException {
        try {
            return servicoDAO.selecionar(servicoId);
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar serviço: " + e.getMessage());
            throw e;
        }
    }

    public List<Servico> selecionarTodosServicos() throws SQLException {
        try {
            return servicoDAO.selecionarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar todos os serviços: " + e.getMessage());
            throw e;
        }
    }
}