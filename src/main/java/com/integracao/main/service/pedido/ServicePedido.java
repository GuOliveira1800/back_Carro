package com.integracao.main.service.pedido;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.integracao.main.funcao.funcao;
import com.integracao.main.modelo.carro.dto.CarroDto;
import com.integracao.main.modelo.carro.entity.CarroEntity;
import com.integracao.main.modelo.cliente.dto.ClienteDto;
import com.integracao.main.modelo.cliente.entity.ClienteEntity;
import com.integracao.main.modelo.endereco.dto.EnderecoDto;
import com.integracao.main.modelo.pedido.Pedido;
import com.integracao.main.modelo.pedido.dto.PedidoDto;
import com.integracao.main.modelo.pedido.entity.PedidoEntity;
import com.integracao.main.modelo.pedido.repository.PedidoRepository;
import com.integracao.main.service.carro.ServiceCarro;
import com.integracao.main.service.cliente.ServiceCliente;

@Service
public class ServicePedido {
	
	@Autowired
	private PedidoRepository pedidoRepository;	
	
	@Autowired
	private ServiceCarro serviceCarro = new ServiceCarro();
	
	@Autowired
	private ServiceCliente serviceCliente = new ServiceCliente();
	
	public PedidoDto RetornaRelatorioPedido(Long codigoPedido) {
		
		// TODO Auto-generated method stub
		Optional<PedidoEntity> opPedido = this.pedidoRepository.findById(codigoPedido);
		
		if (opPedido.isEmpty()) {
			return null;
		}else {
			PedidoEntity pedidoEntity =  opPedido.get();
			
			PedidoDto pedidoDto = new PedidoDto(pedidoEntity.getCodigo_ped(),pedidoEntity.getStatus_ped(),
					funcao.getInstance().formataData(pedidoEntity.getDatcon_ped().getTime(),"yyyy/MM/dd"),
					funcao.getInstance().formataData(pedidoEntity.getDatdev_ped().getTime(),"yyyy/MM/dd"), 
					new CarroDto(pedidoEntity.getCodcar_ped().getCodigo_car(),pedidoEntity.getCodcar_ped().getMarca_car(),pedidoEntity.getCodcar_ped().getModelo_car(),
							pedidoEntity.getCodcar_ped().getAno_car(),pedidoEntity.getCodcar_ped().getStatus_car(),pedidoEntity.getCodcar_ped().getCategoria_car(),pedidoEntity.getCodcar_ped().getPreco_car()), 
					new ClienteDto(pedidoEntity.getCodcli_ped().getCodigo_cli(),pedidoEntity.getCodcli_ped().getVerifi_cli(),pedidoEntity.getCodcli_ped().getNome_cli(),
							pedidoEntity.getCodcli_ped().getDocume_cli(),pedidoEntity.getCodcli_ped().getTelefo_cli(), new EnderecoDto()));
			
			return pedidoDto;
		}
	}
	
	public ResponseEntity<String> inserir(Pedido pedido) throws Exception {
		
		CarroEntity carroEntity = this.serviceCarro.listar(pedido.getCodcar_ped());
		ClienteEntity clienteEntity = this.serviceCliente.listar(pedido.getCodcli_ped());
		
		ArrayList<String> erros = this.validaPedido(pedido,carroEntity,clienteEntity);
		
		if (erros.size() > 0) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			Gson gson = gsonBuilder.create();
			System.out.println(gson.toJson(erros));
			
			return new ResponseEntity<String>(gson.toJson(erros),HttpStatus.BAD_REQUEST);
		}
		
		PedidoEntity pedidoEntity = new PedidoEntity();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
		Calendar dataContratacao = Calendar.getInstance();
		Calendar dataDevolucao = Calendar.getInstance();
		
		dataContratacao.setTime(simpleDateFormat.parse(pedido.getDatcon_ped()));
		dataDevolucao.setTime(simpleDateFormat.parse(pedido.getDatdev_ped()));	
		
		pedidoEntity.setDatcon_ped(dataContratacao);
		pedidoEntity.setDatdev_ped(dataDevolucao);		
		pedidoEntity.setCodcar_ped(carroEntity);
		pedidoEntity.setCodcli_ped(clienteEntity);		
		pedidoEntity.setStatus_ped(1);
		
		
		pedidoRepository.save(pedidoEntity);
		
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	public ArrayList<String> validaPedido (Pedido pedido, CarroEntity carroEntity, ClienteEntity clienteEntity) {
		
		boolean isValidoDataContratacao = funcao.getInstance().validaDataContratacao(pedido.getDatcon_ped());
		
		int diferencaDias = funcao.getInstance().calculaDiferencaDias(pedido.getDatcon_ped(),pedido.getDatdev_ped());
		
		int diasAntecedencia = funcao.getInstance().calculaDiferencaDias(pedido.getDatcon_ped());
		
		ArrayList<String> erros = new ArrayList<>();
		
		if(isValidoDataContratacao)
			erros.add("Data de contratação menor que a data atual!");
		
		if(diferencaDias > 7)
			erros.add("Periodo de contratação maior que sete dias");
		
		if(diasAntecedencia > 7)
			erros.add("Data de contratação com mais de 7 dias de antecedência");
		
		if(serviceCarro.validaStatus(pedido.getCodcar_ped()))
			erros.add("Carro com status inválido!");
		
		if(serviceCliente.validaClienteVerificado((long) pedido.getCodcli_ped()))
			erros.add("Cliente não verificado!");
			
		if (carroEntity == null) {
			erros.add("Carro não encontrado!");
		}
		
		if (clienteEntity == null) {
			erros.add("Cliente não encontrado!");
		}
		
		return erros;
	}
	
	public ArrayList<String> validaPedido (Pedido pedido, CarroEntity carroEntity, ClienteEntity clienteEntity, boolean isAtualizar) {
		
		boolean isValidoDataContratacao = funcao.getInstance().validaDataContratacao(pedido.getDatcon_ped());
		
		int diferencaDias = funcao.getInstance().calculaDiferencaDias(pedido.getDatcon_ped(),pedido.getDatdev_ped());
		
		int diasAntecedencia = funcao.getInstance().calculaDiferencaDias(pedido.getDatcon_ped());
		
		ArrayList<String> erros = new ArrayList<>();
		
		if(isValidoDataContratacao)
			erros.add("Data de contratação menor que a data atual!");
		
		if(diferencaDias > 7)
			erros.add("Periodo de contratação maior que sete dias");
	
		if(diasAntecedencia > 7)
			erros.add("Data de contratação com mais de 7 dias de antecedência");
		
		if(serviceCliente.validaClienteVerificado((long) pedido.getCodcli_ped()))
			erros.add("Cliente não verificado!");
			
		if (carroEntity == null) 
			erros.add("Carro não encontrado!");
		
		
		if (clienteEntity == null)
			erros.add("Cliente não encontrado!");
		
		
		return erros;
	}
	
	public ResponseEntity<String> atualizar(Pedido pedido) throws Exception{
		
		CarroEntity carroEntity = this.serviceCarro.listar(pedido.getCodcar_ped());
		ClienteEntity clienteEntity = this.serviceCliente.listar(pedido.getCodcli_ped());
		
		ArrayList<String> erros = this.validaPedido(pedido,carroEntity,clienteEntity,true);
		
		if (erros.size() > 0) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			Gson gson = gsonBuilder.create();
			System.out.println(gson.toJson(erros));
			
			return new ResponseEntity<String>(gson.toJson(erros),HttpStatus.BAD_REQUEST);
		}
		
		PedidoEntity pedidoEntity = new PedidoEntity();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
		Calendar dataContratacao = Calendar.getInstance();
		Calendar dataDevolucao = Calendar.getInstance();
		
		dataContratacao.setTime(simpleDateFormat.parse(pedido.getDatcon_ped()));
		dataDevolucao.setTime(simpleDateFormat.parse(pedido.getDatdev_ped()));
		
		pedidoEntity.setDatcon_ped(dataContratacao);
		pedidoEntity.setDatdev_ped(dataDevolucao);	
		pedidoEntity.setCodcar_ped(carroEntity);
		pedidoEntity.setCodcli_ped(clienteEntity);
		pedidoEntity.setCodigo_ped(pedido.getCodigo_ped());		
		pedidoEntity.setStatus_ped(pedido.getStatus_ped());
		
		this.pedidoRepository.save(pedidoEntity);
		
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	public ResponseEntity<String> deletar(int codigoPedido) {
		this.pedidoRepository.deleteById((long) codigoPedido);		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	public List<PedidoEntity> listar() {
		// TODO Auto-generated method stub
		return this.pedidoRepository.findAll();
	}
	
	public PedidoEntity listar(long codigoPedido) {
		// TODO Auto-generated method stub
		Optional<PedidoEntity> opPedido = this.pedidoRepository.findById(codigoPedido);
		
		if (opPedido.isEmpty()) {
			return null;
		}else {
			return opPedido.get();
		}
	}
	
	public void atualizaStatus(long codigoPedido, int Status) {
		Optional<PedidoEntity> oPedidoEntity = pedidoRepository.findById(codigoPedido);
		
		PedidoEntity pedidoEntity = oPedidoEntity.get();
		
		pedidoEntity.setStatus_ped(Status);
		
		pedidoRepository.save(pedidoEntity);		
	}
	
}
