package cn.zr.survey.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class P {  //分页类型
    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据
     */
    private List<?> list;
}
