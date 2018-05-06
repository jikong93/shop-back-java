package com.zq.shop.web.mappers;

import com.zq.shop.web.bean.UserFriends;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFriendsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zq_friends
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zq_friends
     *
     * @mbg.generated
     */
    int insert(UserFriends record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zq_friends
     *
     * @mbg.generated
     */
    int insertSelective(UserFriends record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zq_friends
     *
     * @mbg.generated
     */
    UserFriends selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zq_friends
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserFriends record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zq_friends
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserFriends record);

    List<UserFriends> findByUid(@Param("uid")Integer uid);

	int deleteByUidAndFollowId(@Param("uid")Integer uid,@Param("followId")Integer followId);
    List<UserFriends> findByuidAndFollowId(@Param("uid")Integer uid,@Param("followId")Integer followId);




}