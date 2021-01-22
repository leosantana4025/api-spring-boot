package br.com.estudo.leoforum.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@Cacheable(value = "listaTopicos")
	public Page<TopicoDTO> listaTopicos(@RequestParam(required = false)
	String nomeCurso, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10)Pageable paginacao) {
		
		if (nomeCurso == null) {
			
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDTO.toTopicoDTO(topicos);
			
		} else {
			
			Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
			return TopicoDTO.toTopicoDTO(topicos);
			
		}
		
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaTopicos", allEntries = true)
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
	@CacheEvict(value = "listaTopicos", allEntries = true)
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
	@CacheEvict(value = "listaTopicos", allEntries = true)
	public ResponseEntity<?> deletaTopico(@PathVariable Long id) {
		
		Optional<Topico> topicoOptional = topicoRepository.findById(id);
		
		if (topicoOptional.isPresent()) {
			
			topicoRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
}
