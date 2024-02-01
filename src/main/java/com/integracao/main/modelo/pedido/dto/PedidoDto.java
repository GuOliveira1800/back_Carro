package com.integracao.main.modelo.pedido.dto;

import com.integracao.main.modelo.carro.dto.CarroDto;
import com.integracao.main.modelo.cliente.dto.ClienteDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class PedidoDto {
	
	private Long codigo_ped;
	private int status_ped;
	private String datcon_ped; 
	private String datdev_ped;
	private CarroDto codcar_ped;
	private ClienteDto codcli_ped;	
}
