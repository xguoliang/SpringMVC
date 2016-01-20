package com.mongo.morphia.complex.integer;



/**
 * 网络档案信息
 * 
 * @since 2010-11-30
 * @author xichu_yu
 */
public final class NetworkProfileInfo {
    /**
     * 关系类型
     * 
     * @since 2010-12-2
     * @author xichu_yu
     */
    public static enum Relation {
        /**
         * 上级
         */
        SUPERIOR,
        /**
         * 同事
         */
        FELLOW,
        /**
         * 下级
         */
        FOLLOWER,

        /**
         * 自己
         */
        SELF
    }

    private String userId;
    private String userName;
    private String jobTitle;
    private Relation relation;
    private String photoId;
    private boolean assigned;
    private String orgName;
    private String orgId;
    private String email;

    public NetworkProfileInfo(String userId, String userName, String photoId,//
            String jobTitle, Relation relation, boolean assigned, String email) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.jobTitle = jobTitle;
        this.relation = relation;
        this.photoId = photoId;
        this.assigned = assigned;
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }

    /**
     * 是否明确指定的,如果不是,则表示根据同事/上下级关系算出来的,在汇报关系图上不能删除.
     * 
     * @return
     */
    public boolean isAssigned() {
        return assigned;
    }

    public String getPhotoId() {
        return photoId;
    }

    public String getUserId() {
        return userId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getUserName() {
        return userName;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getOrgName() {
        return orgName;
    }
}
