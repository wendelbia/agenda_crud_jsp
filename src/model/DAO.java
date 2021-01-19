package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DAO {
//m�dulo de conex�o
	//Par�metro de conex�o
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";
	
//M�todo de conex�o 
	//usando o private agora s� a classe dao pode usar o m�todo de conex�o
	//quando uso um m�todo sem a palavra void ele precisa de um retorno 
	private Connection conectar() {
		//uso o objeto con para criar uma se��o com o bando de dados
		Connection con = null;
		//try catch para estabelecer um conex�o com o banco, uso vai pegar as exce��es, uso ctrl+space para cri�-lo
		try {
			//na tentativa de conectar
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			//caso aconte�a uma excess�o
			System.out.println(e);
			return null;
		}
	}
	/*teste de conex�o
	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
			//no Controller.java importo o DAO l� para o teste de conex�o
		} catch (Exception e) {
			System.out.println(e);
		}
	}*/
	//create
	public void inserirContato(JavaBeans contato) {
		//as interroga��es s�o os dados armazenados nas vari�veis do JavaBeans
		String create = "insert into contatos (nome, fone, email) values (?,?,?)";
		try {
			//abrir a conex�o com o banco
			Connection con = conectar();
			//Preparar a query para execu��o no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			//substituo os par�metros pelos conte�dos das vari�veis JavaBeans encapsuladas com m�todos publicos
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			//tendo substituido os par�metros pelos dados que vem da JavaBeans
			//executo a query
			//� o comando que ensire os dados no banco
			pst.executeUpdate();
			//� necess�rio encerrar a conex�o com o banco pois pode ter problema de performance
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
