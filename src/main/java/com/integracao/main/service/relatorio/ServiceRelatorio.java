package com.integracao.main.service.relatorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.integracao.main.funcao.funcao;
import com.integracao.main.modelo.pedido.dto.PedidoDto;
import com.integracao.main.modelo.relatorio.Relatorio;
import com.integracao.main.service.carro.ServiceCarro;
import com.integracao.main.service.pedido.ServicePedido;

@Service
public class ServiceRelatorio {

	@Autowired
	ServicePedido servicePedido = new ServicePedido();
	
	@Autowired
	ServiceCarro serviceCarro = new ServiceCarro();
	
	public ResponseEntity<Relatorio> gerar(Long codigoPedido) {		
		PedidoDto pedidoDto = this.servicePedido.RetornaRelatorioPedido(codigoPedido);
		
		if (pedidoDto.getCodcar_ped().getStatus_car() != 1)
			serviceCarro.atualizaStatus(pedidoDto.getCodcar_ped().getCodigo_car(),1);		
		
		if (pedidoDto.getStatus_ped() != 3)
			servicePedido.atualizaStatus(pedidoDto.getCodigo_ped(), 3);		
		
		Double valorTotal = serviceCarro.calcularValor(funcao.getInstance().calculaDiferencaDias(pedidoDto.getDatcon_ped(), pedidoDto.getDatdev_ped()),
													   pedidoDto.getCodcar_ped().getCodigo_car());
		
		String DescricaoCarro = "Carro modelo "+pedidoDto.getCodcar_ped().getModelo_car()+" da marca "+pedidoDto.getCodcar_ped().getMarca_car()+" do ano "+
								pedidoDto.getCodcar_ped().getAno_car();		
				
		return new ResponseEntity<Relatorio>(new Relatorio(DescricaoCarro,pedidoDto.getCodcli_ped().getNome_cli(),pedidoDto.getDatcon_ped(),pedidoDto.getDatdev_ped(),valorTotal),
											 HttpStatus.ACCEPTED);
	}
}
