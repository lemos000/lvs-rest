package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Oficina;
import br.com.fiap.conexoes.ConexaoFactory;

public class OficinasDAO implements AutoCloseable {
    
    private Connection minhaConexao;
    
    public OficinasDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }
    
    public boolean inserir(Oficina oficina) throws SQLException {
        String sql = "INSERT INTO T_OFICINAS (nome, endereco, contato, status) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, oficina.getNome());
            stmt.setString(2, oficina.getEndereco());
            stmt.setString(3, oficina.getContato());
            stmt.setString(4, oficina.getStatus());

            stmt.executeUpdate();
            stmt.close();
            minhaConexao.close();

            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir oficina: " + e.getMessage());
            minhaConexao.close();

            throw e; 
        }
    }

    public boolean deletar(String nome) throws SQLException {
        String sql = "DELETE FROM t_oficinas WHERE nome = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.execute();
            stmt.close();
            minhaConexao.close();

            return true;
        }
    }

    public String atualizar(Oficina oficina) throws SQLException {
        String sql = "UPDATE T_Oficinas SET ENDERECO = ?, CONTATO = ?, STATUS = ? WHERE NOME = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, oficina.getEndereco());
            stmt.setString(2, oficina.getContato());
            stmt.setString(3, oficina.getStatus());
            stmt.setString(4, oficina.getNome());
            stmt.executeUpdate();
            stmt.close();
            minhaConexao.close();
            return "Atualizado com Sucesso!";
        }
    }

    public Oficina selecionar(String nome) throws SQLException {
        String sql = "SELECT * FROM T_oficinas WHERE nome = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stmt.close();
                    return new Oficina(
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("contato"),
                        rs.getString("status")
                    );
                }
            }
        } finally {
        minhaConexao.close();
    }
    return null;
    }

    public List<Oficina> selecionarTodos() throws SQLException {
        List<Oficina> listaOficinas = new ArrayList<>();
        String sql = "SELECT * FROM T_Oficinas ORDER BY STATUS";

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Oficina oficina = new Oficina(
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("contato"),
                    rs.getString("status")
                );
                listaOficinas.add(oficina);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao selecionar todos as oficinas: " + e.getMessage());
            throw e;
        } finally {
            if (minhaConexao != null && !minhaConexao.isClosed()) {
                minhaConexao.close();
            }
        }

        return listaOficinas;
    }

    @Override
    public void close() throws Exception {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }
}