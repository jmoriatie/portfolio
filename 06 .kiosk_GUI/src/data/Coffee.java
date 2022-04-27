package data;

public class Coffee {
	private String[] baseDrink = {
			"아메리카노", "스페셜아메리카노", "헤이즐넛아메리카노", "유자아메리카노",
			"카푸치노", "카페라떼", "헤이즐넛라떼", "바닐라라떼",
			"크리미라떼", "헤이즐넛크리미라떼","카페모카", "카라멜마키야또",
			"에스프레소", "더치커피", "더치시나몬라떼", "더치코코넛라뗴"};
	private String[] basePrice = {
			"1500", "2500", "2000", "2500", 
			"2500", "2500", "3000", "3000",
			"3000", "3500","3500", "3500",
			"1500", "2500", "3000", "3000"};

	public String[] getBaseDrink() {
		return baseDrink;
	}
	
	public String[] getBasePrice() {
		return basePrice;
	}

	//	public void setBaseCoffee(String[] baseCoffee) {
//		this.baseCoffee = baseCoffee;
//	}
//	public void setBasePrice(String[] basePrice) {
//		this.basePrice = basePrice;
//	}
	
	
}
