package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面Service
 * 
 * @author ys-fj
 *
 */
@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {

	/** ユーザー情報テーブルRepository */
	private final UserInfoRepository repository;

	/** Dozer Mapper */
	private final Mapper mapper;

	/** プロフィール画像の保存先フォルダ */
	@Value("${image.folder}")
	private String imgFolder;

	/** プロフィール画像の保管拡張子 */
	@Value("${image.extract}")
	private String imgExtract;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void signup(SignupForm form) throws IOException {
		if (!form.getImageFile().isEmpty()) {
			// 保存する画像ファイルのパス設定
			var saveFileName = form.getUserId() + imgExtract;
			Path imgFilePath = Path.of(imgFolder, saveFileName);

			// 画像ファイルの保存(フォルダ)
			Files.copy(form.getImageFile().getInputStream(), imgFilePath);
		}

		// DB更新
		var userInfo = mapper.map(form, UserInfo.class);
		repository.save(userInfo);
	}

}
