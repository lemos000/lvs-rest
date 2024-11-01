package br.com.fiap.main;

import java.sql.SQLException;

import br.com.fiap.beans.User;
import br.com.fiap.dao.UserDAO;

public class TesteSelecionar {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		User user = dao.selecionar(5);
		if(user != null) {
			System.out.println(user);
			}
		}

	}


