package Serv;

public class TestSyousai {
	private int tdid;			// 試験詳細ID
	private int tid;			//*試験ID
	private int kid;			//*教科ID
	private int delete_flag;	// 削除フラグ

	public TestSyousai() {

	}

	public int getTdid() {
		return tdid;
	}

	public void setTdid(int tdid) {
		this.tdid = tdid;
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

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}


}
