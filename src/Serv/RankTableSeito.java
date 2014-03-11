package Serv;

public class RankTableSeito {
	private int nendo;		// 試験年度
	private String tname;	// 試験名
	private int[] ten;		// 総合点、教科点
	private int[] rank;		// 総合順位、教科順位

	public int getNendo() {
		return nendo;
	}
	public void setNendo(int nendo) {
		this.nendo = nendo;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int[] getTen() {
		return ten;
	}
	public void setTen(int[] ten) {
		this.ten = ten;
	}
	public int[] getRank() {
		return rank;
	}
	public void setRank(int[] rank) {
		this.rank = rank;
	}


}
