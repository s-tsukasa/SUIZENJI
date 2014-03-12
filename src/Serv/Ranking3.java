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
 * Servlet implementation class Ranking3
 */
@WebServlet("/Ranking3")
public class Ranking3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ranking3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("ranking3.jsp");
		dispatch.forward(request, response);		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<RankTableTest> list = new ArrayList<RankTableTest>();

		// Debug
		for(int i = 1; i <= 5; i++) {
			RankTableTest r = new RankTableTest();
			r.setRank(i);
			r.setTen(500 - i);
			r.setKyu("A");
			r.setNamae("田中");
			list.add(r);
		}
		request.setAttribute("tlist", list);

		RequestDispatcher dispatch = request.getRequestDispatcher("ranking3.jsp");
		dispatch.forward(request, response);

		// TODO Auto-generated method stub
	}

}
