package br.com.estudo.leoforum.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.estudo.leoforum.modelo.StatusTopico;
import br.com.estudo.leoforum.modelo.Topico;

public class DetalheTopicoDTO {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeUsuario;
	private StatusTopico status;
	private List<RespostaDTO> respostas;
	
	public DetalheTopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.nomeUsuario = topico.getUsuario().getNome();
		this.status = topico.getStatus();
		this.respostas = new ArrayList<>();
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaDTO> getRespostas() {
		return respostas;
	}

}