package constant;

import constant.enums.CodeEnum;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**结果响应实体类
 * @author: TK
 * @date: 2021/7/22 17:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> implements Serializable {

  /**
   * 实体数据
   */
  private T data;
  /**
   * 响应码
   */
  private Integer code;
  /**
   * 响应信息
   */
  private String msg;

  public static <T> JsonResult<T> success(String msg) {
    return doSuccess(null, CodeEnum.SUCCESS.getCode(), msg);
  }

  public static <T> JsonResult<T> success(T data, String msg) {
    return doSuccess(data, CodeEnum.SUCCESS.getCode(), msg);
  }

  private static <T> JsonResult<T> doSuccess(T data, Integer code, String msg) {
    return new JsonResult<T>(data, code, msg);
  }

  public static <T> JsonResult<T> fail(String msg) {
    return doFail(null, CodeEnum.ERROR.getCode(), msg);
  }

  public static <T> JsonResult<T> fail(T data, String msg) {
    return doFail(data, CodeEnum.ERROR.getCode(), msg);
  }

  private static <T> JsonResult<T> doFail(T data, Integer code, String msg) {
    return new JsonResult<T>(data, code, msg);
  }
}
