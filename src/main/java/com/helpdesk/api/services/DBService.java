package com.helpdesk.api.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.Cliente;
import com.helpdesk.api.model.Tecnico;
import com.helpdesk.api.model.enumerator.Perfil;
import com.helpdesk.api.model.enumerator.Prioridade;
import com.helpdesk.api.model.enumerator.Status;
import com.helpdesk.api.repository.ChamadoRepository;
import com.helpdesk.api.repository.ClienteRepository;
import com.helpdesk.api.repository.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "José da Silva", "27453653550", "josedasilva@gmail.com", encoder.encode("123"));
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Maria Rita", "75953735570", "mariarita@gmail.com", encoder.encode("123"));
		
		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamdo 01", null, tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
	}
}
