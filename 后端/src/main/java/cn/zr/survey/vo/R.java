package cn.zr.survey.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R { //返回的json数据封装在R对象
    /**
     * 编码,  成功200, 失败400
     */
    private Integer code;

    /**
     * 消息,  成功success, 失败fail
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;

    /**
     * 响应成功
     * @return
     */
    public static R ok(){
        return new R(200,"success",null);
    }


    /**
     * 响应成功,带参
     * @return
     */
    public static R ok(Object data){
        return new R(200,"success",data);
    }

    /**
     * 响应失败
     * @return
     */
    public static R error(){
        return new R(400,"fail",null);
    }
}
