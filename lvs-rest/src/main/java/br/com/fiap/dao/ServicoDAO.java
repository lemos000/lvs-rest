package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Servico;
import br.com.fiap.conexoes.ConexaoFactory;

public class ServicoDAO implements AutoCloseable {

    private Connection minhaConexao;

    public ServicoDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public boolean inserir(Servico servico) throws SQLException {
        String sql = "INSERT INTO T_SERVICOS (descricao, tipo_veiculo, preco, exigencia_tecnica, duracao) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, servico.getDescricao());
            stmt.setString(2, servico.getTipoVeiculo());
            stmt.setDouble(3, servico.getPreco());
            stmt.setString(4, servico.getExigenciaTecnica());
            stmt.setString(5, servico.getDuracao());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir serviço: " + e.getMessage());
            throw e;
        }
    }

    public boolean deletar(int servicoId) throws SQLException {
        String sql = "DELETE FROM T_SERVICOS WHERE servico_id = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, servicoId);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar serviço: " + e.getMessage());
            throw e;
        }
    }

    public String atualizar(Servico servico) throws SQLException {
        String sql = "UPDATE T_SERVICOS SET descricao = ?, tipo_veiculo = ?, preco = ?, exigencia_tecnica = ?, duracao = ? WHERE servico_id = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, servico.getDescricao());
            stmt.setString(2, servico.getTipoVeiculo());
            stmt.setDouble(3, servico.getPreco());
            stmt.setString(4, servico.getExigenciaTecnica());
            stmt.setString(5, servico.getDuracao());
            stmt.setInt(6, servico.getServicoId());
            stmt.executeUpdate();
            return "Atualizado com sucesso!";
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar serviço: " + e.getMessage());
            throw e;
        }
    }

    public Servico selecionar(int servicoId) throws SQLException {
        String sql = "SELECT * FROM T_SERVICOS WHERE servico_id = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, servicoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Servico(
                        rs.getInt("servico_id"),
                        rs.getString("descricao"),
                        rs.getString("tipo_veiculo"),
                        rs.getDouble("preco"),
                        rs.getString("exigencia_tecnica"),
                        rs.getString("duracao")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao selecionar serviço: " + e.getMessage());
            throw e;
        }
        return null;
    }

    public List<Servico> selecionarTodos() throws SQLException {
        List<Servico> listaServicos = new ArrayList<>();
        String sql = "SELECT * FROM T_SERVICOS ORDER BY descricao";
        
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Servico servico = new Servico(
                    rs.getInt("servico_id"),
                    rs.getString("descricao"),
                    rs.getString("tipo_veiculo"),
                    rs.getDouble("preco"),
                    rs.getString("exigencia_tecnica"),
                    rs.getString("duracao")
                );
                listaServicos.add(servico);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao selecionar todos os serviços: " + e.getMessage());
            throw e;
        }
        
        return listaServicos;
    }

    @Override
    public void close() throws SQLException {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }
}