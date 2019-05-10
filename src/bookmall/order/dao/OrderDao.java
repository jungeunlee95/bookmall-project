package bookmall.order.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.book.vo.BookVo;
import bookmall.cart.vo.CartVo;
import bookmall.order.vo.OrderBookVo;
import bookmall.order.vo.OrderVo;

public class OrderDao {

	public boolean reduceBookAmount(List<CartVo> cartList) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			for (CartVo vo : cartList) {
				String sql = " update book set stock=stock-? where no = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, vo.getAmount());
				pstmt.setLong(2, vo.getBookNo());

				int count = pstmt.executeUpdate();
				result = count == 1;
			}

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
	
	public boolean deleteCart(Long memberNo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			
			String sql = " delete from cart where member_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,memberNo);
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

	public boolean insertBookOrder(List<CartVo> cartList, Long lastPK) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			Long orderNo = lastPK;

			for (CartVo vo : cartList) {
				String sql = " insert into book_order " 
							+ " values(null, ?, ?, ?) ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, vo.getBookNo());
				pstmt.setLong(2, orderNo);
				pstmt.setLong(3, vo.getAmount());

				int count = pstmt.executeUpdate();
				result = count == 1;
			}

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

	public OrderVo insertOrder(OrderVo vo) {
		OrderVo result = new OrderVo();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " insert into orders(no, order_no, address, total_price, member_no)  "
					+ "      values(null, concat(DATE_FORMAT(now(),'%Y%m%d'), '-',  "
					+ "        lpad( ((select count(*) from orders ALIAS_FOR_SUBQUERY"
					+ " where DATE_FORMAT(today, '%y%m%d') = DATE_FORMAT(now(), '%y%m%d') )+1), '5', '0' )), "
					+ "       ?, " + "       (select sum(amount*price)\r\n" + 
							"  from cart c, book b " + 
							"  where c.book_no = b.no  and member_no = ?), ? )";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getAddress());
			pstmt.setLong(2, vo.getMemberNo());
			pstmt.setLong(3, vo.getMemberNo());
			pstmt.executeUpdate();
			
			
			sql = " select LAST_INSERT_ID() ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Long lastPK = null;
			while(rs.next()) {
				lastPK = rs.getLong(1);
			}
			result.setLastPK(lastPK);

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
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<OrderVo> getOrderList() {
		List<OrderVo> result = new ArrayList<OrderVo>();

		// 자원정리
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select o.no, o.order_no, o.address, total_price, DATE_FORMAT(o.today,'%Y-%m-%d'), m.name " + 
						"  from orders o, member m " + 
						"  where o.member_no = m.no ";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String orderNo = rs.getString(2);
				String address = rs.getString(3);
				Long totalPrice = rs.getLong(4);
				String date = rs.getString(5);
				String memberName = rs.getString(6);

				OrderVo vo = new OrderVo();
				vo.setNo(no);
				vo.setOrderNo(orderNo);
				vo.setAddress(address);
				vo.setTotalPrice(totalPrice);
				vo.setDate(date);
				vo.setMemberName(memberName);
				
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
	
	public List<OrderBookVo> getOrderBookList() {
		List<OrderBookVo> result = new ArrayList<OrderBookVo>();

		// 자원정리
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select b.no, b.title, sum(bo.amount) " + 
						"  from book_order bo, book b " + 
						"  where bo.book_no = b.no " + 
						"  group by bo.book_no ";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long count = rs.getLong(3);

				OrderBookVo vo = new OrderBookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setCount(count);
				
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
