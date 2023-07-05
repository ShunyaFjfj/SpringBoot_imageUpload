package com.example.demo.service;

import java.io.IOException;
import java.util.Optional;

import com.example.demo.dto.ConfirmResult;

/**
 * ユーザー確認画面Service Interface
 * 
 * @author ys-fj
 *
 */
public interface ConfirmService {

	/**
	 * ユーザー確認
	 * 
	 *  ユーザーIDを使ってユーザー情報を検索する
	 *  
	 * @param loginId ユーザーID
	 * @return ユーザー情報
	 * @throws IOException
	 */
	public Optional<ConfirmResult> confirm(String loginId) throws IOException;
}
