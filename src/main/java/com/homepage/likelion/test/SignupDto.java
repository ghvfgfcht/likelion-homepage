package com.homepage.likelion.test;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// requestDto 에는 @Builder 어노테이션이 필요하지 않다.
public class SignupDto {

    // 기본적으로 json data에 userId가 포함되지 않아도 오류가 발생하지 않는다.
    // 무조건 Controller에서 @Valid 어노테이션으로 검증 처리를 해주어야 오류가 발생하게 된다.
    // 이때, log에 "비어 있을 수 없습니다." 라는 메세지가 뜨게 된다. 이 메세지는 @NotEmpty 어노테이션 자체에서 지원하는 자체 메세지이다.
    // 이것은 다음과 같이  Custom 가능하다.
    @NotEmpty(message = "userId는 필수값입니다.")
    private String userId;

    @Email(message = "이메일 형식을 맞춰주세요.")
    private String email;

    private String password;

}