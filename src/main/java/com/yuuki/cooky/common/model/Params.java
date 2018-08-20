package com.yuuki.cooky.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Params implements Serializable {


    private static final long serialVersionUID = -7504280353284515856L;

    private Integer page;

    private Integer pageSize;

}
