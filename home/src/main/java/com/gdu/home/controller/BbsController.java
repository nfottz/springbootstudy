package com.gdu.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.home.service.BbsService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/bbs")
@RequiredArgsConstructor
@Controller
public class BbsController {

	private final BbsService bbsService;
	
	@GetMapping("/list.do")
	public String list(HttpServletRequest request, Model model) {
		bbsService.loadBbsList(request, model);
		return "bbs/list";
	}
	
	@GetMapping("/write.html")
	public String write() {
		return "bbs/write";
	}
	
	@PostMapping("/add.do")
	public String add(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("addResult", bbsService.addBbs(request));
		return "redirect:/bbs/list.do";
	}
	
	@PostMapping("/remove.do")
	public String remove(int bbsNo, RedirectAttributes redirectAttributes) {	// @RequestParam 생략
		int removeResult = bbsService.removeBbs(bbsNo);
		redirectAttributes.addFlashAttribute("removeResult", removeResult);
		return "redirect:/bbs/list.do";
	}
	
	@PostMapping("/reply/add.do")
	public String addReply(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("addReplyResult", bbsService.addReply(request));
		return "redirect:/bbs/list.do";
	}
	
}
