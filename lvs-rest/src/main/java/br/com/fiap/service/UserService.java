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
    
    public boolean verificarUser(String email, String senha) throws SQLException {
        try {
            User user = userDAO.selecionarPorEmailESenha(email, senha);
            return user != null;
        } catch (SQLException e) {
            System.out.println("Erro ao verificar usuário: " + e.getMessage());
            throw e;
        }
    }
	public User selecionarUser(int id) throws ClassNotFoundException, SQLException {
		UserDAO userdao = new UserDAO();
		return userdao.selecionar(id);
	}
	public List<User> selecionarTodosUsers() throws ClassNotFoundException, SQLException {
	    UserDAO userDao = new UserDAO();
	    return userDao.selecionarTodos();
	}

	
	public boolean cadastrarUser(User user) throws ClassNotFoundException, SQLException {
	    UserDAO userDao = new UserDAO();
	    boolean jaExiste = userDao.usuarioExiste(user.getEmail());
	    if (jaExiste) {
	        return false;  
	    }
	  
        return userDao.inserir(user);
	}
		
	public boolean deletarUser(int id) throws SQLException, ClassNotFoundException {
		UserDAO userDAO = new UserDAO();
		return userDAO.deletar(id);
	}
	 
	
	  public String atualizarUsuario(User user) throws ClassNotFoundException, SQLException {
		  UserDAO userDao = new UserDAO();
	        try {
	            return userDao.atualizar(user);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return "Erro ao atualizar: " + e.getMessage();
	        }
	    }
}
