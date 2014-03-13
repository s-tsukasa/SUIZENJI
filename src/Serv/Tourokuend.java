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
 * Servlet implementation class Touroku_end
 */
@WebServlet("/Touroku_end")
public class Tourokuend extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tourokuend() {
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
		// TODO Auto-generated method stub
		int tid = Integer.parseInt(request.getParameter("tid"));
		int kid = Integer.parseInt(request.getParameter("kid"));
		String mode = request.getParameter("mode");
		int num = Integer.parseInt(request.getParameter("num"));
		String str_sid;
		String str_ten;

		SeisekiKanriDB db = new SeisekiKanriDB();
//		ArrayList<Tokuten> tenlist = db.getTokutenList(sid,tlist);
		ArrayList<TestSyousai> tlist = db.getTestSyousaiList();
		ArrayList<Tokuten> tenlist = db.getTokutenList();
		ArrayList<SeitoAll> slist_tmp = db.getSeitoAllList();

		// 処理
		ArrayList<SeitoAll> slist = new ArrayList<SeitoAll>();
		for(int i=0;i<num;i++) {
			str_sid = "sid" + i;
			str_ten = "ten" + i;
//			ArrayList<TestSyousai> tlist = db.getTestSyousaiList(tid,kid);
//			db.insertTokuten(Integer.parseInt(request.getParameter(str_sid)), tlist.get(0).getTdid(), Integer.parseInt(request.getParameter(str_ten)));
		}
		db.close();

		request.setAttribute("mode", mode);

		// 処理を"test.jsp"に処理を受け渡す
		RequestDispatcher dispatch = request.getRequestDispatcher("tourokuend.jsp");
		dispatch.forward(request, response);
	}

}
