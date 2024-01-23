package com.preschool.preschoolhome.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {

    CHECK_DUPLICATION_ID(HttpStatus.BAD_REQUEST,"아이디 중복체크를 해주세요."),
    CHECK_CODE(HttpStatus.BAD_REQUEST,"식별코드를 확인하세요."),
    NOT_ENTER_ID(HttpStatus.BAD_REQUEST,"아이디를 입력하세요."),
    ALREADY_EXIST_ID(HttpStatus.BAD_REQUEST,"이미 존재하는 아이디"),
    CHECK_UID(HttpStatus.BAD_REQUEST,"아이디를 확인해주세요"),
    NOT_EXIST_USER_ID(HttpStatus.NOT_FOUND,"아이디를 확인해주세요"),
    VALID_PASSWORD(HttpStatus.NOT_FOUND,"비밀번호를 확인해주세요"),
    CHANGE_JUST_ONETHING(HttpStatus.BAD_REQUEST, "한개 이상 입력하세요"),
    NEED_SIGNIN(HttpStatus.UNAUTHORIZED,"로그인이 필요합니다"),
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.NOT_FOUND,"refresh-token이 없습니다"),
    NOT_EMPTY_INFO(HttpStatus.BAD_REQUEST,"값을 입력하세요"),
    NOT_CONNETCT_KID(HttpStatus.BAD_REQUEST,"최대 2명까지만 연결 가능 합니다."),
    NOT_CORRECT_INFORMATION(HttpStatus.BAD_REQUEST,"잘못된 요청"),
    NOT_IMPORTED(HttpStatus.BAD_REQUEST ,"정보를 불러올 수 없음"),
    OVER_FIX_NOTICE(HttpStatus.BAD_REQUEST, "공지갯수를 초과했습니다"),
    NOT_ENTER_ACCESS(HttpStatus.BAD_REQUEST,"권한이 없음");




    private final HttpStatus httpStatus;
    private final String message;
}
