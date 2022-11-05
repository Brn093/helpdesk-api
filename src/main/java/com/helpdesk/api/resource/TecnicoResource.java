package com.helpdesk.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.api.model.Tecnico;
import com.helpdesk.api.model.dto.TecnicoDTO;
import com.helpdesk.api.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos" )
public class TecnicoResource {
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {		
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
}