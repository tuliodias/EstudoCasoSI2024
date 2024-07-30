/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudocasosi.modelo.dao;

import com.mycompany.estudocasosi.modelo.entidade.Cidade;
import com.mycompany.estudocasosi.modelo.entidade.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tulio
 */
public class FuncionarioDao extends GenericoDAO<Funcionario> {
     
    public void salvar(Funcionario c){
        String insert = "INSERT INTO FUNCIONARIO(NOME,SALARIO,NASCIMENTO,CIDADE) VALUES (?,?,?,?)";
        save(insert, c.getNomeFuncionario(), c.getSalarioFuncionario(), c.getNascimentoFuncionario(),c.getCidadeFuncionario().getCodigoCidade());
    }
    
    public void alterar(Funcionario c){
        String update = "UPDATE FUNCIONARIO SET NOME=?,SALARIO=?,NASCIMENTO=?,CIDADE=? WHERE CODIGO=?";
        save(update,c.getNomeFuncionario(),  c.getSalarioFuncionario(), c.getNascimentoFuncionario(),c.getCidadeFuncionario().getCodigoCidade(),c.getCodigoFuncionario());
    }
    
    public void excluir(Funcionario c){
        String delete="DELETE FROM FUNCIONARIO WHERE CODIGO=?";
        save(delete, c.getCodigoFuncionario());
    }
    
    public Cidade buscarPorId(int id){
        String select = "SELECT * FROM CIDADE WHERE CODIGO=?";
        return buscarPorId(select, new CidadeRowMapper(), id);
    }
    
    public List<Cidade> buscarTodas(){
         String select = "SELECT * FROM CIDADE";
        return buscarTodos(select, new CidadeRowMapper());
    }
    
    public static class CidadeRowMapper implements RowMapper<Cidade>{

        @Override
        public Cidade mapRow(ResultSet rs) throws SQLException {
            Cidade cidade = new Cidade();
            cidade.setCodigoCidade(rs.getInt("CODIGO"));
            cidade.setNomeCidade(rs.getString("NOME"));
            cidade.setUfCidade(rs.getString("UF"));
            return cidade;
        }
        
    }
    
}
