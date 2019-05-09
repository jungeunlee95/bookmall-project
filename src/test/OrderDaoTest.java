package test;

import java.util.List;

import bookmall.cart.dao.CartDao;
import bookmall.cart.vo.CartVo;
import bookmall.order.dao.OrderDao;
import bookmall.order.vo.OrderBookVo;
import bookmall.order.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
//		insert(1L);
//		getListTest();
		getBookListTest();

	}

	public static void insert(Long memberNo) {
		OrderVo vo = new OrderVo();
		vo.setMemberNo(memberNo);
		
		// 주문 정보 등록
		new OrderDao().insertOrder(vo);
		Long lastPK = new OrderDao().lastPK(memberNo);
		
		// 어떤 책 등록했는지
		List<CartVo> cartList = new CartDao().getCartList(memberNo);
		new OrderDao().insertBookOrder(cartList, memberNo, lastPK);
		
		// 장바구니 정보 삭제
		new OrderDao().deleteCart(memberNo);
	}

	public static void getListTest() {
		List<OrderVo> list = new OrderDao().getOrderList();
		for (OrderVo vo : list) {
			System.out.println(vo);
		}
	}
	
	public static void getBookListTest() {
		List<OrderBookVo> list = new OrderDao().getOrderBookList();
		for (OrderBookVo vo : list) {
			System.out.println(vo);
		}
	}

}
