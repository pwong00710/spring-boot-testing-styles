package com.zat.easygo.cucumber.type;

public enum Action {
    LOGIN("100"), 							// LOGIN, Call EasyGo Core API to login and get easy go profile
    SESSION_VALIDATION("150"), 				// SESSION TOKEN VALIDATION
    GET_USER_PROFILE_AND_USAGE("210"),		// Get user profile and usage
    PAYMENT_BY_CP("800");					// Call EasyGo Core API
    
    private String code;

    Action(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }    
}
