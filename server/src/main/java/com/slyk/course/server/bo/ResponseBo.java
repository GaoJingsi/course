package com.slyk.course.server.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBo<T> {

    private Integer error_no;
    private T data;
    private String msg;
}
