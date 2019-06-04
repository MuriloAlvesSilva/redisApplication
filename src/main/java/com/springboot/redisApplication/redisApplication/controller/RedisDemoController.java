package com.springboot.redisApplication.redisApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.redisApplication.redisApplication.model.Produto;

@RestController
public class RedisDemoController {
	
	private static final String REDIS_INDEX_KEY = "PRODUTO";
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	//CADASTRAR
	@RequestMapping(value="/produtos", method=RequestMethod.POST)
	public String criarProduto(@RequestBody Produto produto) {
		//Cria produto através de Hash
		redisTemplate.opsForHash().put(REDIS_INDEX_KEY, produto.getProducoId(), produto.toString());
		return "Produto cadastrado com sucesso";
	}
	
	//LISTAR
	@RequestMapping(value="/produtos", method=RequestMethod.GET)
	public ResponseEntity<Object> getProdutos(){
		return new ResponseEntity<>(redisTemplate.opsForHash().entries(REDIS_INDEX_KEY), HttpStatus.OK);
	}
	
	//ATUALIZAR
	@RequestMapping(value="/produtos/{produtoId}", method=RequestMethod.PUT)
	public String updateProduto(@PathVariable("produtoId") String produtoId, @RequestBody Produto produto) {
		
		redisTemplate.opsForHash().put(REDIS_INDEX_KEY, produto.getProducoId(), produto.toString());
		
		return "Produto Atualizado com sucesso";		
	}
	
	//DELETAR
	@RequestMapping(value="/produtos/{produtoId}", method=RequestMethod.DELETE)
	public String deleteProduto(@PathVariable("produtoId") String produtoId) {
		redisTemplate.opsForHash().delete(REDIS_INDEX_KEY, produtoId);
		return "Produto foi deletado";
	}
	
	//PESQUISAR POR ID
	@RequestMapping(value="/produtos/{produtoId}", method=RequestMethod.GET)
	public Object getProdutoById(@PathVariable("produtoId") String produtoId) {		
		return redisTemplate.opsForHash().get(REDIS_INDEX_KEY, produtoId);
	}
	
	
}
