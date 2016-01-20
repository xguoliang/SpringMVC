package com.mongo.morphia.complex.Enum;



public enum ProductFeature {
    SHORTMAIL {
        @Override
        public String getLocalString() {
            return "短邮";
        }
    },
    
    ADDRESSBOOK {
        @Override
        public String getLocalString() {
            return "通讯录";
        }
    };
    
    
    public abstract String getLocalString();
}
