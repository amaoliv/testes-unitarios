package io.github.olivmnd.servicos;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import io.github.olivmnd.servicos.Calculadora;

import org.junit.Assert;
import org.junit.Before;


public class CalculadoraMockTest {
	
	@Mock
	private Calculadora calcMock;
	
	@Spy
	private Calculadora calcSpy;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void diferencaEntreMockSpy() {
		
		Mockito.when(calcMock.somar(1, 2)).thenReturn(8);
		Mockito.when(calcSpy.somar(1, 2)).thenReturn(8);
		
		System.out.println("Mock: " + calcMock.somar(1,5));
		System.out.println("Spy: " + calcSpy.somar(1,5));
	}
	
	@Test
	public void teste() {
		Calculadora calc = Mockito.mock(Calculadora.class);
		
		ArgumentCaptor<Integer> argCapt = ArgumentCaptor.forClass(Integer.class);
		Mockito.when(calc.somar(argCapt.capture(), argCapt.capture())).thenReturn(5);
		//Mockito.when(calc.somar(Mockito.eq(1), Mockito.anyInt())).thenReturn(5);
	
		Assert.assertEquals(5, calc.somar(1, 200));
		//System.out.println(argCapt.getAllValues());
	}

}
