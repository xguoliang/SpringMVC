package com.mongo.morphia.complex.Enum;



public enum Gender {
    
    MALE() {
        @Override
        public String getName() {
            return "男";
        }        
    }, 
    
    FEMALE() {
        @Override
        public String getName() {
            return "女";
        }
    }, 
    
    SECRECY() {
        @Override
        public String getName() {
            return "保密";
        }
    };
   

    public abstract String getName();
    
   public static void main(String[] args) {
	System.out.println(Gender.MALE.toString());
}
}
