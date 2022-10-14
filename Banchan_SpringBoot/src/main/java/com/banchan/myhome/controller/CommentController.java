package com.banchan.myhome.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.banchan.myhome.domain.Comment;
import com.banchan.myhome.service.CommentService;

@RestController
public class CommentController {
	
	private CommentService commentService;
	
	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping("/comments")
	public Map<String,Object> CommentList(
			@RequestParam int board_num, 
			@RequestParam int page) {
		List<Comment> list = commentService.getCommentList(board_num, page);
		int listcount = commentService.getListCount(board_num);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("listcount", listcount);
		return map;
	}
	
	@PostMapping("/comments/new")
	public int CommentAdd(@RequestBody Comment co) {
		return commentService.commentsInsert(co);
	}
	
	@PatchMapping("/comments")
	public int CommentUpdate(@RequestBody Comment co) {
		return commentService.commentsUpdate(co);
	}
	
	@DeleteMapping("/comments/{num}")
	public int CommentDelete(@PathVariable int num) {
		return commentService.commentsDelete(num);
	}
}
