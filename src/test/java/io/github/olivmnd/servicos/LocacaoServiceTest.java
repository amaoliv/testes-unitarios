package io.github.olivmnd.servicos;


import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import io.github.olivmnd.builders.FilmeBuilder;
import io.github.olivmnd.builders.UsuarioBuilder;
import io.github.olivmnd.entidades.Filme;
import io.github.olivmnd.entidades.Locacao;
import io.github.olivmnd.entidades.Usuario;
import io.github.olivmnd.servicos.LocacaoService;
import io.github.olivmnd.utils.DataUtils;

public class LocacaoServiceTest {
	
	@Test

	public void testeLocacao() throws Exception {
		
		
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		Filme filme = FilmeBuilder.umFilme().agora();
		
		//acao
		//Locacao locacao;
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		
		//verificacao com Assert
		
		/*
		//importancia da ordem
		Assert.assertEquals(locacao.getValor(), 4.0, 0.01);
		
		Assert.assertEquals(4.0, locacao.getValor(), 0.01);*/
		
		//
		
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(),new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(),DataUtils.obterDataComDiferencaDias(1)));
		
		//verificacao com AssertThat
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(5.0) );
		Assert.assertThat(locacao.getValor(), CoreMatchers.not(6.0));
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(),new Date()), CoreMatchers.is(true));
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(),DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
		
		
	}
}
