package com.cyclingTour.common;

import lombok.Data;

@Data
public class Result {
    private int code;
    private Object data;
//    成功
    public static Result success(){
          return result(20000,"成功");
    }
    public static Result success(Object data){
        return result(20000,data);
    }
//    失败
    public static Result fail(){
        return result(40000,"失败");
    }



    public static Result result(int code,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setData(data);
        return result;
    }

}
