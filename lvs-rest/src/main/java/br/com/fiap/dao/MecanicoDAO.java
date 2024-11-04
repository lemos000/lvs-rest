package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Mecanico;
import br.com.fiap.conexoes.ConexaoFactory;

public class MecanicoDAO implements AutoCloseable {
    
    private Connection minhaConexao;
    
    public MecanicoDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }
    
    public boolean inserir(Mecanico mecanico) throws SQLException {
        String sql = "INSERT INTO T_MECANICOS (nome, cnpj, celular1, celular2, telefone1, telefone2, email, endereco_id, numero, complemento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, mecanico.getNome());
            stmt.setString(2, mecanico.getCnpj());
            stmt.setString(3, mecanico.getCelular1());
            stmt.setString(4, mecanico.getCelular2());
            stmt.setString(5, mecanico.getTelefone1());
            stmt.setString(6, mecanico.getTelefone2());
            stmt.setString(7, mecanico.getEmail());
            stmt.setInt(8, mecanico.getEndereco_id());
            stmt.setString(9, mecanico.getNumero());
            stmt.setString(10, mecanico.getComplemento());
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir mecânico: " + e.getMessage());
            throw e;
        } finally {
            minhaConexao.close();
        }
    }

    public boolean deletar(int mecanicoId) throws SQLException {
        String sql = "DELETE FROM T_MECANICOS WHERE mecanico_id = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, mecanicoId);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar mecânico: " + e.getMessage());
            throw e;
        } finally {
            minhaConexao.close();
        }
    }

    public String atualizar(Mecanico mecanico) throws SQLException {
        String sql = "UPDATE T_MECANICOS SET nome = ?, cnpj = ?, celular1 = ?, celular2 = ?, telefone1 = ?, telefone2 = ?, email = ?, endereco_id = ?, numero = ?, complemento = ? WHERE mecanico_id = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, mecanico.getNome());
            stmt.setString(2, mecanico.getCnpj());
            stmt.setString(3, mecanico.getCelular1());
            stmt.setString(4, mecanico.getCelular2());
            stmt.setString(5, mecanico.getTelefone1());
            stmt.setString(6, mecanico.getTelefone2());
            stmt.setString(7, mecanico.getEmail());
            stmt.setInt(8, mecanico.getEndereco_id());
            stmt.setString(9, mecanico.getNumero());
            stmt.setString(10, mecanico.getComplemento());
            stmt.setInt(11, mecanico.getMecanico_id());
            stmt.executeUpdate();
            return "Atualizado com sucesso!";
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar mecânico: " + e.getMessage());
            throw e;
        } finally {
            minhaConexao.close();
        }
    }

    public Mecanico selecionar(int mecanicoId) throws SQLException {
        String sql = "SELECT * FROM T_MECANICOS WHERE mecanico_id = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, mecanicoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Mecanico(
                        rs.getInt("mecanico_id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("celular1"),
                        rs.getString("celular2"),
                        rs.getString("telefone1"),
                        rs.getString("telefone2"),
                        rs.getString("email"),
                        rs.getInt("endereco_id"),
                        rs.getString("numero"),
                        rs.getString("complemento")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao selecionar mecânico: " + e.getMessage());
            throw e;
        } finally {
            minhaConexao.close();
        }
        return null;
    }

    public List<Mecanico> selecionarTodos() throws SQLException {
        List<Mecanico> listaMecanicos = new ArrayList<>();
        String sql = "SELECT * FROM T_MECANICOS ORDER BY nome";
        
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Mecanico mecanico = new Mecanico(
                    rs.getInt("mecanico_id"),
                    rs.getString("nome"),
                    rs.getString("cnpj"),
                    rs.getString("celular1"),
                    rs.getString("celular2"),
                    rs.getString("telefone1"),
                    rs.getString("telefone2"),
                    rs.getString("email"),
                    rs.getInt("endereco_id"),
                    rs.getString("numero"),
                    rs.getString("complemento")
                );
          
                listaMecanicos.add(mecanico);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao selecionar todos os mecânicos: " + e.getMessage());
            throw e;
        } finally {
            minhaConexao.close();
        }
        
        return listaMecanicos;
    }

    @Override
    public void close() throws Exception {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }
}