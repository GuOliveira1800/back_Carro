package com.integracao.main.service.carro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.integracao.main.modelo.carro.Carro;
import com.integracao.main.modelo.carro.entity.CarroEntity;
import com.integracao.main.modelo.carro.repository.CarroRepository;

@Service
public class ServiceCarro {
	
	@Autowired
	CarroRepository carroRepository; 
	
	public ResponseEntity<Carro> inserir(Carro carro) throws Exception {
		
		CarroEntity carroEntity = new CarroEntity();

		carroEntity.setAno_car(carro.getAno_car());
		carroEntity.setCategoria_car(carro.getCategoria_car());
		carroEntity.setMarca_car(carro.getMarca_car());
		carroEntity.setModelo_car(carro.getModelo_car());
		carroEntity.setStatus_car(carro.getStatus_car());
		carroEntity.setPreco_car(carro.getPreco_car());
		
		carroRepository.save(carroEntity);
		
		return new ResponseEntity<Carro>(HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<Carro> atualizar(Carro carro) throws Exception {
		
		CarroEntity carroEntity = new CarroEntity();

		carroEntity.setCodigo_car((long) carro.getCodigo_car());
		carroEntity.setAno_car(carro.getAno_car());
		carroEntity.setCategoria_car(carro.getCategoria_car());
		carroEntity.setMarca_car(carro.getMarca_car());
		carroEntity.setModelo_car(carro.getModelo_car());
		carroEntity.setStatus_car(carro.getStatus_car());
		carroEntity.setPreco_car(carro.getPreco_car());
		
		carroRepository.save(carroEntity);
		
		return new ResponseEntity<Carro>(HttpStatus.ACCEPTED);
	}
	
	public java.util.List<CarroEntity> listar(){		
		return carroRepository.findAll();
	}
	
	public CarroEntity listar(long codigoCarro){		
		Optional<CarroEntity> opCarro = this.carroRepository.findById(codigoCarro);
		
		if (opCarro.isEmpty()) {
			return null;
		}else {
			return opCarro.get();
		}
	}
	
	public ResponseEntity<String> deletar(long codigoCarro) {
		this.carroRepository.deleteById((long) codigoCarro);		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	public void atualizaStatus(long codigoCarro, int Status) {
		Optional<CarroEntity> oCarroEntity = carroRepository.findById(codigoCarro);
		
		CarroEntity carroEntity = oCarroEntity.get();
		
		carroEntity.setStatus_car(Status);
		
		carroRepository.save(carroEntity);		
	}
	
	public boolean validaStatus(long codigoCarro) {
		System.out.println(codigoCarro);
		Optional<CarroEntity> oCarroEntity = carroRepository.findById(codigoCarro);
		
		CarroEntity carroEntity = oCarroEntity.get();
		
		return carroEntity.getStatus_car() != 1;
	}
	
	public double calcularValor (int diasContados, Long codigoCarro) {
		Optional<CarroEntity> oCarroEntity = carroRepository.findById(codigoCarro);
		
		CarroEntity carroEntity = oCarroEntity.get();
		
		Double valor = 0.00;
		
		valor = (50.00 * carroEntity.getCategoria_car());
	 
		valor = valor *	diasContados; 
		
		return valor;
	}
	
}
