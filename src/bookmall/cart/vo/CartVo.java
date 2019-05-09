package bookmall.cart.vo;

public class CartVo {

	private Long no;
	private Long bookNo;
	private Long memberNo;
	private Long amount;

	private String bookTitle;
	private String memberName;
	private Long totalPrice;

	public CartVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CartVo(Long bookNo, Long memberNo, Long amount) {
		super();
		this.bookNo = bookNo;
		this.memberNo = memberNo;
		this.amount = amount;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getBookNo() {
		return bookNo;
	}

	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}

	public Long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "CartVo [no=" + no + ", bookNo=" + bookNo + ", memberNo=" + memberNo + ", amount=" + amount
				+ ", bookTitle=" + bookTitle + ", memberName=" + memberName + ", totalPrice=" + totalPrice + "]";
	}



}
