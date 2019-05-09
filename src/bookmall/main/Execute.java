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

public class Execute {
	public static List<BookVo> bookList = new BookDao().getBookList();
	public static List<MemberVo> memberList = new MemberDao().getMemberList();
	public static List<CategoryVo> categoryList = new CategoryDao().getCategoryList();

	// 1번 사용자의 장바구니
	public static Long cartList = new CartDao().getAllCartList();
	// 모든 주문 정보
	public static List<OrderVo> orderList = new OrderDao().getOrderList();
	// 총 주문한 책의 수
	public static List<OrderBookVo> orderBooklist = new OrderDao().getOrderBookList();

	
	public static void start( ) {
		// 기본 데이터 넣기
		displayInfo();
		
		// 회원목록
		showMember();
//		
//		// 카테고리 목록
		showCategory();
//
//		// 책 목록
		showBook();

//		// 특정 회원 장바구니 조회
		showCart(2L);
	
	
//		// 모둔 주문 정보
		showAllOrder();
//		// 모든 주문당한 책 정보
		showAllOrderedBookList();
		
	}
	
	
	private static void showAllOrderedBookList() {
		System.out.println("\n--------------------------    [모든 주문 책 정보]    -------------------------");
		for(OrderBookVo vo : orderBooklist) {
			System.out.println("책 제목 : " + vo.getTitle() + ", 총 주문 수량 : " + vo.getCount());
		}
		System.out.println("-----------------------------------------------------------------------");		
	}
	
	private static void showAllOrder() {
		System.out.println("\n-------------------------------    [모든 주문 정보]    ------------------------------");
		for(OrderVo vo : orderList) {
			System.out.println("주문번호: " + vo.getOrderNo() +", 주문자 : " + vo.getMemberName() + ", 주소 : " + vo.getAddress() + ", 총 가격 : " + vo.getTotalPrice());
		}
		System.out.println("---------------------------------------------------------------------------------");
	}
	private static void showCart(Long memberNo) {
		List<CartVo> list = new CartDao().getCartList(memberNo);
		
		System.out.println("\n--------------------  ["+memberNo +"번님의 장바구니]   ------------------");
		for (CartVo vo : list) {
			System.out.println("책제목 : " + vo.getBookTitle() + ", 수량 : " + vo.getAmount() + ", 가격 : " + vo.getTotalPrice());
		}
		System.out.println("---------------------------------------------------------");
		
	}
	private static void showCategory() {
		System.out.println("\n----------------    [카테고리]    ---------------");
		for(CategoryVo vo : categoryList) {
			System.out.println("카테고리 번호: " + vo.getNo() +", 이름 : " + vo.getName());
		}
		System.out.println("-----------------------------------------------");
	}
	
	private static void showBook() {
		System.out.println("\n---------------------    [ 책 목록 ]    --------------------");
		for(BookVo vo : bookList) {
			System.out.println("책번호: " + vo.getNo() +", 제목 : " + vo.getTitle() + ", 가격 : " + vo.getPrice() + ", 재고 : " + vo.getStock());
		}
		System.out.println("---------------------------------------------------------");
	}
	
	private static void showMember() {
		System.out.println("\n----------------    [회원정보]    ---------------");
		for(MemberVo vo : memberList) {
			System.out.println("회원번호: " + vo.getNo() +", 이름 : " + vo.getName() + ", 전화번호 : " + vo.getPhone());
		}
		System.out.println("-----------------------------------------------");
	}
	
	
	private static void displayInfo( ) {
		int totalOrder = 0;
		for (OrderBookVo vo : orderBooklist) {
			totalOrder += (int)(long)vo.getCount();
		}
		
		System.out.println("\n--------------------------------------");
		System.out.println("\t 1. 회원 리스트 - "+ memberList.size() + "명");
		System.out.println("\t 2. 카테고리 리스트 - " + categoryList.size() + "개");
		System.out.println("\t 3. 상품 리스트  - " + bookList.size() + "권" );
		System.out.println("\t 4. 카트 리스트 -  총 " + cartList + "개의 상품");
		System.out.println("\t 5. 주문 리스트 - " + orderList.size() + "건의 주문");
		System.out.println("\t 6. 주문 도서 리스트 - "+ totalOrder + "권");
		System.out.println("--------------------------------------");

	}	
	


}
