package com.helpdesk.api.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.api.exception.DataIntegratyViolationException;
import com.helpdesk.api.exception.ObjectNotFoundException;
import com.helpdesk.api.model.Pessoa;
import com.helpdesk.api.model.Tecnico;
import com.helpdesk.api.model.dto.TecnicoDTO;
import com.helpdesk.api.repository.PessoaRepository;
import com.helpdesk.api.repository.TecnicoRepository;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEmail(objDTO);
		Tecnico objNovo = new Tecnico(objDTO);
		return repository.save(objNovo);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico objAntigo = findById(id);
		validaPorCpfEmail(objDTO);
		objAntigo = new Tecnico(objDTO);
		
		return repository.save(objAntigo);
	}

	private void validaPorCpfEmail(TecnicoDTO objDTO) {
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
		Tecnico obj = findById(id);
		
		if(obj.getChamados().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		}
		repository.deleteById(id);		
	}
}
