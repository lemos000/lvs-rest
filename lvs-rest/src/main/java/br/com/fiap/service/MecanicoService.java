package br.com.fiap.service;

import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.Mecanico;
import br.com.fiap.dao.MecanicoDAO;

public class MecanicoService {
    private MecanicoDAO mecanicoDAO;
    
    public MecanicoService() throws SQLException, ClassNotFoundException {
        this.mecanicoDAO = new MecanicoDAO();
    }

    public boolean cadastrarMecanico(Mecanico mecanico) throws SQLException {
        try {
            return mecanicoDAO.inserir(mecanico);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar mecânico: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletarMecanico(int mecanicoId) throws SQLException {
        try {
            return mecanicoDAO.deletar(mecanicoId);
        } catch (SQLException e) {
            System.out.println("Erro ao deletar mecânico: " + e.getMessage());
            throw e;
        }
    }

    public String atualizarMecanico(Mecanico mecanico) throws SQLException {
        try {
            return mecanicoDAO.atualizar(mecanico);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar mecânico: " + e.getMessage());
            throw e;
        }
    }

    public Mecanico selecionarMecanico(int mecanicoId) throws SQLException {
        try {
            return mecanicoDAO.selecionar(mecanicoId);
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar mecânico: " + e.getMessage());
            throw e;
        }
    }

    public List<Mecanico> selecionarTodosMecanicos() throws SQLException {
        try {
            return mecanicoDAO.selecionarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar todos os mecânicos: " + e.getMessage());
            throw e;
        }
    }
}