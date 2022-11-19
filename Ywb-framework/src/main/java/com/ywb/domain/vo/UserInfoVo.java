package com.ywb.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfoVo {


    /**
     * 头像
     */
    private String avatar;

    private String email;
    /**
     * 主键
     */
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    private String sex;


}
