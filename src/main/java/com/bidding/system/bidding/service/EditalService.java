/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.service;

import com.bidding.system.bidding.model.EditalDTO;
import com.bidding.system.bidding.model.UserDTO;
import com.bidding.system.bidding.repository.EditalDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class EditalService {
    @Autowired
    private EditalDAO service;
    
    @Autowired
    private TokenService tokenm;
    
    
     public List<EditalDTO> listar () {
        return service.lerItens();
    }
    
    public void criarEdital(EditalDTO novo, String token){
        UserDTO userlogado = tokenm.extrairClaim(token);
        
        String mensagem = "";
        
        if(userlogado.getRole().equals("COMPRADOR")){
            if(novo.getTitulo().equals("")){
            mensagem += "Titulo não preenchido";
        }
        if (novo.getDescricao().equals("")){
            mensagem += "Descrição não preenchida:";
        }
        if (novo.getData_fechamento() == null) {
            mensagem += "Data não preenchida";
        }
        
        if(!mensagem.equals("")){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
        }
        novo.setStatus("ABERTO");
        int linhas = service.criar(novo);
        if (linhas == 0){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Erro ao cadastrar ao banco de dados");
        }
        }
        else{
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Acesso não autorizado");
        }
        
       
    }
    
}
