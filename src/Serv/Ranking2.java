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
 * Servlet implementation class Ranking2
 */
@WebServlet("/Ranking2")
public class Ranking2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ranking2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nen = Integer.parseInt(request.getParameter("nen"));
		String kyu=request.getParameter("kyu");

		SeisekiKanriDB db = new SeisekiKanriDB();
		ArrayList<SeitoAll> list = db.getSeitoAllList();
		db.close();

		request.setAttribute("slist", list);
		request.setAttribute("nen", Integer.toString(nen));
		request.setAttribute("kyu", kyu);

		RequestDispatcher dispatch = request.getRequestDispatcher("ranking2.jsp");
		dispatch.forward(request, response);
		// TODO Auto-generated method stub
	}

}
