package com.integracao.main.controller.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integracao.main.modelo.carro.Carro;
import com.integracao.main.modelo.carro.entity.CarroEntity;
import com.integracao.main.service.carro.ServiceCarro;

@RestController
@RequestMapping("/carro")
public class ControllerCarro {	
	
	@Autowired
	private ServiceCarro serviceCarro = new ServiceCarro();
	
	@PostMapping("/inserir")
	public ResponseEntity<Carro> inserir(@RequestBody Carro carro) throws Exception {		
		return serviceCarro.inserir(carro);
	}
	
	@PostMapping("/atualizar")
	public ResponseEntity<Carro> atualizar(@RequestBody Carro carro) throws Exception {		
		return serviceCarro.atualizar(carro);
	}
	
	@GetMapping("/listar")
	public java.util.List<CarroEntity> listar(){
		return serviceCarro.listar();
	}

	@GetMapping("/listar/{codigoCarro}")
	public CarroEntity listar(@PathVariable long codigoCarro){
		return serviceCarro.listar(codigoCarro);
	}
	
	@GetMapping("/deletar/{codigoCarro}")
	public ResponseEntity<String> deletar(@PathVariable long codigoCarro){
		return serviceCarro.deletar(codigoCarro);
	}
	
	public void atualizaStatusCarro(long codigoCarro, int statusCarro){
		serviceCarro.atualizaStatus(codigoCarro, statusCarro);
	}
	
}
