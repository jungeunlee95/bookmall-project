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
		//insert(3L,2L,1L);
//		new CartDao().insertCart(new CartVo(3L, 2L, 30L));
		getListTest();

	}

	public static void insert(Long bookNo, Long memberNo, Long amount) {
		CartVo vo = new CartVo();
		vo.setBookNo(bookNo);
		vo.setMemberNo(memberNo);
		vo.setAmount(amount);
		
		
		new CartDao().insertCart(vo);
	}

	public static void getListTest() {
		List<CartVo> list = new CartDao().getCartList(2L);
		for (CartVo vo : list) {
			System.out.println(vo);
		}
	}

}
