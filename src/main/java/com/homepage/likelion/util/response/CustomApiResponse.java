package com.homepage.likelion.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomApiResponse<T> {

    // status, data, message 를 담는 Response Entity Class.

    private int status;

    private T data;

    private String message;

    // Success method
    public static <T> CustomApiResponse<T> createSuccess(int status, T data, String message) {
        return new CustomApiResponse<>(status, data, message);
    }

    // Failure method
    public static <T> CustomApiResponse<T> createFailWithout(int status, String message) {
        return new CustomApiResponse<>(status, null, message);
    }

}