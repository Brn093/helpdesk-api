package com.helpdesk.api.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.api.exception.DataIntegratyViolationException;
import com.helpdesk.api.exception.ObjectNotFoundException;
import com.helpdesk.api.model.Cliente;
import com.helpdesk.api.model.Pessoa;
import com.helpdesk.api.model.dto.ClienteDTO;
import com.helpdesk.api.repository.ClienteRepository;
import com.helpdesk.api.repository.PessoaRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEmail(objDTO);
		Cliente objNovo = new Cliente(objDTO);
		return repository.save(objNovo);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente objAntigo = findById(id);
		validaPorCpfEmail(objDTO);
		objAntigo = new Cliente(objDTO);
		
		return repository.save(objAntigo);
	}

	private void validaPorCpfEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegratyViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegratyViolationException("E-mail já cadastrado no sistema!");
		}		
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		
		if(obj.getChamados().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		}
		repository.deleteById(id);		
	}
}
