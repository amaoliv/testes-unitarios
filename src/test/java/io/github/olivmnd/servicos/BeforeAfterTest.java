package io.github.olivmnd.servicos;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import io.github.olivmnd.daos.LocacaoDAO;
import io.github.olivmnd.entidades.Filme;
import io.github.olivmnd.entidades.Locacao;
import io.github.olivmnd.entidades.Usuario;
import io.github.olivmnd.exceptions.FilmeSemEstoqueException;
import io.github.olivmnd.exceptions.LocadoraException;
import io.github.olivmnd.servicos.LocacaoService;
import io.github.olivmnd.utils.DataUtils;

public class BeforeAfterTest {
	
	private LocacaoService service;
	
	private static int contador = 0;

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		
		System.out.println("Before");
		service = new LocacaoService();
		contador++;
		System.out.println(contador);
	}
	
	@After
	public void tearDown() {
		System.out.println("After");
	}
	
	
	//precisando ser estaticos para o junit ter acesso a eles antes da classe ser criada
	@BeforeClass
	public static void setupClass() {
		System.out.println("Before Class");
	}
	
	@AfterClass
	public static void tearDownClass() {
		System.out.println("After Class");
	}
	
	
	@Test
	public void testeLocacao() throws Throwable {
		
	
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme ("Filme 1", 2, 5.0);
		
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
			Usuario usuario = new Usuario("Usuario 1");
			Filme filme = new Filme ("Filme 1", 0, 5.0);
			
			//acao
			service.alugarFilme(usuario, filme); 
			
		}
		
		@Test
		public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
			//cenario
			Filme filme = new Filme ("Filme 1", 1, 5.0);
			
			//acao
			try {
				service.alugarFilme(null, filme);
				Assert.fail();

			} catch (LocadoraException e) {
				Assert.assertThat(e.getMessage(), CoreMatchers.is("Usuario vazio"));
			} 
			
			System.out.println("Mensagem :D");

		}
		
		@Test
		public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
			//cenario
			Usuario usuario = new Usuario("Usuario 1");
			
			
			exception.expect(LocadoraException.class);
			exception.expectMessage("Filme vazio");
	
			//acao
			service.alugarFilme(usuario, null);
			
		}
}
