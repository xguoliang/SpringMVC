package com.mongo.morphia.complex.Enum;

/**
 * Copyright 1993-2012 Kingdee Software (China), Co., Ltd. , Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */



/**
 *
 * @since 2012-11-22
 * @author guolei_mao
 */
public enum NetworkSubType {
    
    COMPANY {
        @Override
        public String getLocalString() {
            return "公司";
        }
    },
    
    SCHOOL {
        @Override
        public String getLocalString() {
            return "学校";
        }
    },
   
    /**
     * lian_lin 2013-10-17 增加团队:公共邮箱注册的公司
     */
    TEAM{
    	 @Override
         public String getLocalString() {
             return "团队";
         }
    },
    
    /**
     * lian_lin 2014-12-24  微信群相册类型网络
     */
    PHOTO {
    	 @Override
         public String getLocalString() {
             return "群相册";
         }
    },
    
   
    /**
     * lian_lin 2013-12-5 与兴旺讨论，在subType也加上此类型，方便业务中处理
     */
    COMMUNITY {
        @Override
        public String getLocalString() {
            return "社区";
        }
    },
    
    /**
     * 从V4版本升级旧数据时（小组，社区均变为网络），需要标识这些新创建网络的来源（Network.source），因此增加此标识：
     *  GROUP : 小组
     *  DO : 云之家导入
     *  COMMUNITY：社区
     *  peng_luo 2015-03-05
     */
    GROUP{
      	 @Override
           public String getLocalString() {
               return "小组";
           }
      },
      
    DO{
        @Override
           public String getLocalString() {
               return "云之家导入";
           }
      };
    
    public abstract String getLocalString();
    
}
