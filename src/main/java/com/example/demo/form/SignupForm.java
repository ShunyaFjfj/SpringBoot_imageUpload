package com.example.demo.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * ユーザー登録画面 form
 * 
 * @author ys-fj
 *
 */
@Data
public class SignupForm {

	/** ログインID */
	private String userId;

	/** ユーザー名 */
	private String userName;

	/** プロフィール写真 */
	private MultipartFile imageFile;
}
