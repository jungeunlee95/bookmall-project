package bookmall.order.vo;

public class OrderBookVo {

	private Long no;
	private String title;
	private Long count;

	public OrderBookVo() {
		super();
		// TODO Auto-generated constructor stub
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

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "OrderBookVo [no=" + no + ", title=" + title + ", count=" + count + "]";
	}

}
