package com.bc.app.server.vo.usercontroller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 登录视图数据实体
 *
 * @author tangyifei
 */
@Data
public class LoginVO implements Serializable {

    @NotBlank(message = "用户手机号不能为空！")
    private String phone;

    @NotBlank(message = "用户密码不能为空！")
    private String password;

}
