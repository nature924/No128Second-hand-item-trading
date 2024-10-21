package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 商家
 *
 * @author 
 * @email
 */
@TableName("shangjia")
public class ShangjiaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShangjiaEntity() {

	}

	public ShangjiaEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 商家名称
     */
    @ColumnInfo(comment="商家名称",type="varchar(200)")
    @TableField(value = "shangjia_name")

    private String shangjiaName;


    /**
     * 联系方式
     */
    @ColumnInfo(comment="联系方式",type="varchar(200)")
    @TableField(value = "shangjia_phone")

    private String shangjiaPhone;


    /**
     * 邮箱
     */
    @ColumnInfo(comment="邮箱",type="varchar(200)")
    @TableField(value = "shangjia_email")

    private String shangjiaEmail;


    /**
     * 营业执照展示
     */
    @ColumnInfo(comment="营业执照展示",type="varchar(200)")
    @TableField(value = "shangjia_photo")

    private String shangjiaPhoto;


    /**
     * 商家信用类型
     */
    @ColumnInfo(comment="商家信用类型",type="int(11)")
    @TableField(value = "shangjia_xingji_types")

    private Integer shangjiaXingjiTypes;


    /**
     * 现有余额
     */
    @ColumnInfo(comment="现有余额",type="decimal(10,2)")
    @TableField(value = "new_money")

    private Double newMoney;


    /**
     * 商家介绍
     */
    @ColumnInfo(comment="商家介绍",type="longtext")
    @TableField(value = "shangjia_content")

    private String shangjiaContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "shangjia_delete")

    private Integer shangjiaDelete;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 设置：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 设置：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：商家名称
	 */
    public String getShangjiaName() {
        return shangjiaName;
    }
    /**
	 * 设置：商家名称
	 */

    public void setShangjiaName(String shangjiaName) {
        this.shangjiaName = shangjiaName;
    }
    /**
	 * 获取：联系方式
	 */
    public String getShangjiaPhone() {
        return shangjiaPhone;
    }
    /**
	 * 设置：联系方式
	 */

    public void setShangjiaPhone(String shangjiaPhone) {
        this.shangjiaPhone = shangjiaPhone;
    }
    /**
	 * 获取：邮箱
	 */
    public String getShangjiaEmail() {
        return shangjiaEmail;
    }
    /**
	 * 设置：邮箱
	 */

    public void setShangjiaEmail(String shangjiaEmail) {
        this.shangjiaEmail = shangjiaEmail;
    }
    /**
	 * 获取：营业执照展示
	 */
    public String getShangjiaPhoto() {
        return shangjiaPhoto;
    }
    /**
	 * 设置：营业执照展示
	 */

    public void setShangjiaPhoto(String shangjiaPhoto) {
        this.shangjiaPhoto = shangjiaPhoto;
    }
    /**
	 * 获取：商家信用类型
	 */
    public Integer getShangjiaXingjiTypes() {
        return shangjiaXingjiTypes;
    }
    /**
	 * 设置：商家信用类型
	 */

    public void setShangjiaXingjiTypes(Integer shangjiaXingjiTypes) {
        this.shangjiaXingjiTypes = shangjiaXingjiTypes;
    }
    /**
	 * 获取：现有余额
	 */
    public Double getNewMoney() {
        return newMoney;
    }
    /**
	 * 设置：现有余额
	 */

    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
    }
    /**
	 * 获取：商家介绍
	 */
    public String getShangjiaContent() {
        return shangjiaContent;
    }
    /**
	 * 设置：商家介绍
	 */

    public void setShangjiaContent(String shangjiaContent) {
        this.shangjiaContent = shangjiaContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getShangjiaDelete() {
        return shangjiaDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setShangjiaDelete(Integer shangjiaDelete) {
        this.shangjiaDelete = shangjiaDelete;
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

    @Override
    public String toString() {
        return "Shangjia{" +
            ", id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", shangjiaName=" + shangjiaName +
            ", shangjiaPhone=" + shangjiaPhone +
            ", shangjiaEmail=" + shangjiaEmail +
            ", shangjiaPhoto=" + shangjiaPhoto +
            ", shangjiaXingjiTypes=" + shangjiaXingjiTypes +
            ", newMoney=" + newMoney +
            ", shangjiaContent=" + shangjiaContent +
            ", shangjiaDelete=" + shangjiaDelete +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
