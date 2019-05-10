package bookmall.order.vo;

public class OrderVo {

	private Long no;
	private String orderNo;
	private String address;
	private Long totalPrice;
	private String date;

	private Long memberNo;
	private Long lastPK;

	private String memberName;

	public OrderVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderVo(Long memberNo, String address) {
		super();
		this.memberNo = memberNo;
		this.address = address;
		
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Long getLastPK() {
		return lastPK;
	}

	public void setLastPK(Long lastPK) {
		this.lastPK = lastPK;
	}

	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", orderNo=" + orderNo + ", address=" + address + ", totalPrice=" + totalPrice
				+ ", date=" + date + ", memberNo=" + memberNo + ", lastPK=" + lastPK + ", memberName=" + memberName
				+ "]";
	}



}
