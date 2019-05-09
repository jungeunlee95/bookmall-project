package test;

import java.util.List;

import bookmall.book.dao.BookDao;
import bookmall.book.vo.BookVo;
import bookmall.member.dao.MemberDao;
import bookmall.member.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
//		insert("이정은3", "010-6666-7777", "ccc", "ccc");
		getListTest();

	}

	public static void insert(String name, String phone, String email, String password) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setPassword(password);
		
		new MemberDao().insertMember(vo);
	}

	public static void getListTest() {
		List<MemberVo> list = new MemberDao().getMemberList();
		for (MemberVo vo : list) {
			System.out.println(vo);
		}
	}

}
