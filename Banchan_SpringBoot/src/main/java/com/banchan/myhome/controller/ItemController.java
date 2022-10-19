package com.banchan.myhome.controller;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.banchan.myhome.domain.Item;
import com.banchan.myhome.domain.Member;
import com.banchan.myhome.service.ItemService;
import com.banchan.myhome.service.MemberService;

@RestController
public class ItemController {
	
private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	private ItemService itemService;
	
	@Autowired
	public ItemController(ItemService itemService, MemberService memberservice) {
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
		logger.info("homedir:"+homedir);
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
    //String fileDBName = File.separator + year + "-" + month + "-" + date + File.separator + refileName;
    String fileDBName = year + "-" + month + "-" + date + "/" + refileName;
    logger.info("fileDBName = " + fileDBName);
    return fileDBName;
	}
	
	
	//상품등록
	@PostMapping(value ="/product_new")
	public String add(Item item, String id)throws Exception {
			
		
		MultipartFile[] uploadfile = item.getUploadfile();
		logger.info("갯수 : " + uploadfile.length);
		
		String uploadfilenames = "";
		
		if (uploadfile!=null) {
		//c:/upload 생성합니다.
				//이전에는 직접 폴더를 생성했다면 지금은 File의 mkdir()로 폴더를 생성합니다.
				logger.info("saveFolder:"+saveFolder);
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
				
			for(MultipartFile loadfile : uploadfile ) {
			String fileName = loadfile.getOriginalFilename(); //원래 파일명
			item.setOriginal(fileName); //원래 파일명 저장
			
			
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("UploadfileDBName = " + "/" + fileDBName);
			
			// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			loadfile.transferTo(new File(saveFolder + "/" + fileDBName));
			uploadfilenames += fileDBName + ",";
			
			}
			//바뀐 파일명으로 저장
			item.setImage(uploadfilenames);	
		}
			
		
		itemService.insertItem(item); //저장메서드 호출
		itemService.insertSell(item); // 솔빈 sell_list DB 추가
		return "success";
	}
	
	
	
	
	//상품 리스트
	@GetMapping(value = "/item")
	public Map<String,Object> itemListAjax(
			@RequestParam(value="page",defaultValue="1", required=false) int page,
			@RequestParam(value="limit",defaultValue="10", required=false) int limit,
			@RequestParam(value="search_field", defaultValue="") String index,
			@RequestParam(value="search_word", defaultValue="") String search_word
			) {
		
		List<Item> list = null;
		
		int listcount = itemService.getListCount(); // 총 리스트 수를 받아옴
		
		
		// 총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;
		
		
		list = itemService.getSearchList(index, search_word, page, limit);
		
		//만약 maxpage가 2이고 page도 2인 경우
		//2페이지의 목록의 수가 한 개인 상태에서 남은 항목 한개를 삭제한 경우
		//maxpage=1이 되고 page=2가 됩니다. 이런 경우 page는 maxpage로 수정합니다.
		if(page>maxpage) {
			page=maxpage;
		}
		
		
		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = ((page - 1) / 10) * 10 + 1;
		
		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage + 10 - 1;
		
		if(endpage > maxpage)
			endpage = maxpage;
		
		List<Item> boardlist = itemService.getItemList(page, limit); // 리스트를 받아옴
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("listcount", listcount);
		map.put("boardlist", boardlist);
		map.put("boardlist1", list);
		map.put("limit", limit);
		map.put("search_field", index);
		map.put("search_word", search_word);
		return map;
	}
	
	//게시판 사용될 정보 구하기
	@GetMapping(value= {"/items/{num}"})
	public Map<String, Object> Detail(@PathVariable int num) {
		Item item = itemService.getDetail(num);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("item", item);
		return map;
	}
	
	//상품 삭제
	@DeleteMapping("/items/{num}")
	public int ItemDeleteAction(
			   @PathVariable int num
			) {
		// 글 삭제 명령을 요청한 사용자가 글을 작성한 사용자인지 판단하기 위해
		// 입력한 비밀번호와 저장된 비밀번호를 비교하여 일치하면 삭제합니다.
		logger.info("글번호=" + num);
		
		
		// 비밀번호 일치하는 경우 삭제 처리합니다.
		int result = itemService.itemDelete(num);
		
		
		// 삭제 처리 실패한 경우
		if (result == 0) {
			logger.info("게시판 삭제 실패");
			return -1;
		} 
		
		//삭제 처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
		logger.info("게시판 삭제 성공");
		return 1;
	}
	
	
	//상품 수정
	@PatchMapping("/items")
	public String ItemModifyAction(
			Item itemdata
			) throws Exception {
		
		String message="";
		
		MultipartFile[] uploadfile = itemdata.getUploadfile();
		
		String uploadfilenames = "";
		
		if (itemdata.getImage() != null && !itemdata.getImage().equals("")) { //기존파일 그대로 사용하는 경우입니다.
			logger.info("기존파일 그대로 사용합니다." + itemdata.getImage());
			itemdata.setOriginal(itemdata.getImage());
			itemdata.setImage(itemdata.getImage());//""로 초기화 합니다.
		} 
			//파일 변경한 경우
			if(uploadfile!=null) {
				for(MultipartFile loadfile : uploadfile) {
					logger.info("파일 변경되었습니다.");
					
					String fileName = loadfile.getOriginalFilename(); //원래 파일명
					itemdata.setOriginal(fileName);
					
					String fileDBName = fileDBName(fileName, saveFolder);
					
					//tranceferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
					loadfile.transferTo(new File(saveFolder + "/" + fileDBName));
//					loadfile.transferTo(new File(saveFolder + fileDBName));
					uploadfilenames += fileDBName + ",";
				}
				//바뀐 파일명으로 저장
				logger.info("기존파일명: "+itemdata.getImage());
				itemdata.setImage(itemdata.getImage()+uploadfilenames);
			}
		
		logger.info("글 번호 : " + itemdata.getId());
		logger.info("글 작성자 :" + itemdata.getSeller());
		logger.info("글 제목 :" + itemdata.getName());
		logger.info("글 가격 :" + itemdata.getPrice());
		logger.info("글 내용 :" + itemdata.getDescription());
	
		
		//DAO에서 수정 메서드 호출하여 수정합니다.
		int result = itemService.itemModify(itemdata);
		
		logger.info("수정결과:"+result);
		
		//수정에 실패한 경우
		if (result == 0) {
			message="fail";
		} else { //수정 성공의 경우
			logger.info("게시판 수정 완료");
			message = "success";
		}
		return message;
	}
}