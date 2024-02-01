package com.integracao.main.service.cliente;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.integracao.main.funcao.funcao;
import com.integracao.main.modelo.cliente.Cliente;
import com.integracao.main.modelo.cliente.entity.ClienteEntity;
import com.integracao.main.modelo.cliente.repository.ClienteRepository;
import com.integracao.main.modelo.endereco.entity.EnderecoEntity;

@Service
public class ServiceCliente {

	@Autowired
	ClienteRepository clienteRepository;
	
	public ResponseEntity<Cliente> inserir(Cliente cliente) throws Exception {
		
		ClienteEntity clienteEntity = new ClienteEntity();
		EnderecoEntity enderecoEntity = new EnderecoEntity();
		
		JSONObject jsonCep = funcao.getInstance().buscaCep(cliente.getCodend_cli().getCep_end());
		
		if (jsonCep != null) {
			enderecoEntity.setBairro_end(jsonCep.getString("bairro"));
			enderecoEntity.setCidade_end(jsonCep.getString("localidade"));
			enderecoEntity.setRua_end(jsonCep.getString("logradouro"));
			enderecoEntity.setUf_end(jsonCep.getString("uf"));
		}		
		enderecoEntity.setNumero_end(cliente.getCodend_cli().getNumero_end());
		enderecoEntity.setCep_end(cliente.getCodend_cli().getCep_end());
		
		clienteEntity.setCodend_cli(enderecoEntity);		
		clienteEntity.setDocume_cli(cliente.getDocume_cli());
		clienteEntity.setNome_cli(cliente.getNome_cli());
		clienteEntity.setTelefo_cli(cliente.getTelefo_cli());
		clienteEntity.setVerifi_cli(0);
		
		clienteRepository.save(clienteEntity);
		
		return new ResponseEntity<Cliente>(HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<Cliente> atualizar(Cliente cliente) throws Exception {
		
		ClienteEntity clienteEntity = new ClienteEntity();
		EnderecoEntity enderecoEntity = new EnderecoEntity();
		
		enderecoEntity.setCodigo_end(cliente.getCodend_cli().getCodigo_end());		
		enderecoEntity.setCodigo_end(cliente.getCodend_cli().getCodigo_end());
		enderecoEntity.setBairro_end(cliente.getCodend_cli().getBairro_end());
		enderecoEntity.setCidade_end(cliente.getCodend_cli().getCidade_end());
		enderecoEntity.setRua_end(cliente.getCodend_cli().getRua_end());
		enderecoEntity.setUf_end(cliente.getCodend_cli().getUf_end());				
		enderecoEntity.setNumero_end(cliente.getCodend_cli().getNumero_end());
		enderecoEntity.setCep_end(cliente.getCodend_cli().getCep_end());
		
		clienteEntity.setCodigo_cli(cliente.getCodigo_cli());
		clienteEntity.setCodend_cli(enderecoEntity);
		clienteEntity.setDocume_cli(cliente.getDocume_cli());
		clienteEntity.setNome_cli(cliente.getNome_cli());
		clienteEntity.setTelefo_cli(cliente.getTelefo_cli());
		clienteEntity.setVerifi_cli(cliente.getVerifi_cli());
		
		clienteRepository.save(clienteEntity);
		
		return new ResponseEntity<Cliente>(HttpStatus.ACCEPTED);
	}
	
	public boolean validaClienteVerificado(Long codigoCliente) {
		
		Optional<ClienteEntity> oClienteEntity = clienteRepository.findById(codigoCliente);
		
		ClienteEntity clienteEntity = oClienteEntity.get();
		
		if(clienteEntity.getVerifi_cli() == 0) {
			return true;
		}
		
		return false;
	}
	
	public java.util.List<ClienteEntity> listar(){		
		return clienteRepository.findAll();
	}
	
	public ClienteEntity listar(long codigoCliente){		
		Optional<ClienteEntity> opCliente = this.clienteRepository.findById(codigoCliente);
		
		if (opCliente.isEmpty()) {
			return null;
		}else {
			return opCliente.get();
		}
	}
	
	public ResponseEntity<Cliente> deletar(long codigoCliente){
		this.clienteRepository.deleteById(codigoCliente);		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
}
