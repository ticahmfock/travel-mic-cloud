package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author: TK
 * @date: 2021/8/12 14:33
 */
@Data
public class User implements Serializable {

  private static final long serialVersionUID = -1349199662737294908L;

  /**
   * 用户编号
   */
  private Long userId;
  /**
   * 用户名
   */
  private String userName;
  /**
   * 密码
   */
  private String password;
  /**
   * 手机号
   */
  private String mobile;
  /**
   * 头像
   */
  private String url;
  /**
   * 状态,wait待审核,normal正常,closed禁用,delete删除
   */
  private String status;
  /**
   * 创建时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  /**
   * 最近一次登录时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date lastTime;
}
