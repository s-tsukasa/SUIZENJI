package Serv;

import java.sql.Timestamp;
import java.util.ArrayList;

public class TeikiTest {
	private int nen;		// 学年
	private String tname;	// 試験名
	private int nendo;		// 試験年度
	private Timestamp hi;	// 試験日
	private int kikan;		// 試験期間

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
	public void testList() {

	}

	public int tenAvg() {
		int avg = 0;
		return avg;
	}

	public ArrayList<RankTableTest> Ranking() {
		ArrayList<RankTableTest> ra = new ArrayList<RankTableTest>();
		// 総得点、または教科点でソートし、
		// RankTableTestクラスを作成
		return ra;
	}

}
