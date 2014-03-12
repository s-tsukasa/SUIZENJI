package Serv;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Tokuten_ent
 */
@WebServlet("/Tokuten_ent")
public class Tokuten_ent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tokuten_ent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("tokuten_ent.jsp");
		dispatch.forward(request, response);		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tnamae = request.getParameter("tnamae");
		int ka = Integer.parseInt(request.getParameter("ka"));
		int nen = Integer.parseInt(request.getParameter("nen"));
		String kyu = request.getParameter("kyu");


		SeisekiKanriDB db = new SeisekiKanriDB();
		ArrayList<SeitoAll> slist = db.getSeitoAllList();
		db.close();

		// 処理
		request.setAttribute("slist", slist);

		// 処理を"test.jsp"に処理を受け渡す
		RequestDispatcher dispatch = request.getRequestDispatcher("tokuten_ent.jsp");
		dispatch.forward(request, response);
	}

}
