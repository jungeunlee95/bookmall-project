package test;

import java.util.List;
import bookmall.category.dao.CategoryDao;
import bookmall.category.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		insert("인문");
		insert("정치/사회");
		insert("컴퓨터/IT");
		insert("가정/육아");
		getListTest();

	}

	public static void insert(String name) {
		CategoryVo vo = new CategoryVo();
		vo.setName(name);
		
		new CategoryDao().insertCategory(vo);
		System.out.println("카테고리 등록이 완료되었습니다.");
	}

	public static void getListTest() {
		List<CategoryVo> categoryList = new CategoryDao().getCategoryList();
		System.out.println("\n----------------    [카테고리]    ---------------");
		for(CategoryVo vo : categoryList) {
			System.out.println("카테고리 번호: " + vo.getNo() +", 이름 : " + vo.getName());
		}
		System.out.println("-----------------------------------------------");
	}

}
