package io.github.olivmnd.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.github.olivmnd.exceptions.DivisaoPorZeroException;
import io.github.olivmnd.servicos.Calculadora;

public class CalculadoraTest {
	
	private Calculadora calc;

	@Before
	public void setup() {
		calc = new Calculadora();
	}
	
	@Test
	public void deveSomarDoisValores() {
		
		//cenario
		int a = 5;
		int b = 3;
		Calculadora calc = new Calculadora();
		
		//acao
		int resultado = calc.somar(a, b);
		
		//verificacao
		
		Assert.assertEquals(8, resultado);
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		//cenario
		int a = 8;
		int b = 5;

				
		//acao
		int resultado = calc.subtrair(a, b);
				
		//verificacao
		Assert.assertEquals(3, resultado);
	}
	
	@Test
	public void deveDividirDoisValores() throws DivisaoPorZeroException{
		//cenario
		int a = 10;
		int b = 2;
		
		//acao
		int resultado = calc.dividir(a,b);
		//verificacao
		Assert.assertEquals(5, resultado);
		
	}
	
	@Test(expected = DivisaoPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws DivisaoPorZeroException {
		//cenario
		int a = 10;
		int b = 0;
	
		
		//acao
		calc.dividir(a,b);
		
		//verificacao

		
	}

}
