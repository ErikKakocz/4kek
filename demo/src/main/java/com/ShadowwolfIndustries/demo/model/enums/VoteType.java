package com.ShadowwolfIndustries.demo.model.enums;

public enum VoteType {
    UPVOTE("U"),DOWNVOTE("D");

    private String code;

    private VoteType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
