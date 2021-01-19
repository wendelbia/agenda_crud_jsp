package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
/*importar para o teste de conex�o*/
import model.DAO;
//impoto a classe model
import model.JavaBeans;
//aqui recebe os valores do formul�rio atrav�s do action="insert"
@WebServlet(urlPatterns = {"/Controller", "/main", "/insert" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   // uso para testar a conex�o
	DAO dao = new DAO();
	//crio o objeto model
	JavaBeans contato = new JavaBeans();
    
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//esse linha j� n�o � mais necess�ria
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*TESTE DE CONEX�O
		dao.testeConexao();*/
		
		String action = request.getServletPath();
		//essa impress�o foi apenas para teste
		System.out.println(action);
		//se o m�todo doget receber essa requisi��o /main eu quero redirecionanar ao m�todo que ir� trabalhar espec�ficamente est� requisa��o 
		//se for main redireciona para o m�todo contatos
		if(action.equals("/main")) {
			contatos(request, response);
			//combina��o de teclas shift+alt+y para fazer a quebra autom�tica shift+alt+f para identa��o do c�digo
			
		//se for insert redireciona para o m�todo novoContatos
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}
		
	}
	//vou c�piar a estrutura do m�todo doGet pois � a mesma
	//Lista contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("agenda.jsp");
	}
	//Novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("agenda.jsp");
		/*tedste de recebimento dos dados do formul�rio
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));*/
		//o setNome pega os valores do formul�rio e armazena na vari�vel nome
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//vamos invocar o m�todo inserirContato passando o objeto contato
		dao.inserirContato(contato);
		//redirecionao evocando o metodo contato
		response.sendRedirect("main");
	}
}
