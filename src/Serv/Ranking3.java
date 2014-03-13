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
		ArrayList<Test>   tlist = tt.getTest();
		ArrayList<String> nlist = tt.getList("nen");
		ArrayList<String> mlist = tt.getList("kyu");
		ArrayList<Kyouka> klist = tt.getKyouka();

		request.setAttribute("tlist", tlist);
		request.setAttribute("nlist", nlist);
		request.setAttribute("mlist", mlist);
		request.setAttribute("klist", klist);

		// 初期表示
		request.setAttribute("tval", "1");	// 試験ID
		request.setAttribute("nval", "1");	// 学年
		request.setAttribute("mval", "-");	// 学級
		request.setAttribute("kval", "1");	// 教科ID

		RequestDispatcher dispatch = request.getRequestDispatcher("ranking3.jsp");
		dispatch.forward(request, response);		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// FORM データの取得
		String tstr = request.getParameter("tnamae");	// 試験ID
		int    tval = Integer.parseInt(request.getParameter("tnamae"));	// 試験ID
		String nstr = request.getParameter("nen");		// 学年
		String mstr = request.getParameter("kyu");		// 学級
		String kstr = request.getParameter("ka");		// 教科ID
		int    kval = Integer.parseInt(request.getParameter("ka"));		// 教科ID

		TeikiTest tt = new TeikiTest();
		ArrayList<Test>   tlist = tt.getTest();
		ArrayList<String> nlist = tt.getList("nen");
		ArrayList<String> mlist = tt.getList("kyu");
		ArrayList<Kyouka> klist = tt.getKyouka();

		request.setAttribute("tlist", tlist);
		request.setAttribute("nlist", nlist);
		request.setAttribute("mlist", mlist);
		request.setAttribute("klist", klist);

		request.setAttribute("tval", tstr);	// 指定された試験名
		request.setAttribute("nval", nstr);	// 指定された学年
		request.setAttribute("mval", mstr);	// 指定された学級
		request.setAttribute("kval", kstr);	// 指定された学級


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

		//学年、学級、テストID、教科IDからリストを返す
		tt.setNen(Integer.parseInt(nstr));
		tt.setKyu(mstr);
		tt.setTid(tval);
		tt.setKid(kval);

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
