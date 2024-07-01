package com.bupt.Pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean implements Serializable {
    private Long total;//总记录数
    private List rows;//分页展示内容
}
