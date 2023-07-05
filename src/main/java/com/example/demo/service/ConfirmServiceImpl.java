package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ConfirmResult;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConfirmServiceImpl implements ConfirmService {

	/** ユーザー情報テーブルDAO */
	private final UserInfoRepository repository;

	/** プロフィール画像の保存先フォルダ */
	@Value("${image.folder}")
	private String imgFolder;

	/** プロフィール画像の保管拡張子 */
	@Value("${image.extract}")
	private String imgExtract;

	/** デフォルトプロフィール画像 */
	@Value("${image.default}")
	private String imgDefault;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<ConfirmResult> confirm(String userId) throws IOException {
		var userInfoOpt = repository.findById(userId);
		if (userInfoOpt.isEmpty()) {
			return Optional.empty();
		}

		var userInfo = userInfoOpt.get();

		var confirmResult = new ConfirmResult();
		confirmResult.setUserId(userId);
		confirmResult.setUserName(userInfo.getUserName());
		confirmResult.setImageFile("data:image/jpg;base64," + outputImage(userId));

		return Optional.of(confirmResult);
	}

	/**
	 * プロフィール画像を出力する
	 * 
	 * @param userId ユーザーID
	 * @return Base64でエンコードされた画像データ
	 * @throws IOException
	 */
	private String outputImage(String userId) throws IOException {
		var imgFilePath = searchImage(userId);
		var byteImg = Files.readAllBytes(imgFilePath);

		return Base64.getEncoder().encodeToString(byteImg);
	}

	/**
	 * プロフィール画像検索
	 * 
	 * ユーザーIDに紐付くプロフィール画像のパスを検索する。未登録の場合はデフォルト画像のパスを返す
	 * 
	 * @param userId ユーザーID
	 * @return プロフィール画像のパス
	 */
	private Path searchImage(String userId) {
		var searchFileName = userId + imgExtract;
		var imgFilePath = Path.of(imgFolder, searchFileName);

		return Files.exists(imgFilePath) ? imgFilePath : Path.of(imgFolder, imgDefault + imgExtract);
	}

}
