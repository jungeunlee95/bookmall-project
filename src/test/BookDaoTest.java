package test;

import java.util.List;

import bookmall.book.dao.BookDao;
import bookmall.book.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		insert("마음으로부터 일곱 발자국", 14400L, 102L, 1L);
		insert("나를 지키는 노동법", 10350L, 500L, 2L);
		insert("자바 최적화(Optimizing Java)", 35100L, 100L, 1L);
		insert("아이를 위한 하루 한 줄 인문학", 13050L, 78L, 4L);
		getListTest();

	}
	
	public static void insert(String title, Long price, Long stock, Long categoryNo) {
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setStock(stock);
		vo.setCategoryNo(categoryNo);
		new BookDao().insertBook(vo);
		System.out.println("책 등록이 완료되었습니다.");
	}
	
	public static void getListTest() {
		List<BookVo> bookList = new BookDao().getBookList();
		System.out.println("\n---------------------    [ 책 목록 ]    --------------------");
		for(BookVo vo : bookList) {
			System.out.println("책번호: " + vo.getNo() +", 제목 : " + vo.getTitle() + ", 가격 : " + vo.getPrice() + ", 재고 : " + vo.getStock());
		}
		System.out.println("---------------------------------------------------------");
	}

}
