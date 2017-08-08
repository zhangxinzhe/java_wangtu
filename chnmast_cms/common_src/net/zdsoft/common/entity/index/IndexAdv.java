package net.zdsoft.common.entity.index;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.ContentType;
import net.zdsoft.common.enums.FieldType;
import net.zdsoft.common.enums.YesNoType;

/**
 * 首页轮播图片
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月27日 上午11:22:18 $
 */
public class IndexAdv extends BaseEntity {
    private static final long serialVersionUID = 2257723327500585311L;

    /**
     * 活动id(compete_id)
     */
    private long competeId;

    /**
     * 来源（0PC版，1手机版）
     */
    private int advSource;

    /**
     * 标题
     */
    private String title;
    /**
     * 栏目类型（0轮播图片，1精彩视频，2伴奏音频，3活动专题-轮播图片）
     */
    private FieldType fieldType;
    /**
     * 分类id
     */
    private long advTypeId;
    /**
     * 内容类型（0无，1外部连接，2编辑内容，3视频，4音频）
     */
    private ContentType contentType;
    /**
     * 链接地址
     */
    private String linkUrl;
    /**
     * 文件路径
     */
    private String localFile;
    /**
     * 是否显示（0不显示，1显示）
     */
    private YesNoType isShow = YesNoType.NO;
    /**
     * 图片路径
     */
    private String pictureFile;
    /**
     * 显示次序
     */
    private int displayOrder;

    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 备注/姓名
     */
    private String remark;

    ////////////////////////// 辅助字段 /////////////////////////
    private String advTypeTitle;// 分类内容
    private String pictureFileForMobile; // 手机端图片路径

    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Returns the fieldType.
     */
    public FieldType getFieldType() {
        return fieldType;
    }

    /**
     * @param fieldType
     *            The fieldType to set.
     */
    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * @return Returns the contentType.
     */
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * @param contentType
     *            The contentType to set.
     */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    /**
     * @return Returns the linkUrl.
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * @param linkUrl
     *            The linkUrl to set.
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
     * @return Returns the localFile.
     */
    public String getLocalFile() {
        return localFile;
    }

    /**
     * @param localFile
     *            The localFile to set.
     */
    public void setLocalFile(String localFile) {
        this.localFile = localFile;
    }

    /**
     * @return Returns the isShow.
     */
    public YesNoType getIsShow() {
        return isShow;
    }

    /**
     * @param isShow
     *            The isShow to set.
     */
    public void setIsShow(YesNoType isShow) {
        this.isShow = isShow;
    }

    /**
     * @return Returns the pictureFile.
     */
    public String getPictureFile() {
        return pictureFile;
    }

    /**
     * @param pictureFile
     *            The pictureFile to set.
     */
    public void setPictureFile(String pictureFile) {
        this.pictureFile = pictureFile;
    }

    /**
     * @return Returns the displayOrder.
     */
    public int getDisplayOrder() {
        return displayOrder;
    }

    /**
     * @param displayOrder
     *            The displayOrder to set.
     */
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * @return Returns the createDate.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     *            The createDate to set.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getAdvTypeId() {
        return advTypeId;
    }

    public void setAdvTypeId(long advTypeId) {
        this.advTypeId = advTypeId;
    }

    public String getAdvTypeTitle() {
        return advTypeTitle;
    }

    public void setAdvTypeTitle(String advTypeTitle) {
        this.advTypeTitle = advTypeTitle;
    }

    /**
     * @return Returns the pictureFileForMobile.
     */
    public String getPictureFileForMobile() {
        return pictureFileForMobile;
    }

    /**
     * @param pictureFileForMobile
     *            The pictureFileForMobile to set.
     */
    public void setPictureFileForMobile(String pictureFileForMobile) {
        this.pictureFileForMobile = pictureFileForMobile;
    }

    public long getCompeteId() {
        return competeId;
    }

    public void setCompeteId(long competeId) {
        this.competeId = competeId;
    }

    public int getAdvSource() {
        return advSource;
    }

    public void setAdvSource(int advSource) {
        this.advSource = advSource;
    }

}
