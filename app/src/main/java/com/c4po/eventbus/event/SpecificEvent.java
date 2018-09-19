package com.c4po.eventbus.event;

/**
 * 特定具体的事件
 *
 * @author Lisa
 * @date 2018/9/19
 */
public class SpecificEvent {

    public String userName;
    public String userAvatar;

    public SpecificEvent(String userName,String userAvatar){
        this.userAvatar = userAvatar;
        this.userName = userName;
    }

}
