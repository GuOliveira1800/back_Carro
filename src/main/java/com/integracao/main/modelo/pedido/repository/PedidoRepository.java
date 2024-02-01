package com.integracao.main.modelo.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integracao.main.modelo.pedido.entity.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity,Long>{

}
