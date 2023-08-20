package com.spring.project3rd.domain.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }

    // Getter, Setter 메서드 또는 필요한 다른 메서드들...
}