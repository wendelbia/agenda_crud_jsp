package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DAO {
//módulo de conexão
	//Parâmetro de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";
	
//Método de conexão 
	//usando o private agora só a classe dao pode usar o método de conexão
	//quando uso um método sem a palavra void ele precisa de um retorno 
	private Connection conectar() {
		//uso o objeto con para criar uma seção com o bando de dados
		Connection con = null;
		//try catch para estabelecer um conexão com o banco, uso vai pegar as exceções, uso ctrl+space para criá-lo
		try {
			//na tentativa de conectar
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			//caso aconteça uma excessão
			System.out.println(e);
			return null;
		}
	}
	/*teste de conexão
	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
			//no Controller.java importo o DAO lá para o teste de conexão
		} catch (Exception e) {
			System.out.println(e);
		}
	}*/
	//create
	public void inserirContato(JavaBeans contato) {
		//as interrogações são os dados armazenados nas variáveis do JavaBeans
		String create = "insert into contatos (nome, fone, email) values (?,?,?)";
		try {
			//abrir a conexão com o banco
			Connection con = conectar();
			//Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			//substituo os parâmetros pelos conteúdos das variáveis JavaBeans encapsuladas com métodos publicos
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			//tendo substituido os parâmetros pelos dados que vem da JavaBeans
			//executo a query
			//é o comando que ensire os dados no banco
			pst.executeUpdate();
			//é necessário encerrar a conexão com o banco pois pode ter problema de performance
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
