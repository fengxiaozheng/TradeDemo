package com.payexpress.trade.http;



public class ResultErrorException extends RuntimeException {
    public ResultErrorException(String msg){
        super(msg);
    }
}
