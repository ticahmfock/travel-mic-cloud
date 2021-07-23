package constant.enums;

/**
 * @author: TK
 * @date: 2021/7/22 17:51
 */
public enum CodeEnum {
  SUCCESS(0),
  ERROR(1);

  private Integer code;

  CodeEnum(Integer code) {
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }
}
