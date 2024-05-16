package com.homepage.likelion.test.tmp.controller;

import com.homepage.likelion.test.tmp.dto.SimpleDto;
import com.homepage.likelion.util.response.CustomApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customResponse")
public class CustomTestController {

    @PostMapping("/simple")
    public ResponseEntity<CustomApiResponse> simple(){

        // 1. 응답 Body 구성
        CustomApiResponse<Object> responseBody = CustomApiResponse.createSuccess(200/*HttpStatus.OK*/, null, "회원가입에 성공하였습니다.");

        // 2. 응답 Body를 ResponseEntity에 넣기
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/jsonData")
    public ResponseEntity<CustomApiResponse> jsonData(){

        // userId : example
        // email : example@naver.com

/*        // dto class _ 가장 직관적인 방법 new 키워드로 생성하기
        SimpleDto dto = new SimpleDto("example", "example@naver.com");*/

        // Builder pattern을 사용하는 예
        SimpleDto dto = SimpleDto.builder()
                .userId("example")
                .email("example@naver.com")
                .build();

        // data에 해당하는 값들을 담고있는 객체를 DTO라고 한다.
        CustomApiResponse<Object> responseBody = CustomApiResponse.createSuccess(HttpStatus.OK.value(), dto, "회원 조회에 성공하였습니다.");

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
