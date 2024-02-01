package com.integracao.main.modelo.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
	
	private int codigo_ped;
	private int status_ped;
	private String datcon_ped; 
	private String datdev_ped;
	private int codcar_ped;
	private int codcli_ped;
}
