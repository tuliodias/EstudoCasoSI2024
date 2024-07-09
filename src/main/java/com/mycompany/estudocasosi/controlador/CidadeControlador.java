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
@WebServlet(WebConstantes.BASE_PATH + "/CidadeControlador")
public class CidadeControlador extends HttpServlet {
    
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
            cadastrar(request,response);
            
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são numeros válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }
    
    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cidade.setNomeCidade(nomeCidade);
        cidade.setUfCidade(ufCidade);
        cidadeDao.salvar(cidade);
        encaminharParaPagina(request, response);
    }
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cidade> cidades = cidadeDao.buscarTodas();
        request.setAttribute("cidades", cidades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCidade.jsp");
        dispatcher.forward(request, response);
        
    }
    
}
