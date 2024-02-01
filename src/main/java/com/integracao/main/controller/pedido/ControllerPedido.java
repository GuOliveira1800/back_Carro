package com.integracao.main.controller.pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integracao.main.modelo.pedido.Pedido;
import com.integracao.main.modelo.pedido.entity.PedidoEntity;
import com.integracao.main.service.pedido.ServicePedido;

@RestController
@RequestMapping("/pedido")
public class ControllerPedido {
		
	@Autowired
	private ServicePedido servicePedido = new ServicePedido();
	
	@PostMapping("/inserir")
	public ResponseEntity<String> inserir(@RequestBody Pedido pedido) throws Exception {
		return servicePedido.inserir(pedido);
	}
	
	@PostMapping("/atualizar")
	public ResponseEntity<String> atualizar(@RequestBody Pedido pedido) throws Exception {
		return servicePedido.atualizar(pedido);
	}
	
	@GetMapping("/deletar/{codigoPedido}")
	public ResponseEntity<String> deletar(@PathVariable int codigoPedido) throws Exception {
		return servicePedido.deletar(codigoPedido);
	}
	
	@GetMapping("/listar")
	public List<PedidoEntity> listar(){
		return servicePedido.listar();		
	}
	
	@GetMapping("/listar/{codigoPedido}")
	public PedidoEntity listar(@PathVariable long codigoPedido){
		return servicePedido.listar(codigoPedido);		
	}
	
}
