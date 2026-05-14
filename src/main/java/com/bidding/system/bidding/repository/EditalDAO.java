/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.repository;

import com.bidding.system.bidding.model.EditalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class EditalDAO {
    
    public int criar(EditalDTO novo){
        try{
          Connection conn = Conexao.conectar();
          PreparedStatement stmt = null; 
          
          stmt = conn.prepareStatement("Insert into editais (titulo, descricao, data_fechamento, status) VALUES (?, ?, ?, ? )");
          
          stmt.setString(1, novo.getTitulo());
          stmt.setString(2, novo.getDescricao());
          stmt.setDate(3, novo.getData_fechamento());
          stmt.setString(4, novo.getStatus());
          
          int linhaAfetadas = stmt.executeUpdate();
            if (linhaAfetadas ==0){
                throw new SQLException("Falha na atualização - Nenhuma linha foi afetada");
            }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    public List<EditalDTO> lerItens() {
        List<EditalDTO> lista = new ArrayList();

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM edital");
            
           
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EditalDTO edital = new EditalDTO();
                edital.setId(rs.getLong("id"));
                edital.setTitulo(rs.getString("titulo"));
                edital.setDescricao(rs.getString("descricao"));
                edital.setData_fechamento(rs.getDate("data_fechamento"));
                edital.setStatus(rs.getString("status"));

                lista.add(edital);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }}
