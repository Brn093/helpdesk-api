package com.helpdesk.api.model;

public enum Prioridade {
	BAIXA(0, "BAIXA"),
	MEDIA(1, "MÉDIA"),
	ALTA(2, "ALTA");
	
	private Integer id;
	private String descricao;
	
	private Prioridade(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Prioridade toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for(Prioridade x : Prioridade.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Perfil inválido");
	}
}
