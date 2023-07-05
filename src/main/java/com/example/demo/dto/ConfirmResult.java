package com.example.demo.dto;

import lombok.Data;

/**
 * ユーザー登録画面 form
 * 
 * @author ys-fj
 *
 */
@Data
public class ConfirmResult {

	/** ユーザーID */
	private String userId;

	/** ユーザー名 */
	private String userName;

	/** プロフィール写真 */
	private String imageFile;
}
