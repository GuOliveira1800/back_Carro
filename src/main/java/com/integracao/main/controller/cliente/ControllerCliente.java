package com.integracao.main.controller.cliente;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integracao.main.funcao.funcao;
import com.integracao.main.modelo.cliente.Cliente;
import com.integracao.main.modelo.cliente.entity.ClienteEntity;
import com.integracao.main.modelo.cliente.repository.ClienteRepository;
import com.integracao.main.modelo.endereco.entity.EnderecoEntity;
import com.integracao.main.modelo.pedido.entity.PedidoEntity;
import com.integracao.main.service.cliente.ServiceCliente;

@RestController
@RequestMapping("/cliente")
public class ControllerCliente {

	@Autowired
	private  ServiceCliente serviceCliente = new ServiceCliente();
	
	@PostMapping("/inserir")
	public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) throws Exception {
		return serviceCliente.inserir(cliente);
	}
	
	@PostMapping("/atualizar")
	public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente) throws Exception {
		return serviceCliente.atualizar(cliente);
	}
	
	@GetMapping("/deletar/{codigoCliente}")
	public ResponseEntity<Cliente> deletar(@PathVariable int codigoCliente) throws Exception {
		return serviceCliente.deletar(codigoCliente);
	}
	
	@GetMapping("/listar")
	public List<ClienteEntity> listar(){
		return serviceCliente.listar();		
	}
	
	@GetMapping("/listar/{codigoCliente}")
	public ClienteEntity listar(@PathVariable long codigoCliente){		
		return serviceCliente.listar(codigoCliente);
	}
	
}
