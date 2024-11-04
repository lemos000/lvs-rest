package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.Servico;
import br.com.fiap.service.ServicoService;
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

@Path("/servicos")
public class ServicoResource {

    private ServicoService servicoService;

    public ServicoResource() {
        try {
            this.servicoService = new ServicoService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarServico(Servico servico) {
        try {
            if (servico == null || servico.getDescricao() == null || servico.getTipoVeiculo() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Dados do serviço são obrigatórios")
                               .build();
            }

            boolean isCadastrado = servicoService.cadastrarServico(servico);
            if (isCadastrado) {
                return Response.status(Response.Status.CREATED)
                               .entity("Serviço cadastrado com sucesso")
                               .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Erro no cadastro do serviço")
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
    public Response listarTodosServicos() {
        try {
            List<Servico> servicos = servicoService.selecionarTodosServicos();
            if (!servicos.isEmpty()) {
                return Response.ok(servicos).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT)
                               .entity("Nenhum serviço encontrado")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao listar serviços: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarServico(@QueryParam("id") int servicoId) {
        try {
            Servico servico = servicoService.selecionarServico(servicoId);
            if (servico != null) {
                return Response.ok(servico).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Serviço não encontrado")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao buscar serviço: " + e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarServico(@QueryParam("id") int servicoId, Servico servico) {
        try {
            if (servicoId <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("ID do serviço é obrigatório.")
                               .build();
            }

            servico.setServicoId(servicoId);
            String resultado = servicoService.atualizarServico(servico);
            if (resultado.equals("Atualizado com sucesso!")) {
                return Response.ok(resultado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Serviço não encontrado para atualizar")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao atualizar serviço: " + e.getMessage())
                           .build();
        }
    }

    @DELETE
    @Path("/deletar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarServico(@QueryParam("id") int servicoId) {
        try {
            boolean resultado = servicoService.deletarServico(servicoId);
            if (resultado) {
                return Response.status(Response.Status.OK)
                               .entity("{ \"message\": \"Serviço deletado com sucesso\" }")
                               .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{ \"message\": \"Serviço não encontrado\" }")
                               .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{ \"message\": \"Erro: " + e.getMessage() + "\" }")
                           .build();
        }
    }
}