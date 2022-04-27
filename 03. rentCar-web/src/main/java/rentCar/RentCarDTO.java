package rentCar;

public class RentCarDTO {
	private int no, category, price, use_people;
	private String name, company, image, info;
	
	public RentCarDTO(int no, String name, int category, 
			int price, int use_people, 
			String company, String image, String info) {
		this.no = no;
		this.name = name;
		this.category = category;
		this.price = price;
		this.use_people = use_people;
		this.company = company;
		this.image = image;
		this.info = info;
	}

	public int getNo() {
		return no;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getUse_people() {
		return use_people;
	}

	public void setUse_people(int use_people) {
		this.use_people = use_people;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
