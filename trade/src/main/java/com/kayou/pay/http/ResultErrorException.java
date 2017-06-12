package com.kayou.pay.http;



public class ResultErrorException extends RuntimeException {
    public ResultErrorException(String msg){
        super(msg);
    }
}
