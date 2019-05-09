package bookmall.cart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bookmall.cart.vo.CartVo;

public class CartDao {

	public int checkBook(Long memberNo, Long bookNo) {
		int result = 0;

		// 자원정리
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select count(*) " + 
						"  from cart " + 
						"  where member_no = ? " + 
						"  and book_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberNo);
			pstmt.setLong(2, bookNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = (int) rs.getLong(1);
			}

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	

	public boolean insertCart(CartVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			if (checkBook(vo.getMemberNo(), vo.getBookNo()) > 0) {

				String sql = " update cart " 
						+ "     set amount = amount+? " 
						+ "    where member_no = ? "
						+ "     and book_no = ?";
				pstmt = conn.prepareStatement(sql);
				

				pstmt.setLong(1, vo.getAmount());
				pstmt.setLong(2, vo.getMemberNo());
				pstmt.setLong(3, vo.getBookNo());

			} else {
				// 책번호, 고객번호, 수량
				String sql = " insert into cart values(null, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setLong(1, vo.getBookNo());
				pstmt.setLong(2, vo.getMemberNo());
				pstmt.setLong(3, vo.getAmount());
			}

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("ERROR! : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<CartVo> getCartList(Long memberNo) {
		List<CartVo> result = new ArrayList<CartVo>();

		// 자원정리
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select c.no, b.title, m.name, c.amount, m.no, b.no, b.price*c.amount  " + 
						"  from cart c, book b, member m  " + 
						"  where c.book_no = b.no  " + 
						"  and c.member_no = m.no " + 
						"  and member_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String bookTitle = rs.getString(2);
				String memberName = rs.getString(3);
				Long amount = rs.getLong(4);
				Long memberNo2 = rs.getLong(5);
				Long bookNo = rs.getLong(6);
				Long totalPrice = rs.getLong(7);

				CartVo vo = new CartVo();
				vo.setNo(no);
				vo.setBookTitle(bookTitle);
				vo.setMemberName(memberName);
				vo.setAmount(amount);
				vo.setMemberNo(memberNo2);
				vo.setBookNo(bookNo);
				vo.setTotalPrice(totalPrice);
				
				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public Long getAllCartList() {
		Long result = null;

		// 자원정리
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select count(*) from cart ";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getLong(1);

			}

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.1.52:3307/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} finally {

		}

		return conn;

	}

}
