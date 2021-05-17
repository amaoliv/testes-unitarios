package io.github.olivmnd.servicos;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import io.github.olivmnd.builders.FilmeBuilder;
import io.github.olivmnd.builders.UsuarioBuilder;
import io.github.olivmnd.entidades.Filme;
import io.github.olivmnd.entidades.Locacao;
import io.github.olivmnd.entidades.Usuario;
import io.github.olivmnd.exceptions.FilmeSemEstoqueException;
import io.github.olivmnd.servicos.LocacaoService;
import io.github.olivmnd.utils.DataUtils;

public class TestWithExceptionsTreatment {

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	@Test

	public void testeLocacao() throws Throwable {
		
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		Filme filme = FilmeBuilder.umFilme().agora();
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		
		//verificacao com Ruler
		error.checkThat(locacao.getValor(), CoreMatchers.is(5.0) );
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(),new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(),DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
	}
	
		@Test(expected=FilmeSemEstoqueException.class)
		public void testLocacao_filmeSemEstoque() throws Exception {
			
			//cenario
			LocacaoService service = new LocacaoService();
			Usuario usuario = UsuarioBuilder.umUsuario().agora();
			Filme filme = FilmeBuilder.umFilme().semEstoque().agora();
			
			//acao
			service.alugarFilme(usuario, filme); 
			
		}
		

		/*
			
		@Test
		public void testLocacao_filmeSemEstoque2() {
				
			//cenario
			LocacaoService service = new LocacaoService();
			Usuario usuario = UsuarioBuilder.umUsuario().agora();
			Filme filme = FilmeBuilder.umFilme().semEstoque().agora();
				
			//acao
			
			try {
				service.alugarFilme(usuario, filme);
				Assert.fail("Deveria ter lancado uma excecao");
			}catch(Exception e) {
				Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme sem estoque"));
			}		
		}
		
		@Test
		public void testLocacao_filmeSemEstoque3() throws Exception {
			
			//cenario
			LocacaoService service = new LocacaoService();
			Usuario usuario = UsuarioBuilder.umUsuario().agora();
			Filme filme = FilmeBuilder.umFilme().semEstoque().agora();		
			
			exception.expect(Exception.class);
			exception.expectMessage("Filme sem estoque");
			//acao
			service.alugarFilme(usuario, filme); 
			
			
		}
		
		*/
	}

