package test;

import java.util.List;

import bookmall.book.dao.BookDao;
import bookmall.book.vo.BookVo;
import bookmall.cart.dao.CartDao;
import bookmall.cart.vo.CartVo;
import bookmall.member.dao.MemberDao;
import bookmall.member.vo.MemberVo;

public class CartDaoTest {

	public static void main(String[] args) {
		// 1번회원이 책 2권을 총 3권담음      
		//   책번호  회원번호  수량
		insert(1L, 1L, 2L);
		insert(2L, 1L, 1L);

		// 2번회원이 30권 담음
		insert(3L, 2L, 30L);
		insert(2L, 2L, 2L);
		
		getListTest(1L);
		getListTest(2L);
		getListTest(3L);

	}

	public static void insert(Long bookNo, Long memberNo, Long amount) {
		CartVo vo = new CartVo();
		vo.setBookNo(bookNo);
		vo.setMemberNo(memberNo);
		vo.setAmount(amount);
		
		
		new CartDao().insertCart(vo);
		
		System.out.println("장바구니 담기가 완료되었습니다.");
	}

	public static void getListTest(Long memberNo) {
		List<CartVo> list = new CartDao().getCartList(memberNo);
		System.out.println("\n--------------------  ["+memberNo +"번님의 장바구니]   ------------------");
		if(list.size()==0) {
			System.out.println("\t\t 장바구니가 비어있습니다.");
		}
		for (CartVo vo : list) {
			System.out.println("책제목 : " + vo.getBookTitle() + ", 수량 : " + vo.getAmount() + ", 가격 : " + vo.getTotalPrice());
		}
		System.out.println("---------------------------------------------------------");
		
	}

}
