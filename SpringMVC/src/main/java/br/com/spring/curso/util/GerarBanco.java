package br.com.spring.curso.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class GerarBanco {

	public static void main(String[] args) {
		System.out.println("Inicio");
		gerarBanco();
		System.out.println("Fim");
	}

	public static void gerarBanco() {
		// Carrega as configurações do arquivo
		// hibernate.cfg.xml
		Configuration conf = new Configuration();
		conf.configure();
		SchemaExport se2 = new SchemaExport(conf);
		// Executa a operação da criação do Banco de Dados
		se2.create(true, true);
	}
}