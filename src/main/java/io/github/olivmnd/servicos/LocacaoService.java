package io.github.olivmnd.servicos;

import static io.github.olivmnd.utils.DataUtils.adicionarDias;

import java.awt.List;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import io.github.olivmnd.daos.LocacaoDAO;
import io.github.olivmnd.entidades.Filme;
import io.github.olivmnd.entidades.Locacao;
import io.github.olivmnd.entidades.Usuario;
import io.github.olivmnd.exceptions.FilmeSemEstoqueException;
import io.github.olivmnd.exceptions.LocadoraException;
import io.github.olivmnd.utils.DataUtils;

public class LocacaoService {
	
	private LocacaoDAO dao;
	private SPCService spcService;
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {
	
	
		if(usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		if (filme == null) {
			throw new LocadoraException("Filme vazio");
		}
		if(filme.getEstoque() ==0) {
			throw new FilmeSemEstoqueException();
		}
		
		if(spcService.possuiNegativacao(usuario)) {
			throw new LocadoraException("Usuário Negativado");
		}
		
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		dao.salvar(locacao);
		
		return locacao;
	}
	
	//métodos de injeção
	
	public void setLocacaoDAO(LocacaoDAO dao) {
		this.dao = dao;
	}
	
	public void setSPCService(SPCService spc) {
		spcService = spc;
	}
}