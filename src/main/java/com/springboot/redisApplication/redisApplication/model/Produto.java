package com.springboot.redisApplication.redisApplication.model;

public class Produto {
	private String producoId;
	private String produtoNome;
	private String preco;
	
	//GGAS
	public String getProducoId() {
		return producoId;
	}
	public void setProducoId(String producoId) {
		this.producoId = producoId;
	}
	public String getProdutoNome() {
		return produtoNome;
	}
	public void setProdutoNome(String produtoNome) {
		this.produtoNome = produtoNome;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	
	
	@Override
	public String toString() {
		return "Produto [producoId=" + producoId + ", produtoNome=" + produtoNome + ", preco=" + preco + "]";
	}
	
	
}
