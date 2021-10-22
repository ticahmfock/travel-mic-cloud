package com.tk.service;

/**
 * @author: TK
 * @date: 2021/10/21 11:20
 */
public interface TestService {

  /**
   * 获取粉丝数
   *
   * @param userId 用户id
   * @return 粉丝数
   */
  long countFansCountByUserId(Long userId);

  /**
   * 获取消息数
   *
   * @param userId 用户id
   * @return 消息数
   */
  long countMsgCountByUserId(Long userId);

  /**
   * 获取收藏总数
   *
   * @param userId 用户id
   * @return 收藏总数
   */
  long countCollectCountByUserId(Long userId);

  /**
   * 获取关注数
   *
   * @param userId 用户id
   * @return 关注数
   */
  long countFollowCountByUserId(Long userId);

  /**
   * 获取红包总数
   *
   * @param userId 用户id
   * @return 红包总数
   */
  long countRedBagCountByUserId(Long userId);

  /**
   * 获取优惠卡券总数
   *
   * @param userId 用户id
   * @return 优惠卡券总数
   */
  long countCouponCountByUserId(Long userId);

}
