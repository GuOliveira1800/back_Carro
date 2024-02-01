package com.integracao.main.modelo.pedido.entity;

import java.util.Calendar;

import com.integracao.main.modelo.carro.entity.CarroEntity;
import com.integracao.main.modelo.cliente.entity.ClienteEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity(name = "pedido")
@Table(name="pedido",schema = "api")
public class PedidoEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_ped" , unique = true)
	private long codigo_ped;
	
	@Column(name="status_ped")
	private int status_ped;
	
	@Column(name="datcon_ped")
	@Temporal(TemporalType.DATE) 
	private Calendar datcon_ped;
	
	@Column(name="datdev_ped")
	@Temporal(TemporalType.DATE) 
	private Calendar datdev_ped;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codcar_ped", referencedColumnName = "codigo_car")
	private CarroEntity codcar_ped;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codcli_ped", referencedColumnName = "codigo_cli")
	private ClienteEntity codcli_ped;
}
