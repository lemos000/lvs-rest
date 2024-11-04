package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.Oficina;
import br.com.fiap.service.OficinaService;
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

@Path("/oficinas")
public class OficinaResource {

    private OficinaService oficinaService;

    public OficinaResource() {
        try {
            this.oficinaService = new OficinaService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarOficina(Oficina oficina) {
        try {
            if (oficina == null || oficina.getNome() == null || oficina.getEndereco() == null || oficina.getContato() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Dados da oficina são obrigatórios")
                               .build();
            }

            boolean isCadastrada = oficinaService.cadastrarOficina(oficina);
            if (isCadastrada) {
                return Response.status(Response.Status.CREATED)
                               .entity("Oficina cadastrada com sucesso")
                               .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Erro no cadastro da oficina")
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
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodasOficinas() {
        try {
            List<Oficina> oficinas = oficinaService.selecionarTodasOficinas();
            if (!oficinas.isEmpty()) {
                return Response.ok(oficinas).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT)
                               .entity("Nenhuma oficina encontrada")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao listar oficinas: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarOficina(@QueryParam("nome") String nome) {
        try {
            Oficina oficina = oficinaService.selecionarOficina(nome);
            if (oficina != null) {
                return Response.ok(oficina).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Oficina não encontrada")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao buscar oficina: " + e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarOficina(@QueryParam("nome") String nome, Oficina oficina) {
        try {
            if (nome == null || nome.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Nome da oficina é obrigatório.")
                               .build();
            }

            oficina.setNome(nome);
            String resultado = oficinaService.atualizarOficina(oficina);
            if (resultado.equals("Atualizado com Sucesso!")) {
                return Response.ok(resultado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Oficina não encontrada para atualizar")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao atualizar oficina: " + e.getMessage())
                           .build();
        }
    }

    @DELETE
    @Path("/deletar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarOficina(@QueryParam("nome") String nome) throws ClassNotFoundException {
        try {
            boolean resultado = oficinaService.deletarOficina(nome);
            if (resultado) {
                return Response.status(Response.Status.OK)
                               .entity("{ \"message\": \"Oficina deletada com sucesso\" }")
                               .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{ \"message\": \"Oficina não encontrada\" }")
                               .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{ \"message\": \"Erro: " + e.getMessage() + "\" }")
                           .build();
        }
    }
}