package io.github.olivmnd.servicos;

import java.security.Provider.Service;
import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.github.olivmnd.builders.FilmeBuilder;
import io.github.olivmnd.builders.UsuarioBuilder;
import io.github.olivmnd.daos.LocacaoDAO;
import io.github.olivmnd.entidades.Filme;
import io.github.olivmnd.entidades.Locacao;
import io.github.olivmnd.entidades.Usuario;
import io.github.olivmnd.exceptions.FilmeSemEstoqueException;
import io.github.olivmnd.exceptions.LocadoraException;
import io.github.olivmnd.servicos.LocacaoService;
import io.github.olivmnd.servicos.SPCService;
import io.github.olivmnd.utils.DataUtils;

public class TestWithExceptionsTreatmentAndMock {
	
	@InjectMocks
	private LocacaoService service;
	
	@Mock
	private SPCService spc;
	
	@Mock
	private LocacaoDAO dao;

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	//exemplo de uso de mockito
	public void setup() {
		MockitoAnnotations.initMocks(this);
		/*service = new LocacaoService();
		dao = Mockito.mock(LocacaoDAO.class);
		service.setLocacaoDAO(dao);
		spc =Mockito.mock(SPCService.class);
		service.setSPCService(spc);*/
	}
	
	
	@Test

	public void testeLocacao() throws Throwable {
		
	
		//cenario
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
			Usuario usuario = UsuarioBuilder.umUsuario().agora();
			Filme filme = FilmeBuilder.umFilme().semEstoque().agora();
			
			//acao
			service.alugarFilme(usuario, filme); 
			
		}
		
		@Test
		public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
			//cenario
			Filme filme = FilmeBuilder.umFilme().agora();
			
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
			Usuario usuario = UsuarioBuilder.umUsuario().agora();
			
			
			exception.expect(LocadoraException.class);
			exception.expectMessage("Filme vazio");
	
			//acao
			service.alugarFilme(usuario, null);
			
		}
		
		@Test
		public void naoDeveAlugarFilmeParaNegativadoSPC() throws FilmeSemEstoqueException, LocadoraException {
			//cenario
			Usuario usuario = UsuarioBuilder.umUsuario().agora();
			Filme filme = FilmeBuilder.umFilme().agora();
			
			Mockito.when(spc.possuiNegativacao(Mockito.any(Usuario.class))).thenReturn(true);
			exception.expect(LocadoraException.class);
			exception.expectMessage("Usuário Negativado");
		
			
			//acao
			service.alugarFilme(usuario, filme);
			
			//verificacao
		}
}
