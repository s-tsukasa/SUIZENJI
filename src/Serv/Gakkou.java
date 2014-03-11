package Serv;

public class Gakkou {
	private String namae;	// 学校名
	private String nendo;	// 現在の年度

	public Gakkou() {
		namae = "水前寺中学校";
		nendo = "2014";
	}

	public String getNamae() {
		return namae;
	}

	public void setNamae(String namae) {
		this.namae = namae;
	}

	public String getNendo() {
		return nendo;
	}

	public void setNendo(String nendo) {
		this.nendo = nendo;
	}

}
