package Serv;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
			list.add(new String("－"));
			list.add(new String("A"));
			list.add(new String("B"));
			list.add(new String("C"));
		}
		else {
			SeisekiKanriDB db = new SeisekiKanriDB();
			if(str.equals("test")) {
				ArrayList<Test>   dbL = db.getTestList();
				//list.add(new String("－"));
				for(Test l : dbL) {
					list.add(l.getTnamae());
				}
			}
			else if(str.equals("ka")) {
				ArrayList<Kyouka> dbL = db.getKyoukaList();
				//list.add(new String("－"));
				list.add(new String("合計"));
				for(Kyouka l : dbL) {
					list.add(l.getKa());
				}
			}
			else {
				// none
			}
			db.close();
		}

		return list;
	}

	public ArrayList<RankTableTest> Ranking() {
		ArrayList<RankTableTest> list = new ArrayList<RankTableTest>();
		// 総得点、または教科点でソートし、
		// RankTableTestクラスを作成

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
		r.setNamae("浅田優2");
		list.add(r);
		RankTableTest r1 = new RankTableTest();
		r1.setTen(51);
		r1.setKyu("?");
		r1.setNamae("安部孝則2");
		list.add(r1);
		RankTableTest r2 = new RankTableTest();
		r2.setTen(85);
		r2.setKyu("?");
		r2.setNamae("安藤由紀子2");
		list.add(r2);
		RankTableTest r3 = new RankTableTest();
		r3.setTen(61);
		r3.setKyu("?");
		r3.setNamae("関川雄太2");
		list.add(r3);

		// 点数でソート
		Collections.sort(list, new CompTokuten());

		// ループして順位をセット
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
