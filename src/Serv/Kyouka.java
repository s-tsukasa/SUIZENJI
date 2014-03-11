package Serv;

public class Kyouka {
	private int kid;			// 教科ID
	private String ka;			// 科目名
	private int delete_flag;	// 削除フラグ

	public Kyouka() {

	}

	public int getKid() {
		return kid;
	}

	public void setKid(int kid) {
		this.kid = kid;
	}

	public String getKa() {
		return ka;
	}

	public void setKa(String ka) {
		this.ka = ka;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}


}
