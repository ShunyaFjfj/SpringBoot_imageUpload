package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.ConfirmForm;
import com.example.demo.service.ConfirmService;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー確認画面Controller
 * 
 * @author ys-fj
 *
 */
@Controller
@RequiredArgsConstructor
public class ConfirmController {

	/** ユーザー確認画面Service */
	private final ConfirmService service;

	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @param form フォーム
	 * @return 画面名
	 */
	@GetMapping("/confirm")
	public String view(Model model, ConfirmForm form) {
		return "confirm";
	}

	/**
	 * ユーザー確認
	 * 
	 * @param loginId ユーザーID
	 * @param model モデル
	 * @return 画面名
	 * @throws IOException
	 */
	@PostMapping("/confirm")
	public String confirm(String userId, Model model) throws IOException {
		var confirmFormOpt = service.confirm(userId);
		if (confirmFormOpt.isEmpty()) {
			model.addAttribute("errMsg", "ユーザーが存在しません。");
		} else {
			model.addAttribute("confirmResult", confirmFormOpt.get());
		}

		return "confirm";
	}
}
