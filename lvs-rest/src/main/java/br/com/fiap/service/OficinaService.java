package br.com.fiap.service;

import java.sql.SQLException;
import java.util.List;
import br.com.fiap.beans.Oficina;
import br.com.fiap.dao.OficinasDAO;

public class OficinaService {
    private OficinasDAO oficinasDAO;
    
    public OficinaService() throws SQLException, ClassNotFoundException {
        this.oficinasDAO = new OficinasDAO();
    }

    public boolean cadastrarOficina(Oficina oficina) throws SQLException {
        try {
            return oficinasDAO.inserir(oficina);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar oficina: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletarOficina(String nome) throws SQLException {
        try {
            return oficinasDAO.deletar(nome);
        } catch (SQLException e) {
            System.out.println("Erro ao deletar oficina: " + e.getMessage());
            throw e;
        }
    }

    public String atualizarOficina(Oficina oficina) throws SQLException {
        try {
            return oficinasDAO.atualizar(oficina);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar oficina: " + e.getMessage());
            throw e;
        }
    }

    public Oficina selecionarOficina(String nome) throws SQLException {
        try {
            return oficinasDAO.selecionar(nome);
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar oficina: " + e.getMessage());
            throw e;
        }
    }

    public List<Oficina> selecionarTodasOficinas() throws SQLException {
        try {
            return oficinasDAO.selecionarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar todas as oficinas: " + e.getMessage());
            throw e;
        }
    }
}