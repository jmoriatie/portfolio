package data;

public class TeaNAde {
	public String[] baseDrink = {
			"허니레몬티", "허니유자티", "허니자몽티", "얼그레이",
			"국화차", "민트초코티", "캐모마일", "페퍼민트",
			"청포도에이드", "보이차","루이보스", "로즈힙",
			"히비스커스", "복숭아아이스티", "블루레몬에이드", "자몽에이드"};
	public String[] basePrice = {
			"3500", "3500", "3500", "2500", 
			"2500", "2500", "2500", "2500",
			"3500", "2500","2500", "2500",
			"2500", "3000", "3500", "3500"};

	public String[] getBaseDrink() {
		return baseDrink;
	}
	
	public String[] getBasePrice() {
		return basePrice;
	}


	// 세터 게터
}
