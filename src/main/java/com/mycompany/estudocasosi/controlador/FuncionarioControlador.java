/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudocasosi.controlador;

import com.mycompany.estudocasosi.modelo.dao.CidadeDao;
import com.mycompany.estudocasosi.modelo.entidade.Cidade;
import com.mycompany.estudocasosi.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author tulio
 */
@WebServlet(WebConstantes.BASE_PATH + "/FuncionarioControlador")
public class FuncionarioControlador extends HttpServlet {

    private CidadeDao cidadeDao;
    private Cidade cidade;
    String codigoCidade = "";
    String nomeCidade = "";
    String ufCidade = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        cidadeDao = new CidadeDao();
        cidade = new Cidade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            codigoCidade = request.getParameter("codigoCidade");
            nomeCidade = request.getParameter("nomeCidade");
            ufCidade = request.getParameter("ufCidade");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }
            switch (opcao) {
                case "cadastrar":  cadastrar(request, response); break;
                case "editar":  editar(request, response); break;
                case "confirmarEditar":  confirmarEditar(request, response); break;
                case "excluir":  excluir(request, response); break;
                case "confirmarExcluir":  confirmarExcluir(request, response); break;
                case "cancelar":  cancelar(request, response); break;
                default:
                    throw new IllegalArgumentException("Opção inválida"+opcao);
            }
          

        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são numeros válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        cidade.setNomeCidade(nomeCidade);
        cidade.setUfCidade(ufCidade);
        cidadeDao.salvar(cidade);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoCidade", codigoCidade);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nomeCidade", nomeCidade);
        request.setAttribute("ufCidade", ufCidade);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoCidade", codigoCidade);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nomeCidade", nomeCidade);
        request.setAttribute("ufCidade", ufCidade);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        cidade.setCodigoCidade(Integer.valueOf(codigoCidade));
        cidade.setNomeCidade(nomeCidade);
        cidade.setUfCidade(ufCidade);
        cidadeDao.alterar(cidade);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cidade.setCodigoCidade(Integer.valueOf(codigoCidade));
        cidade.setNomeCidade(nomeCidade);
        cidade.setUfCidade(ufCidade);
        cidadeDao.excluir(cidade);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoCidade", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nomeCidade", "");
        request.setAttribute("ufCidade", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cidade> cidades = cidadeDao.buscarTodas();
        request.setAttribute("cidades", cidades);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCidade.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(nomeCidade==null || nomeCidade.isEmpty()|| ufCidade==null || ufCidade.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }

}
