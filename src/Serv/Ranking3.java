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
		// 試験ごとのclass生成
		TeikiTest tt = new TeikiTest();
		// プルダウンメニューの情報を生成
		ArrayList<Test>   tlist = tt.getTest();
		ArrayList<String> nlist = tt.getList("nen");
		ArrayList<String> mlist = tt.getList("kyu");
		ArrayList<Kyouka> klist = tt.getKyouka();
		// jspへデータセット
		request.setAttribute("tlist", tlist);
		request.setAttribute("nlist", nlist);
		request.setAttribute("mlist", mlist);
		request.setAttribute("klist", klist);

		// 初期表示設定
		request.setAttribute("tval", "1");	// 試験ID
		request.setAttribute("nval", "1");	// 学年
		request.setAttribute("mval", "-");	// 学級
		request.setAttribute("kval", "0");	// 教科ID ->合計

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

		// 試験ごとのclass生成
		TeikiTest tt = new TeikiTest();
		// プルダウンメニューの情報を生成
		ArrayList<Test>   tlist = tt.getTest();
		ArrayList<String> nlist = tt.getList("nen");
		ArrayList<String> mlist = tt.getList("kyu");
		ArrayList<Kyouka> klist = tt.getKyouka();
		// jspへデータセット
		request.setAttribute("tlist", tlist);
		request.setAttribute("nlist", nlist);
		request.setAttribute("mlist", mlist);
		request.setAttribute("klist", klist);
		// FORMで指定された値を設定
		request.setAttribute("tval", tstr);	// 指定された試験名
		request.setAttribute("nval", nstr);	// 指定された学年
		request.setAttribute("mval", mstr);	// 指定された学級
		request.setAttribute("kval", kstr);	// 指定された学級

		// 抽出条件の設定
		tt.setNen(Integer.parseInt(nstr));	// 学年
		tt.setKyu(mstr);					// 学級
		tt.setTid(tval);					// 試験ID
		tt.setKid(kval);					// 教科ID

		// ランキング、平均点の設定
		ArrayList<RankTableTest> list = tt.Ranking();
		if(list == null) {
			request.setAttribute("outstr", "該当するデータがありません。");
			RequestDispatcher dispatch = request.getRequestDispatcher("ranking3.jsp");
			dispatch.forward(request, response);
			return;
		}

		request.setAttribute("tablelist", list);		// 順位データをセット
//		String avg = Float.toString(tt.getAvg());		// 平均点をセット
		String avg = String.format("%.2f", tt.getAvg());		// 平均点をセット
		request.setAttribute("avg", avg);

		RequestDispatcher dispatch = request.getRequestDispatcher("ranking3.jsp");
		dispatch.forward(request, response);

		// TODO Auto-generated method stub
	}
}
