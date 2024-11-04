package br.com.fiap.service;

import java.sql.SQLException;
import java.util.List;



import br.com.fiap.beans.User;
import br.com.fiap.dao.UserDAO;

public class UserService {
    private UserDAO userDAO;
    
    public UserService() throws SQLException, ClassNotFoundException {
        this.userDAO = new UserDAO();
    }
    
    public User verificarUser(String email, String senha) throws SQLException {
        try {
            User user = userDAO.selecionarPorEmailESenha(email, senha);
            return user;
        } catch (SQLException e) {
            System.out.println("Erro ao verificar usuário: " + e.getMessage());
            throw e;
        }
    }
	public User selecionarUser(int id) throws ClassNotFoundException, SQLException {
		return userDAO.selecionar(id);
	}
	public List<User> selecionarTodosUsers() throws ClassNotFoundException, SQLException {
	    return userDAO.selecionarTodos();
	}

	
	public boolean cadastrarUser(User user) throws ClassNotFoundException, SQLException {
	    boolean jaExiste = userDAO.usuarioExiste(user.getEmail());
	    if (jaExiste) {
	        return false;  
	    }
	  
        return userDAO.inserir(user);
	}
		
	public boolean deletarUser(int id) throws SQLException, ClassNotFoundException {
		return userDAO.deletar(id);
	}
	 
	
	  public String atualizarUsuario(User user) throws ClassNotFoundException, SQLException {
	        try {
	            return userDAO.atualizar(user);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return "Erro ao atualizar: " + e.getMessage();
	        }
	    }
}
