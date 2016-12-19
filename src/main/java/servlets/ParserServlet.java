package servlets;

public class ParserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throw Throwable {
		PrintWriter() writer = response.getWriter();
		writer.printline("<h1>Hello</h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throw Throwable {
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throw Throwable {
	
	}

	protected void do(HttpServletRequest request, HttpServletResponse response) throw Throwable {
	
	}
	

}
