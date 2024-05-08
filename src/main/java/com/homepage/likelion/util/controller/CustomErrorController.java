package com.homepage.likelion.util.controller;

import com.homepage.likelion.util.response.CustomApiResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<CustomApiResponse<?>> handleError(HttpServletRequest request) {

        // HttpServletRequest 는 요청에 대한 상태코드를 담은 객체이다.
        // HttpServletRequest로 부터 status code 가져오기
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // error 발생
        if(status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // Bad Request (400)
            if(statusCode == HttpStatus.BAD_REQUEST.value()) { // statusCode == 400
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new CustomApiResponse<>(HttpStatus.BAD_REQUEST.value(), null, "잘못된 요청입니다."));
            }

            // Forbidden (403)
            else if(statusCode == HttpStatus.FORBIDDEN.value()) { // statusCode == 403
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(new CustomApiResponse<>(HttpStatus.FORBIDDEN.value(), null, "접근이 금지되었습니다."));
            }

            // Not found (404)
            else if(statusCode == HttpStatus.NOT_FOUND.value()) { // statusCode == 404
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new CustomApiResponse<>(HttpStatus.NOT_FOUND.value(), null, "요청 경로를 찾을 수 없습니다."));
            }

            // Method Not Allowed (405)
            else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) { // statusCode == 405
                return ResponseEntity
                        .status(HttpStatus.METHOD_NOT_ALLOWED)
                        .body(new CustomApiResponse<>(HttpStatus.METHOD_NOT_ALLOWED.value(), null, "허용되지 않은 메소드입니다."));
            }

            // Internal Server Error (500)
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { // statusCode == 500
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new CustomApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "내부 서버 오류가 발생하였습니다."));
            }
        }

        // status code 에 따라서 응답을 다르게 내려줘야 한다.

        // 일단 이 메소드로 들어오게 된 이상, 오류가 발생하였다는 뜻이므로 Default로 ERROR MESSAGE를 return 해주어야 한다.
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CustomApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "내부 서버 오류가 발생하였습니다."));
    }
}