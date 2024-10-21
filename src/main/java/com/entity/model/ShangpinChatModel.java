package com.entity.model;

import com.entity.ShangpinChatEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 用户咨询
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShangpinChatModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 提问人
     */
    private Integer yonghuId;


    /**
     * 回答人
     */
    private Integer shangjiaId;


    /**
     * 问题
     */
    private String shangpinChatIssueText;


    /**
     * 问题图片
     */
    private String shangpinChatIssuePhoto;


    /**
     * 问题时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date issueTime;


    /**
     * 回复
     */
    private String shangpinChatReplyText;


    /**
     * 回复图片
     */
    private String shangpinChatReplyPhoto;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date replyTime;


    /**
     * 状态
     */
    private Integer zhuangtaiTypes;


    /**
     * 数据类型
     */
    private Integer shangpinChatTypes;


    /**
     * 提问时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：提问人
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：提问人
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：回答人
	 */
    public Integer getShangjiaId() {
        return shangjiaId;
    }


    /**
	 * 设置：回答人
	 */
    public void setShangjiaId(Integer shangjiaId) {
        this.shangjiaId = shangjiaId;
    }
    /**
	 * 获取：问题
	 */
    public String getShangpinChatIssueText() {
        return shangpinChatIssueText;
    }


    /**
	 * 设置：问题
	 */
    public void setShangpinChatIssueText(String shangpinChatIssueText) {
        this.shangpinChatIssueText = shangpinChatIssueText;
    }
    /**
	 * 获取：问题图片
	 */
    public String getShangpinChatIssuePhoto() {
        return shangpinChatIssuePhoto;
    }


    /**
	 * 设置：问题图片
	 */
    public void setShangpinChatIssuePhoto(String shangpinChatIssuePhoto) {
        this.shangpinChatIssuePhoto = shangpinChatIssuePhoto;
    }
    /**
	 * 获取：问题时间
	 */
    public Date getIssueTime() {
        return issueTime;
    }


    /**
	 * 设置：问题时间
	 */
    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }
    /**
	 * 获取：回复
	 */
    public String getShangpinChatReplyText() {
        return shangpinChatReplyText;
    }


    /**
	 * 设置：回复
	 */
    public void setShangpinChatReplyText(String shangpinChatReplyText) {
        this.shangpinChatReplyText = shangpinChatReplyText;
    }
    /**
	 * 获取：回复图片
	 */
    public String getShangpinChatReplyPhoto() {
        return shangpinChatReplyPhoto;
    }


    /**
	 * 设置：回复图片
	 */
    public void setShangpinChatReplyPhoto(String shangpinChatReplyPhoto) {
        this.shangpinChatReplyPhoto = shangpinChatReplyPhoto;
    }
    /**
	 * 获取：回复时间
	 */
    public Date getReplyTime() {
        return replyTime;
    }


    /**
	 * 设置：回复时间
	 */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
    /**
	 * 获取：状态
	 */
    public Integer getZhuangtaiTypes() {
        return zhuangtaiTypes;
    }


    /**
	 * 设置：状态
	 */
    public void setZhuangtaiTypes(Integer zhuangtaiTypes) {
        this.zhuangtaiTypes = zhuangtaiTypes;
    }
    /**
	 * 获取：数据类型
	 */
    public Integer getShangpinChatTypes() {
        return shangpinChatTypes;
    }


    /**
	 * 设置：数据类型
	 */
    public void setShangpinChatTypes(Integer shangpinChatTypes) {
        this.shangpinChatTypes = shangpinChatTypes;
    }
    /**
	 * 获取：提问时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：提问时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
