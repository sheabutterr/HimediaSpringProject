package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


//회원 정보를 조회해서 	=> MemberDao
//출력하는 기능을 구현 	=> MemberPrinter
public class MemberInfoPrinter {

	// 의존 객체를 필드로 추가
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	@Autowired
	// setter 메서드를 통해서 의존 객체를 주입
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	@Autowired
	@Qualifier("printer")       //  ⇐ 사용할 빈을 한정자 이름으로 지정
	public void setMemberPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	// 이메일과 일치하는 회원의 정보를 출력하는 메서드 
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			System.out.println("일치하는 회원 정보가 없습니다.");
			return;
		}
		printer.print(member);		
	}	
}
