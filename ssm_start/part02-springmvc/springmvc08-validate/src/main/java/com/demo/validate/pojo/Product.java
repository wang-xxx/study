package com.demo.validate.pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Null(message = "赋值必须为空")
    private String name;

    @NotNull
    private String desc;

}
