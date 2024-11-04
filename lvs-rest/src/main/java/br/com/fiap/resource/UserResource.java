package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.User;
import br.com.fiap.service.UserService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
///lvs/rest/user
@Path("/user")
public class UserResource {		
    
	private UserService userService;
    
    public UserResource() {
        try {
            this.userService = new UserService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    ///lvs/rest/user/verificar?email=xxx&senha=yyy
    @GET
    @Path("/verificar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarUsuario(
            @QueryParam("email") String email,
            @QueryParam("senha") String senha) {

        try {
            if (email == null || senha == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                             .entity("Email e senha são obrigatórios")
                             .build();
            }

            User usuarioValido = userService.verificarUser(email, senha);

            if (usuarioValido != null) {
                return Response.ok(usuarioValido).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                             .entity("Usuário ou senha inválidos")
                             .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                         .entity("Erro ao verificar usuário: " + e.getMessage())
                         .build();
        }
    }
    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(User user) {
    	System.out.println("Conectou");
        try {
            if (user == null || user.getEmail() == null || user.getSenha() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Dados do usuário são obrigatórios")
                               .build();
            }

            boolean isCadastrado = userService.cadastrarUser(user);
            if (isCadastrado) {
                return Response.status(Response.Status.CREATED)
                               .entity("Usuário cadastrado com sucesso")
                               .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Usuário já existe ou erro no cadastro")
                               .build();
            }

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro no banco de dados: " + e.getMessage())
                           .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@QueryParam("id") int id) {
        try {
            User user = userService.selecionarUser(id);
            if (user != null) {
                return Response.ok(user).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                             .entity("Usuário não encontrado")
                             .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                         .entity("Erro ao buscar usuário: " + e.getMessage())
                         .build();
        }
    }
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        try {
            List<User> users = userService.selecionarTodosUsers();
            if (!users.isEmpty()) {
                return Response.accepted(users).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT)
                             .entity("Nenhum usuário encontrado")
                             .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                         .entity("Erro ao listar usuários: " + e.getMessage())
                         .build();
        }
    }
    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(@QueryParam("id") int id, User user) {
        try {
            if (id <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("ID do usuário é obrigatório e deve ser positivo.")
                               .build();
            }

            user.setId(id);

            String resultado = userService.atualizarUsuario(user);
            if (resultado.equals("Atualizado com Sucesso!")) {
                return Response.ok(resultado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Usuário não encontrado para atualizar")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao atualizar usuário: " + e.getMessage())
                           .build();
        }
    }
    @DELETE
    @Path("/deletar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarUsuario(@QueryParam("id") int id) {
        try {
            boolean resultado = userService.deletarUser(id);
            if (resultado) {
                return Response.status(Response.Status.OK)
                               .entity("{ \"message\": \"" + resultado + "\" }")
                               .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{ \"message\": \"" + resultado + "\" }")
                               .build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{ \"message\": \"Erro: " + e.getMessage() + "\" }")
                           .build();
        }
    }
    @GET
    @Path("/teste")
    @Produces(MediaType.TEXT_PLAIN)
    public String teste() {
        return "API está funcionando!";
    }
    
    
    
    
}