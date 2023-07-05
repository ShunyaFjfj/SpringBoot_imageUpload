package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面Controller
 * 
 * @author ys-fj
 *
 */
@Controller
@RequiredArgsConstructor
public class SignupController {

	/** ユーザー登録画面Service */
	private final SignupService service;

	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @param form フォーム
	 * @return 画面名
	 */
	@GetMapping("/signup")
	public String view(Model model, SignupForm form) {
		return "signup";
	}

	/**
	 * ユーザー登録
	 * 
	 * @param form フォーム
	 * @return リダイレクト先
	 * @throws IOException
	 */
	@PostMapping("/signup")
	public String signup(SignupForm form) throws IOException {
		service.signup(form);

		return "redirect:/signup";
	}
}
