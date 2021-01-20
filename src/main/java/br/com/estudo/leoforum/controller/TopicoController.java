package br.com.estudo.leoforum.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.estudo.leoforum.dto.DetalheTopicoDTO;
import br.com.estudo.leoforum.dto.TopicoDTO;
import br.com.estudo.leoforum.form.AtualizacaoTopicoForm;
import br.com.estudo.leoforum.form.TopicoForm;
import br.com.estudo.leoforum.modelo.Topico;
import br.com.estudo.leoforum.repository.CursoRepository;
import br.com.estudo.leoforum.repository.TopicoRepository;

@Controller
@RequestMapping("/topicos")
@ResponseBody
public class TopicoController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<TopicoDTO> listaTopicos(String nomeCurso) {
		
		List<Topico> topicos = new ArrayList<Topico>();
		
		if (nomeCurso == null) {
			
			topicos = topicoRepository.findAll();
			
		} else {
			
			topicos = topicoRepository.findByCursoNome(nomeCurso);
			
		}
		
		return TopicoDTO.toTopicoDTO(topicos);
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDTO> cadastraNovoTopico(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriComponentsBuilder) {
		
		Topico topico = topicoForm.toTopico(cursoRepository);
		
		topicoRepository.save(topico);
		
		URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalheTopicoDTO> detalhaTopico(@PathVariable Long id) {
		
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if (topico.isPresent()) {
			
			return ResponseEntity.ok(new DetalheTopicoDTO(topico.get()));
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizaTopico(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm atualizacaoTopicoForm) {
		
		Optional<Topico> topicoOptional = topicoRepository.findById(id);
		
		if (topicoOptional.isPresent()) {
			
			Topico topico = atualizacaoTopicoForm.atualizaTopico(id, topicoRepository);
			
			return ResponseEntity.ok(new TopicoDTO(topico));
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletaTopico(@PathVariable Long id) {
		
		Optional<Topico> topicoOptional = topicoRepository.findById(id);
		
		if (topicoOptional.isPresent()) {
			
			topicoRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
}
