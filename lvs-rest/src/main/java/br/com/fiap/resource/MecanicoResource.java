package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.Mecanico;
import br.com.fiap.service.MecanicoService;
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

@Path("/mecanicos")
public class MecanicoResource {

    private MecanicoService mecanicoService;

    public MecanicoResource() {
        try {
            this.mecanicoService = new MecanicoService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarMecanico(Mecanico mecanico) {
        try {
            if (mecanico == null || mecanico.getNome() == null || mecanico.getCnpj() == null || mecanico.getEmail() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Dados do mecânico são obrigatórios")
                               .build();
            }

            boolean isCadastrado = mecanicoService.cadastrarMecanico(mecanico);
            if (isCadastrado) {
                return Response.status(Response.Status.CREATED)
                               .entity("Mecânico cadastrado com sucesso")
                               .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Erro no cadastro do mecânico")
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
    public Response listarTodosMecanicos() {
        try {
            List<Mecanico> mecanicos = mecanicoService.selecionarTodosMecanicos();
            if (!mecanicos.isEmpty()) {
                return Response.ok(mecanicos).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT)
                               .entity("Nenhum mecânico encontrado")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao listar mecânicos: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarMecanico(@QueryParam("id") int mecanicoId) {
        try {
            Mecanico mecanico = mecanicoService.selecionarMecanico(mecanicoId);
            if (mecanico != null) {
                return Response.ok(mecanico).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Mecânico não encontrado")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao buscar mecânico: " + e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarMecanico(@QueryParam("id") int mecanicoId, Mecanico mecanico) {
        try {
            if (mecanicoId <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("ID do mecânico é obrigatório.")
                               .build();
            }

            mecanico.setMecanico_id(mecanicoId);
            String resultado = mecanicoService.atualizarMecanico(mecanico);
            if (resultado.equals("Atualizado com sucesso!")) {
                return Response.ok(resultado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Mecânico não encontrado para atualizar")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao atualizar mecânico: " + e.getMessage())
                           .build();
        }
    }

    @DELETE
    @Path("/deletar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarMecanico(@QueryParam("id") int mecanicoId) {
        try {
            boolean resultado = mecanicoService.deletarMecanico(mecanicoId);
            if (resultado) {
                return Response.status(Response.Status.OK)
                               .entity("{ \"message\": \"Mecânico deletado com sucesso\" }")
                               .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{ \"message\": \"Mecânico não encontrado\" }")
                               .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{ \"message\": \"Erro: " + e.getMessage() + "\" }")
                           .build();
        }
    }
}