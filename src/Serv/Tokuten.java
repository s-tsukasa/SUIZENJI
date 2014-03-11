package Serv;

public class Tokuten {
	private int tenid;			// 得点ID
	private int sid;			//*生徒ID
	private int tdid;			//*試験詳細ID
	private int ten;			// 得点
	private int delete_flag;	// 削除フラグ

	public Tokuten() {

	}

	public int getTenid() {
		return tenid;
	}

	public void setTenid(int tenid) {
		this.tenid = tenid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getTdid() {
		return tdid;
	}

	public void setTdid(int tdid) {
		this.tdid = tdid;
	}

	public int getTen() {
		return ten;
	}

	public void setTen(int ten) {
		this.ten = ten;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}


}
