package Serv;

public class SumTokuten {
	int sum = 0;	// 合計得点
					// クラスの継承の例でprivateの記載がなかったため未記載

	public SumTokuten() {

	}

	public void sum(int[] ten) {
		for(int val : ten) {
			sum += val;
		}
	}
}
