package com.ywb.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagVo {
    @TableId
    private Long id;

    //标签名
    private String name;


    //备注
//    private String remark;
}
