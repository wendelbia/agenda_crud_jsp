package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
/*importar para o teste de conexão*/
import model.DAO;
//impoto a classe model
import model.JavaBeans;
//aqui recebe os valores do formulário através do action="insert"
@WebServlet(urlPatterns = {"/Controller", "/main", "/insert" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   // uso para testar a conexão
	DAO dao = new DAO();
	//crio o objeto model
	JavaBeans contato = new JavaBeans();
    
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//esse linha já não é mais necessária
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*TESTE DE CONEXÃO
		dao.testeConexao();*/
		
		String action = request.getServletPath();
		//essa impressão foi apenas para teste
		System.out.println(action);
		//se o método doget receber essa requisição /main eu quero redirecionanar ao método que irá trabalhar específicamente está requisação 
		//se for main redireciona para o método contatos
		if(action.equals("/main")) {
			contatos(request, response);
			//combinação de teclas shift+alt+y para fazer a quebra automática shift+alt+f para identação do código
			
		//se for insert redireciona para o método novoContatos
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}
		
	}
	//vou cópiar a estrutura do método doGet pois é a mesma
	//Lista contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("agenda.jsp");
	}
	//Novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("agenda.jsp");
		/*tedste de recebimento dos dados do formulário
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));*/
		//o setNome pega os valores do formulário e armazena na variável nome
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		//vamos invocar o método inserirContato passando o objeto contato
		dao.inserirContato(contato);
		//redirecionao evocando o metodo contato
		response.sendRedirect("main");
	}
}
