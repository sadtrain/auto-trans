package com.sadtrain.autotrans.api.response.base;

public class BaseResponse <T> {
    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public static void main(String[] args) {
//        System.out.println(StringUtils.replace("a",".*","x"));
//        Matcher matcher = Pattern.compile((String) ".*").matcher("a");
//        matcher.matches();
//        System.out.println(matcher.group(0));
//        System.out.println(matcher.group(1));
        System.out.println("a".replaceAll(".*","xxx"));
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseResponse{");
        sb.append("code=").append(code);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
