package test;

import java.util.List;

import bookmall.book.dao.BookDao;
import bookmall.book.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
//		insert("테스트책제목", 30000L, 10L, 4L);
		getListTest();

	}
	
	public static void insert(String title, Long price, Long stock, Long categoryNo) {
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setStock(stock);
		vo.setCategoryNo(categoryNo);
		new BookDao().insertBook(vo);
	}
	
	public static void getListTest() {
		List<BookVo> list = new BookDao().getBookList();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}

}
