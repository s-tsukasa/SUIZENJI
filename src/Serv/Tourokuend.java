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
		ArrayList<Tokuten> tenlist = new ArrayList<Tokuten>();
		ArrayList<TestSyousai> tlist = new ArrayList<TestSyousai>();

		// 処理
		try {
			for(int i=0;i<num;i++) {
				str_ten = "ten" + i;
				if(Integer.parseInt(request.getParameter(str_ten)) < 0 || Integer.parseInt(request.getParameter(str_ten)) > 100){
					db.close();

					request.setAttribute("outstr", "得点は0～100で入力してください");
					// 処理を"test.jsp"に処理を受け渡す
					RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
					dispatch.forward(request, response);
					return;
				}
			}

			for(int i=0;i<num;i++) {
				str_sid = "sid" + i;
				str_ten = "ten" + i;
				tlist = db.getTestSyousaiList(tid,kid);

				if(!tlist.isEmpty()){
						tenlist = db.getTokutenList(Integer.parseInt(request.getParameter(str_sid)),tlist.get(0).getTdid());

						if(!tenlist.isEmpty()){
							db.updateTokuten(tenlist.get(0).getTenid(), Integer.parseInt(request.getParameter(str_ten)));
						}
						else{
							db.insertTokuten(Integer.parseInt(request.getParameter(str_sid)), tlist.get(0).getTdid(), Integer.parseInt(request.getParameter(str_ten)));
						}
				}

			}
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			db.close();

			request.setAttribute("outstr", "不正な入力がありました");
			// 処理を"test.jsp"に処理を受け渡す
			RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
			dispatch.forward(request, response);
			return;
		}
		db.close();

		request.setAttribute("mode", mode);

		// 処理を"test.jsp"に処理を受け渡す
		RequestDispatcher dispatch = request.getRequestDispatcher("tourokuend.jsp");
		dispatch.forward(request, response);
	}

}
