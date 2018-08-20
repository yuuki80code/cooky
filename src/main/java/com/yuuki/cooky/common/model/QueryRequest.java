package com.yuuki.cooky.common.model;

import lombok.Data;

@Data
public class QueryRequest<T> {

    private Params params;

    private T req;

}
