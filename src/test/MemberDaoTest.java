package test;

import java.util.List;

import bookmall.book.dao.BookDao;
import bookmall.book.vo.BookVo;
import bookmall.member.dao.MemberDao;
import bookmall.member.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {		
		insert("이정은1", "010-1111-2222", "aaa@aaa", "aaa");
		insert("이정은2", "010-2222-3333", "bbb@bbb", "bbb");
		insert("이정은3", "010-4444-5555", "ccc@ccc", "ccc");
		
		getListTest();

	}

	public static void insert(String name, String phone, String email, String password) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setPassword(password);
		
		new MemberDao().insertMember(vo);
		System.out.println("회원 등록이 완료되었습니다.");
	}

	public static void getListTest() {
		List<MemberVo> memberList = new MemberDao().getMemberList();
		System.out.println("\n----------------    [회원정보]    ---------------");
		for(MemberVo vo : memberList) {
			System.out.println("회원번호: " + vo.getNo() +", 이름 : " + vo.getName() + ", 전화번호 : " + vo.getPhone());
		}
		System.out.println("-----------------------------------------------");
	}

}
