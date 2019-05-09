package test;

import java.util.List;
import bookmall.category.dao.CategoryDao;
import bookmall.category.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		insert("요리");
		getListTest();

	}

	public static void insert(String name) {
		CategoryVo vo = new CategoryVo();
		vo.setName(name);
		
		new CategoryDao().insertCategory(vo);
	}

	public static void getListTest() {
		List<CategoryVo> list = new CategoryDao().getCategoryList();
		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

}
