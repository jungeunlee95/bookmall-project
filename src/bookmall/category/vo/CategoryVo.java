package bookmall.category.vo;

public class CategoryVo {
	private Long no;
	private String name;

	public CategoryVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CategoryVo(String name) {
		super();
		this.name = name;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + "]";
	}

}
