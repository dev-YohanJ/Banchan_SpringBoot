package com.naver.myhome.task;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.banchan.myhome.domain.MailVo;

@Component
public class SendMail {
	private static final Logger logger = LoggerFactory.getLogger(SendMail.class);
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	
	public void sendMail(MailVo vo) {
		MimeMessagePreparator mp = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				/*
				 * MimeMessage : 이 클래스는 MIME 스타일 이메일 메시지를 나타냅니다.
				 * MiMe (영어: Multipurpose Internet Mail Extensions)는
				 * 	전자 우편을 위한 인터넷 표준 포맷입니다. 
				 * 
				 * MimeMessageHelper를 이용하면 첨부 파일이나 특수 문자 인코딩으로 작업할 때 전달된 MimeMessage를 채우는 데
				 * 편리합니다.
				 */
				
				// 두 번째 인자 true는 멀티 파트 메시지를 사용하곘다는 의미입니다.
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
				helper.setFrom(vo.getFrom());
				helper.setTo(vo.getTo());
				helper.setSubject(vo.getSubject());
				helper.setText(vo.getContent());
				
			}// prepare() end
			
		}; // new MimeMessagePreparator()
		
		mailSender.send(mp); // 메일 전송합니다.
		logger.info("메일 전송했습니다.");
	} // sendMail(MailVo vo)
} // class SendMail
