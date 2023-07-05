package com.example.demo.service;

import java.io.IOException;

import com.example.demo.form.SignupForm;

/**
 * ユーザー登録画面Service Interface
 * 
 * @author ys-fj
 *
 */
public interface SignupService {

	/**
	 * ユーザー登録
	 * 
	 * @param form フォーム情報
	 * @throws IOException
	 */
	public void signup(SignupForm form) throws IOException;
}
