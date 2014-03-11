package Serv;

public class Seito {
	private int sid;			// 生徒ID
	private int nen;			// 入学年度
	private String namae;		// 生徒名
	private int delete_flag;	// 削除フラグ
// gitのテストで、コメント追加

	public Seito() {

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
