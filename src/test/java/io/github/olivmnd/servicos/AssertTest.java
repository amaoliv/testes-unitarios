package io.github.olivmnd.servicos;
import org.junit.Assert;
import org.junit.Test;

import io.github.olivmnd.entidades.Usuario;

public class AssertTest {
	
	@Test
	public void test() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals("Erro de comparação", 1, 1); //deixando uma mensagem
		
		//verificando valores
		Assert.assertEquals(1,1);
		
		//verificando valores double
		Assert.assertEquals(0.51, 0.51, 0.01); //é necessário um delta para medida de margem de erro
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		
		//TIPOS PRIMITIVOS VS OBJETOS
		int i = 5;
		Integer i2 = 5;
		
		//convertendo o tipo primitivo para Objeto
		Assert.assertEquals(Integer.valueOf(i), i2);
		
		//convertendo o Objeto para tipo primitivo
		Assert.assertEquals(i, i2.intValue());
		
		
		//COMPARAÇÃO DE STRINGS
		
		//com Equals
		Assert.assertEquals("bola", "bola");
	
		Assert.assertNotEquals("bola", "casa");
		
		//com IgnoreCase
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		
		//usando o radical
		Assert.assertTrue("bola".startsWith("bo"));
		
		
		//COMPARANDO COM O PRÓPRIO OBJETO
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Usuario u3 = u2;
		Usuario u4 = null;
		
		//comparando nomes; é necessário que o Objeto tenha o método Equals implementado
		Assert.assertEquals(u1,u2);
		
		//comparando intancias com o método Equals implementado
		Assert.assertSame(u3, u2);
		Assert.assertNotSame(u1, u2);
		
		//comparando com null
		Assert.assertTrue(u4 == null);
		Assert.assertNull(u4);
		Assert.assertNotNull(u1);
	}
	
}
