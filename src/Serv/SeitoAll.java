package Serv;

public class SeitoAll {
	private int sid;			// 生徒ID
	private int nen;			// 入学年度
	private String namae;		// 生徒名
	private int delete_flag;	// 削除フラグ
	private int gakunen ;		//seitosyousai のnen
	private String kyu;			//学級
	private int no;				//出席番号
// gitのテストで、コメント追加

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public SeitoAll() {

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

	public String getNamae() {
		return namae;
	}

	public void setNamae(String namae) {
		this.namae = namae;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}
}
