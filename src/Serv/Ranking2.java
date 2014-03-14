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

		if (request.getParameter("sid") != null){
			int sid = Integer.parseInt(request.getParameter("sid"));

			SeisekiKanriDB db1 = new SeisekiKanriDB();
			ArrayList<TokutenTbl2> list1 = db1.getTokutenTbl2List(sid);
			ArrayList<TokutenTbl2> list2 = db1.getTokutenTbl2List(sid,0);
			db1.close();
			request.setAttribute("sid", Integer.toString(sid));
			request.setAttribute("slist1", list1);
			request.setAttribute("slist2", list2);
			request.setAttribute("mode","1");

		}else {
			request.setAttribute("mode","0");
		}
		int nen = Integer.parseInt(request.getParameter("nen"));
		String kyu=request.getParameter("kyu");

		SeisekiKanriDB db = new SeisekiKanriDB();
		ArrayList<SeitoAll> list = db.getSeitoAllList(nen,kyu);
		db.close();



		request.setAttribute("slist", list);
		request.setAttribute("nen", Integer.toString(nen));
		request.setAttribute("kyu", kyu);

		RequestDispatcher dispatch = request.getRequestDispatcher("ranking2.jsp");
		dispatch.forward(request, response);
		// TODO Auto-generated method stub
	}

}
