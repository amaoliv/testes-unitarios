package io.github.olivmnd.servicos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


	@RunWith(Suite.class)
	@SuiteClasses({
		CalculadoraTest.class, 
		LocacaoServiceTest.class, 
		TestWithExceptionsTreatment.class,
		TestWithExceptionsTreatmentAndMock.class
	})
	public class SuiteExecucao {
		//remover se puder!!
}
