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
		TeikiTest tt = new TeikiTest();
		ArrayList<String> tlist = tt.getList("test");
		ArrayList<String> nlist = tt.getList("nen");
		ArrayList<String> mlist = tt.getList("kyu");
		ArrayList<String> klist = tt.getList("ka");

		request.setAttribute("tlist", tlist);
		request.setAttribute("nlist", nlist);
		request.setAttribute("mlist", mlist);
		request.setAttribute("klist", klist);

/*
		request.setAttribute("avg", 0.0);
*/
		RequestDispatcher dispatch = request.getRequestDispatcher("ranking3.jsp");
		dispatch.forward(request, response);		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// FORM データの取得
		String tval = request.getParameter("tnamae");	// 試験名
		String nval = request.getParameter("nen");		// 学年
		String mval = request.getParameter("kyu");		// 学級
		String kval = request.getParameter("ka");		// 教科

		TeikiTest tt = new TeikiTest();
		ArrayList<String> tlist = tt.getList("test");
		ArrayList<String> nlist = tt.getList("nen");
		ArrayList<String> mlist = tt.getList("kyu");
		ArrayList<String> klist = tt.getList("ka");

		request.setAttribute("tlist", tlist);
		request.setAttribute("nlist", nlist);
		request.setAttribute("mlist", mlist);
		request.setAttribute("klist", klist);

		request.setAttribute("tval", tval);	// 指定された試験名
		request.setAttribute("nval", nval);	// 指定された学年
		request.setAttribute("mval", mval);	// 指定された学級
		request.setAttribute("kval", kval);	// 指定された学級


		/*
		ArrayList<Memo> list = new ArrayList<Memo>();
		try{
			PreparedStatement stmt = con.prepareStatement("SELECT * from memo");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Memo s = new Memo();
				s.setMid(rs.getInt("mid"));
				s.setBody(rs.getString("body"));
				s.setMtime(rs.getTimestamp("mtime"));
				list.add(s);
			}

			rs.close();
			stmt.close();
		*/


		// ランキング、平均点の設定
		ArrayList<RankTableTest> list = tt.Ranking();

		request.setAttribute("tablelist", list);		// 順位データをセット
		String avg = Float.toString(tt.getAvg());		// 平均点をセット
		request.setAttribute("avg", avg);

		RequestDispatcher dispatch = request.getRequestDispatcher("ranking3.jsp");
		dispatch.forward(request, response);

		// TODO Auto-generated method stub
	}
}
