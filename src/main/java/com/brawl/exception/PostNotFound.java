package com.brawl.exception;

public class PostNotFound extends HodologException{

    //메시지를 여기서 상수로 정해놓으면 될 것 같다(new IllegalArgumentException("존재하지 않는 글입니다"))이제 이렇게 할 필요 없음
    private static final String MESSAGE = "존재하지 않는 글입니다.";

    public PostNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
