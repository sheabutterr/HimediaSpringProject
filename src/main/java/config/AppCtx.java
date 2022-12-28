package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.MemberSummaryPrinter;
import spring.VersionPrinter;

//스프링 설정 클래스를 의미
@Configuration
public class AppCtx {

	// 해당 메서드가 생성한 객체를 스프링 빈으로 설정
	// 메서드 이름을 빈 객체의 이름으로 사용
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}

	@Bean
	public MemberRegisterService memberRegSvc() {
		// 생성자를 이용해 의존 객체를 주입
		//return new MemberRegisterService(memberDao());
		return new MemberRegisterService();
	}

	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		
		// @Bean 설정 메서드에서 의존 객체를 주입하는 코드 삭제 가능
		// setter 메서드를 이용해 의존 객체를 주입
		//pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}

	@Bean
	@Qualifier("printer")						// 해당 빈의 한정값으로 printer를 지정
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}

	@Bean
	public MemberListPrinter listPrinter() {
		//return new MemberListPrinter(memberDao(), memberPrinter());
		return new MemberListPrinter();
	}

	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		/*	의존 객체(MemberDao)를 주입하기 위해 호출했던 setter 메서드를
		 * 	더 이상 호출하지 않아도 됨
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setMemberPrinter(memberPrinter());
		*/
		return infoPrinter;
	}

	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(1);
		return versionPrinter;
	}

}
