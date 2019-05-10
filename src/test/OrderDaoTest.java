package test;

import java.util.List;

import bookmall.cart.dao.CartDao;
import bookmall.cart.vo.CartVo;
import bookmall.order.dao.OrderDao;
import bookmall.order.vo.OrderBookVo;
import bookmall.order.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		// 2번 회원이 책 주문함!
		Long memberNo = 2L;
		String address = "경기도 용인시 수지구 11번지 ";
		
		insert(memberNo, address);
//		getListTest();
		getBookListTest();

	}

	public static void insert(Long memberNo, String address) {
		// 주문 정보 등록
		Long lastPK = new OrderDao().insertOrder(new OrderVo(memberNo, address)).getLastPK();
		
		System.out.println("주문 정보 등록이 완료되었습니다.");
		
		// 장바구니에 있던 책 목록 등록
		List<CartVo> cartList = new CartDao().getCartList(memberNo);
		new OrderDao().insertBookOrder(cartList, lastPK);
		
		System.out.println("장바구니 책 주문으로 가져오기 완료");
		
		// 책 재고 감소
		new OrderDao().reduceBookAmount(cartList);
		
		System.out.println("책 재고 감소 완료");
		
		// 장바구니 정보 삭제
		new OrderDao().deleteCart(memberNo);
		System.out.println("장바구니 비우기 완료");
	}

	public static void getListTest() {
		List<OrderVo> list = new OrderDao().getOrderList();
		for (OrderVo vo : list) {
			System.out.println(vo);
		}
	}
	
	public static void getBookListTest() {
		// 모든 주분 정보
		List<OrderVo> orderList = new OrderDao().getOrderList();
		System.out.println("\n-------------------------------    [모든 주문 정보]    ------------------------------");
		for(OrderVo vo : orderList) {
			System.out.println("주문번호: " + vo.getOrderNo() +", 주문자 : " + vo.getMemberName() + ", 주소 : " + vo.getAddress() + ", 총 가격 : " + vo.getTotalPrice());
		}
		System.out.println("---------------------------------------------------------------------------------");
		
		// 모든 주문 당한 책 정보
		List<OrderBookVo> orderBooklist = new OrderDao().getOrderBookList();
		System.out.println("\n--------------------------    [모든 주문 책 정보]    -------------------------");
		for(OrderBookVo vo : orderBooklist) {
			System.out.println("책 제목 : " + vo.getTitle() + ", 총 주문 수량 : " + vo.getCount());
		}
		System.out.println("-----------------------------------------------------------------------");
	}

}
