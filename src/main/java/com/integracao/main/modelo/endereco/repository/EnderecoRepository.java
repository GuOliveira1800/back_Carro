package com.integracao.main.modelo.endereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.integracao.main.modelo.endereco.entity.EnderecoEntity;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity,Long>{
	
}
