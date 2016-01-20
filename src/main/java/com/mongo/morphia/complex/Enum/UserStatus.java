package com.mongo.morphia.complex.Enum;

import com.mongo.morphia.complex.common.ValueObject;



public enum UserStatus implements ValueObject<UserStatus>{	
	/**
	 * 申请
	 */
	APPLY{
        @Override
        public String getLocalString() {
            return "申请中";
        }
    }, 
	
	/**
	 * 启用（审核通过）
	 */
	ENABLE{
        @Override
        public String getLocalString() {
            return "启用";
        }
    }, 
	
	/**
	 * 禁用
	 */
	DISABLE{
	    @Override
	    public String getLocalString() {
	        return "禁用";
	    }
	},
    
	UNVERIFIED {
        @Override
        public String getLocalString() {
            return "待验证";
        }
    };

    @Override
    public boolean sameValueAs(final UserStatus other) {
        if (null == other) return false;
        return this.getClass() == other.getClass() && this.equals(other);
    }
    
    abstract public String getLocalString();
    
    public static void main(String[] args) {
		System.out.println(UserStatus.APPLY);
	}
}
