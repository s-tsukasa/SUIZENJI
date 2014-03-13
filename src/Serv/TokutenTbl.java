package Serv;

public class TokutenTbl {
	private int sid;			// 生徒ID
	private int nen;			// 入学年度
	private int gakunen ;		//seitosyousai のnen
	private String kyu;			//学級
	private int no;
	private int tid;
	private int kid;
	private int ten;
	private String namae;

	public String getNamae() {
		return namae;
	}
	public void setNamae(String namae) {
		this.namae = namae;
	}
	public int getTen() {
		return ten;
	}
	public void setTen(int ten) {
		this.ten = ten;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getNen() {
		return nen;
	}
	public void setNen(int nen) {
		this.nen = nen;
	}
	public int getGakunen() {
		return gakunen;
	}
	public void setGakunen(int gakunen) {
		this.gakunen = gakunen;
	}
	public String getKyu() {
		return kyu;
	}
	public void setKyu(String kyu) {
		this.kyu = kyu;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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

}
