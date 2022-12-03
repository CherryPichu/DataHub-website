package com.example.datahubwebsite.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN) // 에러 상태 번호 지정
public class ForbiddenException extends RuntimeException {
}
