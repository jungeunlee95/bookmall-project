package bookmall.main;

import java.util.List;

import bookmall.book.dao.BookDao;
import bookmall.book.vo.BookVo;
import bookmall.cart.dao.CartDao;
import bookmall.cart.vo.CartVo;
import bookmall.category.dao.CategoryDao;
import bookmall.category.vo.CategoryVo;
import bookmall.member.dao.MemberDao;
import bookmall.member.vo.MemberVo;
import bookmall.order.dao.OrderDao;
import bookmall.order.vo.OrderBookVo;
import bookmall.order.vo.OrderVo;

public class MainApp {
	public static List<BookVo> bookList = new BookDao().getBookList();
	public static List<MemberVo> memberList = new MemberDao().getMemberList();
	public static List<CategoryVo> categoryList = new CategoryDao().getCategoryList();

	// 1번 사용자의 장바구니
	public static Long cartList = new CartDao().getAllCartList();
	// 모든 주문 정보
	public static List<OrderVo> orderList = new OrderDao().getOrderList();
	// 총 주문한 책의 수
	public static List<OrderBookVo> orderBooklist = new OrderDao().getOrderBookList();

	public static void main(String[] args) {
		// 기본 데이터 넣기
		insertinfo();
		
		// 회원이 장바구니에 담기
		addBookToCart();
		
		// 2번 회원이 주문을 끝마침
		finishOrder();
		
		Execute.start();
	}
	
	private static void insertinfo( ) {
		// 카데고리 넣기
		new CategoryDao().insertCategory(new CategoryVo("인문"));
		new CategoryDao().insertCategory(new CategoryVo("정치/사회"));
		new CategoryDao().insertCategory(new CategoryVo("컴퓨터/IT"));
		new CategoryDao().insertCategory(new CategoryVo("가정/육아"));
		
		// 책 데이터 넣기
		new BookDao().insertBook(new BookVo("마음으로부터 일곱 발자국", 14400L, 102L, 1L));
		new BookDao().insertBook(new BookVo("나를 지키는 노동법", 10350L, 500L, 2L));
		new BookDao().insertBook(new BookVo("자바 최적화(Optimizing Java)", 35100L, 100L, 1L));
		new BookDao().insertBook(new BookVo("아이를 위한 하루 한 줄 인문학", 13050L, 78L, 4L));

		// 회원 데이터 넣기
		new MemberDao().insertMember(new MemberVo("이정은1", "010-1111-2222", "aaa@aaa", "aaa"));
		new MemberDao().insertMember(new MemberVo("이정은2", "010-2222-3333", "bbb@bbb", "bbb"));
		new MemberDao().insertMember(new MemberVo("이정은3", "010-4444-5555", "ccc@ccc", "ccc"));
		
	}	
	
	private static void addBookToCart( ) {
		// 1번회원이 책 2권을 총 3권담음                               책     회원    수량
		new CartDao().insertCart(new CartVo(1L, 1L, 2L));
		new CartDao().insertCart(new CartVo(2L, 1L, 1L));
//		
//		// 2번회원이 30권 담음
		new CartDao().insertCart(new CartVo(3L, 2L, 30L));
		new CartDao().insertCart(new CartVo(2L, 2L, 2L));
		
	}
	
	private static void finishOrder( ) {
		
		//2번 회원이 30권 주문함!
		Long memberNo = 2L;
		String address = "경기도 용인시 수지구 11번지 ";
		// 주문 정보 등록
		new OrderDao().insertOrder(new OrderVo(memberNo, address));
		Long lastPK = new OrderDao().lastPK(memberNo);
		
		// 장바구니에 있던 책 목록 등록
		List<CartVo> cartList = new CartDao().getCartList(memberNo);
		new OrderDao().insertBookOrder(cartList, memberNo, lastPK);
		
		// 책 재고 감소
		new OrderDao().reduceBookAmount(cartList);
		
		// 장바구니 정보 삭제
		new OrderDao().deleteCart(memberNo);
		
	}
}
