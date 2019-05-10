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
	public static void start( ) {
//		 기본 데이터 넣기
//		insertinfo();
		
		// 기본 데이터 확인
		displayInfo();
		
//		// 회원목록
//		showMember();
//	
//		// 카테고리 목록
//		showCategory();
//
//		// 책 목록
//		showBook();
//
// 		회원들이 장바구니에 책 담기
//		addBookToCart(2L, 1L, 3L);
//		
//		// 특정 회원 장바구니 조회
//		System.out.println("\n ★★★★★★★★★★★★★[ 각 회원의 장바구니 목록 ] ★★★★★★★★★★★★★");
//		showCart(1L);
//		showCart(2L);
//		showCart(3L);
//
//		//2번 회원이  주문함!
//		finishOrder(2L, "경기도 용인시 수지구 11번지 ");
//		System.out.println("\n ★★★★★★★★★★★★★★★★ 2번님이 주문을 하셨습니다 ★★★★★★★★★★★★★★★★");
//		
//	
//		// 모든 주문 정보
//		showAllOrder();
//		// 모든 주문당한 책 정보
//		showAllOrderedBookList();
//		
//		displayInfo();
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
	
	
	private static void addBookToCart(Long bookNo, Long memberNo, Long amount  ) {
		// 1번회원이 책 2권을 총 3권담음                               책     회원    수량
		new CartDao().insertCart(new CartVo(bookNo, memberNo, amount));
		
	}
	
	private static void showAllOrderedBookList() {
		List<OrderBookVo> orderBooklist = new OrderDao().getOrderBookList();
		System.out.println("\n--------------------------    [모든 주문 책 정보]    -------------------------");
		for(OrderBookVo vo : orderBooklist) {
			System.out.println("책 제목 : " + vo.getTitle() + ", 총 주문 수량 : " + vo.getCount());
		}
		System.out.println("-----------------------------------------------------------------------");		
	}
	
	private static void showAllOrder() {
		List<OrderVo> orderList = new OrderDao().getOrderList();
		System.out.println("\n-------------------------------    [모든 주문 정보]    ------------------------------");
		for(OrderVo vo : orderList) {
			System.out.println("주문번호: " + vo.getOrderNo() +", 주문자 : " + vo.getMemberName() + ", 주소 : " + vo.getAddress() + ", 총 가격 : " + vo.getTotalPrice());
		}
		System.out.println("---------------------------------------------------------------------------------");
	}
	
	private static void showCart(Long memberNo) {
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
	private static void showCategory() {
		List<CategoryVo> categoryList = new CategoryDao().getCategoryList();
		System.out.println("\n----------------    [카테고리]    ---------------");
		for(CategoryVo vo : categoryList) {
			System.out.println("카테고리 번호: " + vo.getNo() +", 이름 : " + vo.getName());
		}
		System.out.println("-----------------------------------------------");
	}
	
	private static void showBook() {
		List<BookVo> bookList = new BookDao().getBookList();
		System.out.println("\n---------------------    [ 책 목록 ]    --------------------");
		for(BookVo vo : bookList) {
			System.out.println("책번호: " + vo.getNo() +", 제목 : " + vo.getTitle() + ", 가격 : " + vo.getPrice() + ", 재고 : " + vo.getStock());
		}
		System.out.println("---------------------------------------------------------");
	}
	
	private static void showMember() {
		List<MemberVo> memberList = new MemberDao().getMemberList();
		System.out.println("\n----------------    [회원정보]    ---------------");
		for(MemberVo vo : memberList) {
			System.out.println("회원번호: " + vo.getNo() +", 이름 : " + vo.getName() + ", 전화번호 : " + vo.getPhone());
		}
		System.out.println("-----------------------------------------------");
	}
	
	
	private static void displayInfo( ) {
		List<BookVo> bookList = new BookDao().getBookList();
		List<MemberVo> memberList = new MemberDao().getMemberList();
		List<CategoryVo> categoryList = new CategoryDao().getCategoryList();

		// 1번 사용자의 장바구니
		Long cartList = new CartDao().getAllCartList();
		// 모든 주문 정보
		List<OrderVo> orderList = new OrderDao().getOrderList();
		// 총 주문한 책의 수
		List<OrderBookVo> orderBooklist = new OrderDao().getOrderBookList();
		
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
	

	private static void finishOrder(Long memberNo, String address) {
		// 주문 정보 등록
		Long lastPK = new OrderDao().insertOrder(new OrderVo(memberNo, address)).getLastPK();
		
		// 장바구니에 있던 책 목록 등록
		List<CartVo> cartList = new CartDao().getCartList(memberNo);
		new OrderDao().insertBookOrder(cartList, lastPK);
		
		// 책 재고 감소
		new OrderDao().reduceBookAmount(cartList);
		
		// 장바구니 정보 삭제
		new OrderDao().deleteCart(memberNo);
		
	}
}
