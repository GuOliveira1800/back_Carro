package com.integracao.main.modelo.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integracao.main.modelo.cliente.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Long>{

}
