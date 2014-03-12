package Serv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
		//SeisekiKanriDB db = new SeisekiKanriDB();
		//ArrayList<Test> list = db.getTestList();
		//db.close();

		// debug
		ArrayList<String> tlist = new ArrayList<String>();
		tlist.add(new String("2013年度中間テスト"));
		tlist.add(new String("2013年度期末テスト"));
		tlist.add(new String("2014年度中間テスト"));
		tlist.add(new String("2014年度期末テスト"));
		request.setAttribute("tlist", tlist);
		// debug
		ArrayList<String> klist = new ArrayList<String>();
		klist.add(new String("合計"));
		klist.add(new String("国語"));
		klist.add(new String("算数"));
		klist.add(new String("理科"));
		request.setAttribute("klist", klist);

		RequestDispatcher dispatch = request.getRequestDispatcher("ranking3.jsp");
		dispatch.forward(request, response);		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// debug
		ArrayList<String> tlist = new ArrayList<String>();
		tlist.add(new String("2013年度中間テスト"));
		tlist.add(new String("2013年度期末テスト"));
		tlist.add(new String("2014年度中間テスト"));
		tlist.add(new String("2014年度期末テスト"));
		request.setAttribute("tlist", tlist);

		// 選択されたものを表示させること！

		// debug
		ArrayList<String> klist = new ArrayList<String>();
		klist.add(new String("合計"));
		klist.add(new String("国語"));
		klist.add(new String("算数"));
		klist.add(new String("理科"));
		request.setAttribute("klist", klist);

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
		ArrayList<RankTableTest> list = new ArrayList<RankTableTest>();
		// 総得点の場合は、いったん合計する

		// データベースにアクセス
		//		点数、組、名前の情報を取得

		// RankTableTest にセット
		//debug
		//+---------------------+------+------+------+------------+
		//| tnamae              | nen  | ka   | ten  | namae      |
		//+---------------------+------+------+------+------------+
		//| 12年度1学期中間試験 | 2012 | 国語 |   85 | 浅田優     |
		//| 12年度1学期中間試験 | 2012 | 国語 |   51 | 安部孝則   |
		//| 12年度1学期中間試験 | 2012 | 国語 |   85 | 安藤由紀子 |
		//| 12年度1学期中間試験 | 2012 | 国語 |   61 | 関川雄太   |
		//| 12年度1学期中間試験 | 2012 | 国語 |   64 | 曽根麻里   |
		//| 12年度1学期中間試験 | 2012 | 国語 |   93 | 高田一義   |
		//| 12年度1学期中間試験 | 2012 | 国語 |   21 | 野田智     |
		//| 12年度1学期中間試験 | 2012 | 国語 |   21 | 野本綾乃   |
		//| 12年度1学期中間試験 | 2012 | 国語 |   56 | 浜本小百合 |
		//+---------------------+------+------+------+------------+
		//イメージ
		RankTableTest r = new RankTableTest();
		r.setTen(85);
		r.setKyu("?");
		r.setNamae("浅田優");
		list.add(r);
		RankTableTest r1 = new RankTableTest();
		r1.setTen(51);
		r1.setKyu("?");
		r1.setNamae("安部孝則");
		list.add(r1);
		RankTableTest r2 = new RankTableTest();
		r2.setTen(85);
		r2.setKyu("?");
		r2.setNamae("安藤由紀子");
		list.add(r2);
		RankTableTest r3 = new RankTableTest();
		r3.setTen(61);
		r3.setKyu("?");
		r3.setNamae("関川雄太");
		list.add(r3);

		// 点数でソート
		Collections.sort(list, new CompTokuten());

		// ループして順位をセット
		int no      = 0; // 順位
		int ten_old = -1; // １つ前の得点 マイナス得点なしの前提
		int ten_new = -1; // 今の得点
		for(RankTableTest ra : list) {
			ten_new = ra.getTen();
			if(ten_old != ten_new) {	// １回目は必ず異なるため最初の順位は１となる。
				no++;
			}
			ra.setRank(no);
			ten_old = ten_new;
		}

		// 順位データをセット
		request.setAttribute("tablelist", list);

		RequestDispatcher dispatch = request.getRequestDispatcher("ranking3.jsp");
		dispatch.forward(request, response);

		// TODO Auto-generated method stub
	}

	// RankTableTest内の点数で比較する関数
	public class CompTokuten implements Comparator<RankTableTest> {

	    //比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
	    public int compare(RankTableTest a, RankTableTest b) {
	        int ten1 = a.getTen();
	        int ten2 = b.getTen();

	        //点数の降順でソート
	        if (ten1 > ten2) {
	            return -1;

	        } else if (ten1 == ten2) {
	            return 0;

	        } else {
	            return 1;

	        }
	    }

	}
}
