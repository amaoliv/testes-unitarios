package io.github.olivmnd.builders;

import io.github.olivmnd.entidades.Filme;
import io.github.olivmnd.entidades.Usuario;

public class FilmeBuilder {
	
		private Filme filme;
		
		private FilmeBuilder() {}
		
		public static FilmeBuilder umFilme() {	
			
			FilmeBuilder builder = new FilmeBuilder();
			builder.filme	= new Filme();
			builder.filme.setEstoque(2);
			builder.filme.setNome("Filme 1");
			builder.filme.setPrecoLocacao(5.0);
			return builder;
		
		}
		
		public FilmeBuilder semEstoque() {
			filme.setEstoque(0);
			return this;
		}
			
		public Filme agora() {
			return filme;
		}

}
