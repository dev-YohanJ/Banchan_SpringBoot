package com.banchan.myhome.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.banchan.myhome.domain.Item;
import com.banchan.myhome.service.CommentService;
import com.banchan.myhome.service.ItemService;

@RestController
public class ItemController {
	
private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	private ItemService itemService;
	
	@Autowired
	public ItemController(ItemService itemService, CommentService commentService) {
		this.itemService=itemService;
	}
	
	//my.savefolder=c:/upload
	@Value("${my.savefolder}")
	private String saveFolder;
	
	
	private String fileDBName(String fileName, String saveFolder) {
		// 새로운 폴더 이름 : 오늘 년+월+일
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); //오늘 년도
		int month = c.get(Calendar.MONTH)+ 1; //오늘 월
		int date = c.get(Calendar.DATE); //오늘 일
		
		String homedir = saveFolder + "/" + year + "-" + month + "-" + date;
		logger.info(homedir);
		File path1 = new File(homedir);
		if (!(path1.exists())) {
			path1.mkdir(); //새로운 폴더를 생성
		}
		
		//난수를 구합니다.
		Random r = new Random();
		int random = r.nextInt(100000000);
		
		/* 확장자 구하기 시작 */
	
		int index = fileName.lastIndexOf(".");
		//문자열에서 특정 문자열의 위치 값(index)를 반환합니다.
		//indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
		//lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
		//(파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
		logger.info("index = " + index);
		
		String fileExtension = fileName.substring(index + 1);
		logger.info("fileExtension = " + fileExtension);
		/* 확장자 구하기 끝 */
	
		//새로운 파일명
		String refileName = "bbs" + year + month + date + random + "." + fileExtension;
		logger.info("refileName = " + refileName);
		
		//오라클 디비에 저장될 파일 명
		//String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		String fileDBName = File.separator + year + "-" + month + "-" + date + File.separator + refileName;
		logger.info("fileDBName = " + fileDBName);
		return fileDBName;
	}
	
	@PostMapping(value ="/product_new")
	public String add(Item item)throws Exception {
		
		logger.info(item.getName());
		logger.info(item.getDescription());
		logger.info(item.getAllergy());
		
		MultipartFile uploadfile = item.getUploadfile();
		
		if(uploadfile!=null && !uploadfile.isEmpty()) {
			String fileName = uploadfile.getOriginalFilename(); //원래 파일명
			item.setOriginal(fileName); //원래 파일명 저장
			
			//c:/upload 생성합니다.
			//이전에는 직접 폴더를 생성했다면 지금은 File의 mkdir()로 폴더를 생성합니다.
			logger.info(saveFolder);
			File file = new File(saveFolder);
			if(!file.exists()) {
				if(file.mkdir()) {
					logger.info("폴더 생성");
				} else {
					logger.info("폴더 생성 실패");
				}
			} else {
				logger.info("폴더존재");
			}
			
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBNave = " + fileDBName);
			
			// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile.transferTo(new File(saveFolder + fileDBName));
			
			//바뀐 파일명으로 저장
			item.setImage(fileDBName);				//테이블을 추가해야할지?
		}
		
		itemService.insertItem(item); //저장메서드 호출
		return "success";
	}
		
		
	
}