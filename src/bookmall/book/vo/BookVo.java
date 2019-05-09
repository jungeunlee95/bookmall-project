package bookmall.book.vo;

public class BookVo {
	private Long no;
	private String title;
	private Long price;
	private Long stock;

	private Long categoryNo;
	private String categoryName;

	public BookVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookVo(String title, Long price, Long stock, Long categoryNo) {
		super();
		this.title = title;
		this.price = price;
		this.stock = stock;
		this.categoryNo = categoryNo;
		
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Long getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", price=" + price + ", stock=" + stock + ", categoryNo="
				+ categoryNo + ", categoryName=" + categoryName + "]";
	}

}
