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
		// TODO Auto-generated method stub
		SeisekiKanriDB db = new SeisekiKanriDB();
		ArrayList<Test> tlist = db.getTestList();
		ArrayList<Kyouka> klist = db.getKyoukaList();
		db.close();



		request.setAttribute("tid",Integer.toString(tlist.get(0).getTid()));
		request.setAttribute("kid", Integer.toString(klist.get(0).getKid()));
		request.setAttribute("nen", "1");
		request.setAttribute("kyu", "A");

		request.setAttribute("tlist", tlist);
		request.setAttribute("klist", klist);

		RequestDispatcher dispatch = request.getRequestDispatcher("tokuten_ent.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int tid = Integer.parseInt(request.getParameter("tid"));
		int kid = Integer.parseInt(request.getParameter("kid"));
		int nen = Integer.parseInt(request.getParameter("nen"));
		String kyu = request.getParameter("kyu");


		SeisekiKanriDB db = new SeisekiKanriDB();
		ArrayList<Test> tlist = db.getTestList();
		ArrayList<Kyouka> klist = db.getKyoukaList();
		ArrayList<SeitoAll> slist_tmp = db.getSeitoAllList();
		db.close();

		// 処理
		ArrayList<SeitoAll> slist = new ArrayList<SeitoAll>();
		for(SeitoAll s : slist_tmp) {
			if(s.getGakunen() == nen && (s.getKyu()).equals(kyu) && s.getDelete_flag() == 0){
					slist.add(s);
			}
		}

		request.setAttribute("tid", Integer.toString(tid));
		request.setAttribute("kid", Integer.toString(kid));
		request.setAttribute("nen", Integer.toString(nen));
		request.setAttribute("kyu", kyu);

		request.setAttribute("tlist", tlist);
		request.setAttribute("klist", klist);
		request.setAttribute("slist", slist);

		// 処理を"test.jsp"に処理を受け渡す
		RequestDispatcher dispatch = request.getRequestDispatcher("tokuten_ent.jsp");
		dispatch.forward(request, response);
	}

}
