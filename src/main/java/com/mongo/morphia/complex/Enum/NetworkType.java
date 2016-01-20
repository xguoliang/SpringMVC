package com.mongo.morphia.complex.Enum;


public enum NetworkType {
    
    COMPANY {
        @Override
        public String getLocalString() {
            return "公司";
        }
    },
    
    COMMUNITY {
        @Override
        public String getLocalString() {
            return "社区";
        }
    },
    /**
     * @deprecated 增加新类型修改量太大了。经过讨论不启用，通过NetworkSubType解决问题
     * lian_lin 2013-10-17 增加团队:公共邮箱注册的公司
     */
    TEAM{
    	 @Override
         public String getLocalString() {
             return "团队";
         }
    },
    
    
    /**
     * lian_lin 2013-10-17 虚拟网络，T_USER没有defautlnetworkID统一进入这个网络。
     */
    VIRTUAL{
      	 @Override
           public String getLocalString() {
               return "虚拟";
           }
      };
       
    
    
    
    public abstract String getLocalString();

}
