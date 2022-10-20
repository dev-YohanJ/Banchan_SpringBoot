package com.banchan.myhome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banchan.myhome.domain.Buy;
import com.banchan.myhome.domain.Item;
import com.banchan.myhome.domain.Sell;
import com.banchan.myhome.domain.Wish;
import com.banchan.myhome.service.MyService;


@RestController
public class MyController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyController.class);
	
	private MyService myservice;
	
	@Autowired
	public MyController(MyService myservice) {
		this.myservice=myservice;
	}
	
	@GetMapping(value="/wish")
	public Map<String, Object> wish_list(@RequestParam String id,
										 @RequestParam int page) {
		logger.info("id=" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Item> item = myservice.wish_list(id, page);
		int listcount = myservice.getListCount(id);
		
		map.put("item", item);
		map.put("listcount", listcount);
		
		return map;
	}
	
	@DeleteMapping(value = "/wish")
	public int wish_del(Wish wish) {
		
		logger.info("member_id=" + wish.getMember_id());
		int a[] = wish.getItem_id();
		logger.info(a + "");
		for(int t : a) {
			logger.info("item_id = " + t + "");
		}
		
		int result = myservice.wish_del(wish);
				
		// 삭제 처리 성공한 경우
		if (result != 0) {
			logger.info("찜 목록 삭제 성공");
			
		}
		return result;
		
	}
	
	@GetMapping(value="/sell")
	public Map<String, Object> sell_list(@RequestParam String id,
										 @RequestParam int page) {
		logger.info("id=" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Item> item = myservice.sell_list(id, page);
		int listcount = myservice.getSellListCount(id);
		
		map.put("item", item);
		map.put("listcount", listcount);
		
		return map;
	}
	
	@DeleteMapping(value = "/sell")
	public int sell_del(Sell sell) {
		
		logger.info("member_id=" + sell.getMember_id());
		int a[] = sell.getItem_id();
		logger.info(a + "");
		for(int t : a) {
			logger.info("item_id = " + t + "");
		}
		
		int result = myservice.sell_del(sell);
		int result2 = myservice.wish_del2(sell);		
		// 삭제 처리 성공한 경우
		if (result != 0 && result2 != 0) {
			logger.info("판매 목록 삭제 성공");
			
		}
		return result;
		
	}
	
	@PatchMapping(value = "/sellfn/{id}")
	public int sellfn(@PathVariable int id) {
		logger.info("item_id = " + id);
		
		int result = myservice.sellfn(id);
		
		return result;
		
	}
	
	@GetMapping(value="/buy")
	public Map<String, Object> buy_list(@RequestParam String id,
										 @RequestParam int page) {
		logger.info("id=" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Item> item = myservice.buy_list(id, page);
		int listcount = myservice.getBuyListCount(id);
		
		map.put("item", item);
		map.put("listcount", listcount);
		
		return map;
	}
	
	@DeleteMapping(value = "/buy")
	public int sell_del(Buy buy) {
		
		logger.info("member_id=" + buy.getMember_id());
		int a[] = buy.getItem_id();
		logger.info(a + "");
		for(int t : a) {
			logger.info("item_id = " + t + "");
		}
		
		int result = myservice.buy_del(buy);
		// 삭제 처리 성공한 경우
		if (result != 0) {
			logger.info("판매 목록 삭제 성공");
			
		}
		return result;
	}
	
	@GetMapping(value = "wish/add")
	public int wish_insert(Wish w) {
		return myservice.wish_insert(w);
	}
	
	@GetMapping(value = "wish/check")
	public int wish_check(Wish w) {
		return myservice.wish_check(w);
	}
	
	@DeleteMapping(value = "wish/delete")
	public int wish_delete(Wish w) {
		return myservice.wish_delete(w);
	}
	
	@GetMapping(value = "buy/add")
	public int wish_insert(Buy b) {
		return myservice.buy_insert(b);
	}
	
	@GetMapping(value = "buy/check")
	public int wish_check(Buy b) {
		return myservice.buy_check(b);
	}
	
	@DeleteMapping(value = "buy/delete")
	public int wish_delete(Buy b) {
		return myservice.buy_delete(b);
	}
	
}
