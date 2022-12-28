package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {

	// 회원 정보 관리 기능을 구현한 MemberDao를 필드로 정의
	@Autowired							//<- 스프링이 자동으로 의존 객체 주입
	private MemberDao memberDao;

	/*	의존 객체 주입을 위해서 만들었던 setter 메서드는 더이상 필요하지 않음
	// setter 메서드를 통해서 의존 객체를 주입
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	*/

	// 패스워드를 변경하는 메서드를 정의
	public void changePassword(String email, String oldPassword, String newPassword) {
		// 매개변수로 전달된 이메일로 등록된 사용자를 확인
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			throw new MemberNotFoundException();
		}

		// 멤버의 패스워드를 변경
		member.changePassword(oldPassword, newPassword);

		// 변경된 멤버 정보를 멤버 정보를 관리하는 곳에 반영
		memberDao.update(member);
	}
}
