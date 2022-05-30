package com.sadtrain.autotrans.web.response;

public class ResponseBuilder {

    public static <T> BaseResponse<T> createErrorResponse(String message){
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setMessage(message);
        baseResponse.setCode(-1);
        return baseResponse;
    }

    public static <T> BaseResponse<T> createSuccessResponse(T data){
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setData(data);
        baseResponse.setCode(-1);
        return baseResponse;
    }
}
