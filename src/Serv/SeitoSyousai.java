package Serv;

public class SeitoSyousai {
	private int sdid;			// 生徒詳細ID
	private int sid;			//*生徒ID
	private int nen;			// 学年
	private String kyu;			// 学級
	private int no;				// 出席番号
	private int delete_flag;	// 削除フラグ

	public SeitoSyousai() {

	}

	public int getSdid() {
		return sdid;
	}

	public void setSdid(int sdid) {
		this.sdid = sdid;
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

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}


}
