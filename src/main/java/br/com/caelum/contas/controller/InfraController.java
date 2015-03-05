package br.com.caelum.contas.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.ConnectionFactory;

@Controller
public class InfraController {

	@RequestMapping("/tabelas")
	public String criaBanco() throws SQLException {
		Connection c = new ConnectionFactory().getConnection();
		
		PreparedStatement st1 = c.prepareStatement("DROP TABLE IF EXISTS contas");
		st1.execute();

		PreparedStatement st11 = c.prepareStatement("create table contas (id_conta integer not null, descricao varchar(255), valor float8, paga bool, dataPagamento timestamp, tipo varchar(20), primary key (id_conta));");
		st11.execute();
		
		PreparedStatement st12 = c.prepareStatement("DROP SEQUENCE IF EXISTS contas_idConta_seq;");
		st12.execute();
		
		PreparedStatement st13 = c.prepareStatement("CREATE SEQUENCE contas_idConta_seq START 1;");
		st13.execute();
		
		PreparedStatement st14 = c.prepareStatement("ALTER SEQUENCE contas_idConta_seq OWNED BY contas.id_conta;");
		st14.execute();
		
		PreparedStatement st2 = c.prepareStatement("DROP TABLE IF EXISTS usuarios;");
		st2.execute();

		PreparedStatement st22 = c.prepareStatement("create table usuarios (login VARCHAR(255),senha VARCHAR(255));");
		st22.execute();

		PreparedStatement st3 = c.prepareStatement("insert into usuarios (login, senha) values ('caelum', 'online');");
		st3.execute();
		
		c.close();
		
		return "infra-ok";

	}
}
