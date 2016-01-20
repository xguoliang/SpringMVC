package com.mongo.morphia.complex.Enum;

/**
 * Copyright 1993-2012 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



/**
 *
 * @since 2012-2-27
 * @author macro_li
 */
public enum UserType {
    /**
     * 用户注册、邀请的企业邮箱账户，以及社区中邀请注册的公共邮箱与手机用户
     */
    COMMON{
        @Override
        public String getLocalString(){
            return "普通账号";
        }
    },
    /**
     * 企业管理员邀请的公共邮箱账号
     */
    PUBEMAIL_ENT{
        @Override
        public String getLocalString(){
            return "公共邮箱账号";
        }
    },
    /**
     * 企业管理员邀请的手机账号
     */
    MOBILE_ENT{
      @Override
      public String getLocalString(){
          return "手机账号";
      }
    },
    /**
     * 团队账号为管理员创建的虚拟账号，无法登录，只能切换
     */
    TEAM{
      @Override
      public String getLocalString(){
          return "团队账号";
      }
    },
    THREEPLATFORM {
        @Override
        public String getLocalString() {
            return "第三方平台账号";
        }
    }
    ;
    
    abstract public String getLocalString();
}
