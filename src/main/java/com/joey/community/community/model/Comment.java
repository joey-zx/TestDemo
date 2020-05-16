package com.joey.community.community.model;

public class Comment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.ID
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.PARENT_ID
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    private Integer parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.TYPE
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.GMT_CREATE
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.GMT_MODIFIED
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.LIKE_COUNT
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    private Integer likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.COMENTATOR
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    private Integer comentator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.COMMENT_COUNT
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    private Integer commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COMMENT.CONTENT
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.ID
     *
     * @return the value of COMMENT.ID
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.ID
     *
     * @param id the value for COMMENT.ID
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.PARENT_ID
     *
     * @return the value of COMMENT.PARENT_ID
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.PARENT_ID
     *
     * @param parentId the value for COMMENT.PARENT_ID
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.TYPE
     *
     * @return the value of COMMENT.TYPE
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.TYPE
     *
     * @param type the value for COMMENT.TYPE
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.GMT_CREATE
     *
     * @return the value of COMMENT.GMT_CREATE
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.GMT_CREATE
     *
     * @param gmtCreate the value for COMMENT.GMT_CREATE
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.GMT_MODIFIED
     *
     * @return the value of COMMENT.GMT_MODIFIED
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.GMT_MODIFIED
     *
     * @param gmtModified the value for COMMENT.GMT_MODIFIED
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.LIKE_COUNT
     *
     * @return the value of COMMENT.LIKE_COUNT
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.LIKE_COUNT
     *
     * @param likeCount the value for COMMENT.LIKE_COUNT
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.COMENTATOR
     *
     * @return the value of COMMENT.COMENTATOR
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public Integer getComentator() {
        return comentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.COMENTATOR
     *
     * @param comentator the value for COMMENT.COMENTATOR
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public void setComentator(Integer comentator) {
        this.comentator = comentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.COMMENT_COUNT
     *
     * @return the value of COMMENT.COMMENT_COUNT
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.COMMENT_COUNT
     *
     * @param commentCount the value for COMMENT.COMMENT_COUNT
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COMMENT.CONTENT
     *
     * @return the value of COMMENT.CONTENT
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COMMENT.CONTENT
     *
     * @param content the value for COMMENT.CONTENT
     *
     * @mbg.generated Sat May 09 10:46:39 GMT+08:00 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}