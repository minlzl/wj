package com.lzl.wj.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {
    private int code;
    private String message;
    private Object data;

}
