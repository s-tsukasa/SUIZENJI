package Serv;

import java.sql.Timestamp;

public class Test {
	private int tid;			// 試験ID
	private String tnamae;		// 試験名
	private Timestamp thi;		// 試験日
	private int kikan;			// 試験日数
	private int delete_flag;	// 削除フラグ

	public Test() {

	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTnamae() {
		return tnamae;
	}

	public void setTnamae(String tnamae) {
		this.tnamae = tnamae;
	}

	public Timestamp getThi() {
		return thi;
	}

	public void setThi(Timestamp thi) {
		this.thi = thi;
	}

	public int getKikan() {
		return kikan;
	}

	public void setKikan(int kikan) {
		this.kikan = kikan;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}


}
