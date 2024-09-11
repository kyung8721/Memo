package com.kyung.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	// 상수 - 이 변수 값은 다른 곳에서 수정 불가
	// 상수의 변수명은 대문자로 지정
	public static final String FILE_UPLOAD_PATH = "C:\\Users\\user\\Desktop\\bae\\springProject\\upload\\memo"; 
	
	// 파일 저장
	public static String saveFile(int userId, MultipartFile file){
		
		if(file == null) {
			return null;
		}
		
		// 같은 파일이름으로 전달될 경우
		// 폴더를 만들어서 파일 저장
		// 로그인 사용자 userId를 폴더 이름으로 사용
		// 현재 시간정보를 폴더 이름으로 사용
		// UNIX TIME : 1970년 1월 1일부터 흐른 시간을 milli second(1/1000초)로 표현한 값
		// ex ) 2_938091328
		
		// 폴더 이름 지정
		String directoryName = "/" +  userId + "_" + System.currentTimeMillis();
		
		// 전체 경로 지정
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		// 파일 객체 생성
		File directory = new File(directoryPath);
		
		// 앞의 경로를 기반으로 디렉토리를 만들어줌
		if(!directory.mkdir()) {
			// 폴더 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		// 실제 파일
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, bytes); // 경로, 실제 데이터
		} catch (IOException e) {
			// 파일 저장 실패
			return null;
		}
		
		
		// 저장된 파일을 접근할 URL Path 만들기
		// 파일 저장 경로 : C:\\Users\\user\\Desktop\\bae\\springProject\\upload\\memo/2_8120980/test.png
		// 이 파일에 접근 가능한 URL path : /images/2_8120980/test.png
		return "/images" + directoryName + "/" + file.getOriginalFilename();
	}
	
	// 파일 삭제
}
