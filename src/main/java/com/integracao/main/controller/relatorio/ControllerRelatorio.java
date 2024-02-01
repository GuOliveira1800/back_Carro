package com.integracao.main.controller.relatorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integracao.main.modelo.relatorio.Relatorio;
import com.integracao.main.service.relatorio.ServiceRelatorio;

@RestController
@RequestMapping("/relatorio")
public class ControllerRelatorio {
	
	@Autowired
	ServiceRelatorio serviceRelatorio = new ServiceRelatorio();
	
	@GetMapping("/gerar/{codigoPedido}")
	public ResponseEntity<Relatorio> deletar(@PathVariable Long codigoPedido) throws Exception {
		return serviceRelatorio.gerar(codigoPedido);
	}
	
}
