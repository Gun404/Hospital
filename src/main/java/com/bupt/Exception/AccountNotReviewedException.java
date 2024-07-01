package com.bupt.Exception;

/**
 * 账户未审核异常
 */
public class AccountNotReviewedException extends RuntimeException{
    public AccountNotReviewedException() {
    }

    public AccountNotReviewedException(String msg) {
        super(msg);
    }

}
