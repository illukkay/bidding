/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.service;

import com.bidding.system.bidding.model.UserDTO;
import com.bidding.system.bidding.model.UserRequestDTO;
import com.bidding.system.bidding.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    
    @Autowired
    private UserDAO service;
    
    public void register (UserDTO user){
        String mensagem = "";
       if (user.getNome().equals("")){
           mensagem = "Nome nao preenchido";
       }else if(user.getEmail().equals("")){  
           mensagem = "email nao preenchido";
       }else if(user.getSenha().equals("")){
           mensagem = "senha nao preenchida";
       }else if(user.getRole().equals("")){
           user.setRole("FORNECEDOR");
       }
       
       if(mensagem.equals("")){
           throw new ResponseStatusException(HttpStatusCode.valueOf(400));
       }
        service.register(user);
    }
    
    
    public UserDTO Logar(UserRequestDTO user){
        String mensagem = "";
     if(user.getEmail().equals("")){
         mensagem = "email nao preenchido";
     }else if (user.getSenha().equals("")){
         mensagem = "senha nao preenchida";
     }
    
     if(mensagem.equals("")){
           throw new ResponseStatusException(HttpStatusCode.valueOf(400));
       }
     return service.logar(user.getEmail(), user.getSenha());
        
        
    }
     
    
    }
    

