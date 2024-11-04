package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.User;
import br.com.fiap.conexoes.ConexaoFactory;

public class UserDAO {
	
	public Connection minhaConexao;
	
	public UserDAO() throws ClassNotFoundException, SQLException {
		super();
		this.minhaConexao = new ConexaoFactory().conexao();
	}
	// Insert 
	
	public boolean inserir(User user) throws SQLException {
	    String sql = "INSERT INTO T_USERS (nome, email, senha) VALUES (?, ?, ?)";
	    
	    String[] generatedColumns = {"user_id"};

	    try (PreparedStatement stmt = minhaConexao.prepareStatement(sql, generatedColumns)) {
	        stmt.setString(1, user.getNome());
	        stmt.setString(2, user.getEmail());
	        stmt.setString(3, user.getSenha());

	        int linhasAfetadas = stmt.executeUpdate();

	        if (linhasAfetadas > 0) {
	            try (ResultSet rs = stmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    user.setId(rs.getInt(1));
	                }
	            }
	            return true;
	        }
	        stmt.close();
	        
	        return false;
	    } catch (SQLException e) {
	        System.err.println("Erro ao inserir usuário: " + e.getMessage());
	        throw e; 
	    }
	}

	
	// Delete
	public boolean deletar(int id) throws SQLException {
		PreparedStatement stmt = minhaConexao.prepareStatement
				("Delete from t_users where user_id = ?");
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();		
		return true;
	}
	// UpDate 
	public String atualizar(User user) throws SQLException {
		String sql = ("Update T_Users set NOME = ?, EMAIL = ?, SENHA = ? where user_id = ?");
		PreparedStatement stmt = minhaConexao.prepareStatement(sql);
				stmt.setString(1, user.getNome());
				stmt.setString(2, user.getEmail());
				stmt.setString(3, user.getSenha());
				stmt.setInt(4, user.getId());
				stmt.executeUpdate();
				stmt.close();	
		return "Atualizado com Sucesso!";
	}
	// Select 
	public User selecionar(int id) throws SQLException{
		PreparedStatement stmt = minhaConexao.prepareStatement
				("SELECT * FROM T_Users where user_id = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		 if (rs.next()) {
             User user = new User();
             user.setId(rs.getInt("user_id"));
             user.setNome(rs.getString("nome"));
             user.setEmail(rs.getString("email"));
             user.setSenha(rs.getString("senha"));
             rs.close();
             stmt.close();
             return user;
         }
		 return null;
	}
	public List<User> selecionarTodos() throws SQLException {
	    List<User> listaUsers = new ArrayList<>();
	    
	    PreparedStatement stmt = minhaConexao.prepareStatement
	            ("SELECT * FROM T_Users ORDER BY user_id");
	    ResultSet rs = stmt.executeQuery();
	    
	    while (rs.next()) {
	        User user = new User();
	        user.setId(rs.getInt("user_id"));
	        user.setNome(rs.getString("nome"));
	        user.setEmail(rs.getString("email"));
	        user.setSenha(rs.getString("senha"));
	        listaUsers.add(user);
	    }
	    
	    rs.close();
	    stmt.close();
	    return listaUsers;
	}
	

	   public User selecionarPorEmailESenha(String email, String senha) throws SQLException {
	        String sql = "SELECT * FROM T_Users WHERE email = ? AND senha = ?";
	        
	        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
	            stmt.setString(1, email.trim());
	            stmt.setString(2, senha.trim());
	            
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    User user = new User();
	                    user.setId(rs.getInt("user_id"));
	                    user.setNome(rs.getString("nome"));
	                    user.setEmail(rs.getString("email"));
	                    user.setSenha(rs.getString("senha"));
	    	            rs.close();
	    	            stmt.close();
	                    return user;

	                }
	                stmt.close();
	                return null;
	            }
	            
	        } catch (SQLException e) {
	            System.out.println("Erro ao verificar usuário: " + e.getMessage());
	      
	            throw e;
	        }
	    }
	   public boolean usuarioExiste(String email) throws SQLException{
			PreparedStatement stmt = minhaConexao.prepareStatement
					("SELECT * FROM T_Users where email = ?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				stmt.close();
				return true;
			} else {
				stmt.close();
				return false;
			}
		}
	   
	

}
