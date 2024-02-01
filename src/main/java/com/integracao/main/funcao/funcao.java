package com.integracao.main.funcao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

public class funcao {
	
	public static funcao INSTANCE = new funcao();

	public static funcao getInstance() {
		if (INSTANCE == null) {
			setInstance(new funcao());
		}

		return INSTANCE;
	}
	
	public static void setInstance(funcao instance) {
		INSTANCE = instance;
	}
		
	 public String getHttpGET(String urlToRead) {
	        StringBuilder result = new StringBuilder();

	        try {
	            URL url = new URL(urlToRead);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");

	            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = rd.readLine()) != null) {
	                result.append(line);
	            }
	            
	        } catch (MalformedURLException | ProtocolException ex) {
	            // verifica os Eventos
	            
	        } catch (IOException ex) {
	            // verifica os Eventos
	            
	        }
	        
	        return result.toString();
	 }
	 
	 public int calculaDiferencaDias(String dataInicio, String DataFim) {

		 LocalDate dataContratacao = LocalDate.of(Integer.parseInt(dataInicio.substring(0,4)) , 
				 Integer.parseInt(dataInicio.substring(5,7)) ,
				 Integer.parseInt(dataInicio.substring(8,10)));

		LocalDate dataDevolucao = LocalDate.of(Integer.parseInt(DataFim.substring(0,4)) , 
				 Integer.parseInt(DataFim.substring(5,7)) ,
				 Integer.parseInt(DataFim.substring(8,10)));
		 
		 return (int) dataContratacao.until(dataDevolucao, ChronoUnit.DAYS);
	 }
	 
	 public int calculaDiferencaDias(String data) {
		 
		 LocalDate dataContratacao = LocalDate.of(Integer.parseInt(data.substring(0,4)) , 
				 Integer.parseInt(data.substring(5,7)) ,
				 Integer.parseInt(data.substring(8,10)));
		 
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
		 
		 Calendar calendarDataContratacao = Calendar.getInstance();
		 
		try {
			calendarDataContratacao.setTime(simpleDateFormat.parse(data.substring(0,4)+"/"+data.substring(5,7)+"/"+data.substring(8,10)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		if (calendarDataContratacao.after(Calendar.getInstance()) || calendarDataContratacao.equals(Calendar.getInstance())) {
			return 0;
		}
			
		 
		return (int) LocalDate.now().until(dataContratacao,ChronoUnit.DAYS);
	 }
	 
	 public boolean validaDataContratacao(String data) {
		  
		Calendar calendarDataContratacao = Calendar.getInstance();
		Calendar calendarDataAtual = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
		
		try {
			calendarDataContratacao.setTime(simpleDateFormat.parse(data.substring(0,4)+"/"+data.substring(5,7)+"/"+data.substring(8,10)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		calendarDataAtual.set(Calendar.HOUR_OF_DAY, 0);
		calendarDataAtual.set(Calendar.MINUTE, 0);
		calendarDataAtual.set(Calendar.SECOND, 0);
		calendarDataAtual.set(Calendar.MILLISECOND, 0);
		
		calendarDataContratacao.set(Calendar.HOUR_OF_DAY, 0);
		calendarDataContratacao.set(Calendar.MINUTE, 0);
		calendarDataContratacao.set(Calendar.SECOND, 0);
		calendarDataContratacao.set(Calendar.MILLISECOND, 0);
		
		if (calendarDataContratacao.before(calendarDataAtual))
			return true;
		 
		return false;
	 }
	 	 
	 public JSONObject buscaCep (String cep) {
			
		if(cep.isEmpty() || cep == null) {
			return null;
		}
		
		String url = "http://viacep.com.br/ws/" + cep + "/json/";

		String retorno = funcao.getInstance().getHttpGET(url);
		
		if(!retorno.isEmpty() && retorno != null) {		
	        JSONObject jsonCep = new JSONObject(retorno);
	        
	        return jsonCep;
		}else {
			return null;
		}
		
	}
	 
	public String formataData(Date dataFormatar) {
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		
		return dateFormat.format(dataFormatar);
	}
	
	public String formataData(Date dataFormatar, String formato) {
		DateFormat dateFormat = new SimpleDateFormat(formato);
		
		return dateFormat.format(dataFormatar);
	}
	
}
