package com.spring.project3rd.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDto {
    @NotEmpty
    private String id;

    @NotEmpty
    private String password;
}