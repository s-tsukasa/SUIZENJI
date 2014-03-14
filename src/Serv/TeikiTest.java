package Serv;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;

public class TeikiTest {
	private int nen;		// 学年
	private String tname;	// 試験名
	private int nendo;		// 試験年度
	private Timestamp hi;	// 試験日
	private int kikan;		// 試験期間

	// 追加
	private float avg;	// 平均点
	private String kyu;	// 組
	private String ka;	// 教科
	private int tid;	// 試験ID
	private int kid;	// 教科ID


	public TeikiTest() {
		// 未設定
	}

	public int getNen() {
		return nen;
	}

	public void setNen(int nen) {
		this.nen = nen;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getNendo() {
		return nendo;
	}

	public void setNendo(int nendo) {
		this.nendo = nendo;
	}

	public Timestamp getHi() {
		return hi;
	}

	public void setHi(Timestamp hi) {
		this.hi = hi;
	}

	public int getKikan() {
		return kikan;
	}

	public void setKikan(int kikan) {
		this.kikan = kikan;
	}

	//-------------------------------------------------
	public float getAvg() {
		return avg;
	}

	public void setAvg(int avg) {
		this.avg = avg;
	}

	public String getKyu() {
		return kyu;
	}

	public void setKyu(String kyu) {
		this.kyu = kyu;
	}

	public String getKa() {
		return ka;
	}

	public void setKa(String ka) {
		this.ka = ka;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getKid() {
		return kid;
	}

	public void setKid(int kid) {
		this.kid = kid;
	}

	public void setAvg(float avg) {
		this.avg = avg;
	}

	//-------------------------------------------------
//	public void testList() {
//
//	}
//	public int tenAvg() {
//		int avg = 0;
//		return avg;
//	}
	public void setData(String tname, int nen, String kyu, String ka) {
		this.tname = tname;
		this.nen   = nen;
		this.kyu   = kyu;
		this.ka    = ka;
	}

	public ArrayList<String> getList(String str) {
		ArrayList<String> list = new ArrayList<String>();
		if(str.equals("nen")) {
			list.add(new String("1"));
			list.add(new String("2"));
			list.add(new String("3"));
		}
		else if (str.equals("kyu")) {
			list.add(new String("-"));	// 全クラス分に対応！
			list.add(new String("A"));
			list.add(new String("B"));
			list.add(new String("C"));
		}
		else {
			// non
		}
		return list;
	}
	public ArrayList<Test> getTest() {
		SeisekiKanriDB db = new SeisekiKanriDB();
		ArrayList<Test>   list = db.getTestList();
		db.close();

		// 追加データ分　　不要！
		//Test t = new Test();
		//t.setTid(0);
		//t.setTnamae("－");
		//list.add(t);

		return list;
	}

	public ArrayList<Kyouka> getKyouka() {
		SeisekiKanriDB db = new SeisekiKanriDB();
		ArrayList<Kyouka> list = db.getKyoukaList();
		db.close();

		// 追加データ分
		Kyouka k = new Kyouka();
		k.setKid(0);
		k.setKa("合計");		// 総合得点に対応
		// list.add(k);
		list.add(0, k);

		return list;
	}

	public ArrayList<RankTableTest> Ranking() {
		SeisekiKanriDB db = new SeisekiKanriDB();
		ArrayList<TokutenTbl> tbl = new ArrayList<TokutenTbl>();

		if(kyu.equals("-")) {
			if(kid == 0) {
				// 試験と学年（学年全体で、総合点）
				tbl = db.getTokutenTblList(nen, tid);
			}
			else {
				// 試験と学年と教科（学年全体で、教科点）
				tbl = db.getTokutenTblList(nen, tid, kid);
			}
		}
		else {
			if(kid == 0) {
				// 試験と学年とクラス（クラス全体で、総合点）
				tbl = db.getTokutenTblList(nen, kyu, tid);
			}
			else {
				// 試験と学年とクラスと教科（クラス全体で、教科点）
				tbl = db.getTokutenTblList(nen, kyu, tid, kid);
			}
		}
		db.close();

		ArrayList<RankTableTest> list = new ArrayList<RankTableTest>();
		for(TokutenTbl tL : tbl) {
//			System.out.println(tL.getTen());

			// ランキング表示用を作成
			RankTableTest r = new RankTableTest();
			r.setTen(tL.getTen());		// 点数
			r.setKyu(tL.getKyu());		// 学級
			r.setNamae(tL.getNamae());	// 生徒名
			list.add(r);
		}


		// 点数でソート  DB入手の際にソート済み！！！
		// Collections.sort(list, new CompTokuten());

		// ループして順位をセット、点数合計
		int no      = 0;	// 順位
		int ten_old = -1;	// １つ前の得点 マイナス得点なしの前提
		int ten_new = -1;	// 今の得点
		int ten_all = 0;	// すべての得点合計
		int num     = 0;	// 得点の数
		for(RankTableTest ra : list) {
			num++;
			ten_new = ra.getTen();	// 得点の取得
			ten_all += ten_new;
			if(ten_old != ten_new) {	// １回目は必ず異なるため最初の順位は１となる。
				no++;
			}
			ra.setRank(no);
			ten_old = ten_new;
		}

		// 平均点計算
		this.avg = ten_all / num;

		return list;
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
