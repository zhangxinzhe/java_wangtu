package net.zdsoft.chnmaster.action.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import net.zdsoft.common.entity.PageDto;
import net.zdsoft.common.utils.URLUtil;

/**
 * 分页功能的action
 *
 * @author fangb
 *
 */
@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
public class CmsPageAction extends CmsBaseAction {

    private PageDto page;
    private PageBuilder pageBuilder;

    public CmsPageAction() {
        this.page = new PageDto();
    }

    public PageDto getPage() {
        return this.page;
    }

    public void setPage(PageDto page) {
        this.page = page;
    }

    private static boolean needRowNum;// 包含每页条数

    /**
     * 得到分页链接的各个元素
     *
     * @return
     */
    public List<PageItem> getPageItems() {
        needRowNum = false;
        if (pageBuilder == null) {
            pageBuilder = new PageBuilder();
        }
        return pageBuilder.buildItems(false);
    }

    /**
     * 得到分页链接的各个元素(包含每页条数)
     *
     * @return
     */
    public List<PageItem> getPageItems2() {
        needRowNum = true;
        if (pageBuilder == null) {
            pageBuilder = new PageBuilder();
        }
        return pageBuilder.buildItems(true);
    }

    public Map getParameters() {
        if (pageBuilder == null) {
            pageBuilder = new PageBuilder();
        }
        return pageBuilder.getParameters();
    }

    /**
     * 得到发起请求的绝对路径
     *
     * @return
     */
    public String getAbsoluteUrl() {
        return getRequest().getServletPath();
    }

    public class PageBuilder {
        private static final String BLANK_HREF = "javascript:void(0);";
        private static final String PAGE_PARAM_CURRENT = "page.currentPage";
        private static final String PAGE_PARAM_ROW = "page.rowNum";
        private static final String PAGE_PARAM_TOTAL = "page.totalNum";

        private static final String HEAD_TEXT = "首页";
        private static final String PREVIOUS_TEXT = "上一页";
        private static final String NEXT_TEXT = "下一页";
        private static final String FOOT_TEXT = "尾页";

        private final String actionName;
        // 组装后的请求路径，附加了请求的参数，对于中文参数，按照UTF-8的格式进行了转码
        private final String constructedUrl;

        public PageBuilder() {
            actionName = ActionContext.getContext().getActionInvocation().getProxy().getActionName();
            this.constructedUrl = URLUtil.addQueryString(getAbsoluteUrl(), getParameters());
        }

        private Map getParameters() {
            Map parameters = new HashMap();
            parameters.putAll(getRequest().getParameterMap());
            if (needRowNum) {
                parameters.remove(PAGE_PARAM_ROW);
            }
            parameters.remove(PAGE_PARAM_CURRENT);
            parameters.remove(PAGE_PARAM_TOTAL);

            return parameters;
        }

        private String getUrl(int pageIndex) {
            String url = URLUtil.addQueryString(constructedUrl, PAGE_PARAM_CURRENT, Integer.valueOf(pageIndex));
            return url;
        }

        private boolean isIgnoreAction() {
            return (actionName.equals("addStudent") || actionName.equals("addTeacher"));
        }

        public List<PageItem> buildItems(boolean flag) {
            List<PageItem> items = new ArrayList<PageItem>();

            boolean isIgnore = isIgnoreAction();
            int totalPage = page.getTotalNum() / page.getRowNum();
            if (page.getTotalNum() % page.getRowNum() != 0) {
                totalPage++;
            }

            // if (!isIgnore) {
            // 总记录数
            items.add(new PageItem("<span>共" + page.getTotalNum() + "条" + totalPage + "页</span>", BLANK_HREF));

            // 第几页
            items.add(new PageItem("<span class='ml-10'>第" + page.getCurrentPage() + "页</span>", BLANK_HREF));

            if (flag) {
                // 每页条数
                if (needRowNum) {
                    items.add(new PageItem(
                            "<span class='ml-10'>每页<INPUT type='text' style='vertical-align: middle;' class='txt' maxLength=3 "
                                    + "onkeydown='if(event.keyCode ==13){gotoPage(\"" + getUrl(page.getCurrentPage())
                                    + "\"," + page.getRowNum() + "," + page.getCurrentPage() + "," + page.getTotalNum()
                                    + ");}' " + "id='sizePerPage' value='" + page.getRowNum() + "' />条</span>",
                            BLANK_HREF));
                }

                // 页码选择
                int maxL = (page.getTotalPage() + "").length();
                items.add(new PageItem(
                        "<span class='ml-10'>跳转到<input type='text' id='currentPage' class='txt' maxLength='" + maxL
                                + "'" + " onkeydown='if(event.keyCode ==13){gotoPage(\"" + getUrl(page.getCurrentPage())
                                + "\"," + page.getRowNum() + "," + page.getCurrentPage() + "," + page.getTotalNum()
                                + ");}' " + "value='" + page.getCurrentPage() + "' />页</span>",
                        BLANK_HREF));
            }
            // 首页
            if (isHasHead()) {
                items.add(new PageItem(HEAD_TEXT, "gotoPage('" + getUrl(1) + "')"));
            }
            else {
                items.add(new PageItem(HEAD_TEXT, BLANK_HREF));
            }
            // }

            // 上一页
            if (isHasPrevious()) {
                items.add(new PageItem(PREVIOUS_TEXT, "gotoPage('" + getUrl(page.getCurrentPage() - 1) + "')"));
            }
            else {
                items.add(new PageItem(PREVIOUS_TEXT, BLANK_HREF));
            }

            // 下一页和最后一页
            if (isHasNext()) {
                items.add(new PageItem(NEXT_TEXT, "gotoPage('" + getUrl(page.getCurrentPage() + 1) + "')"));
                // if (!isIgnore) {
                items.add(new PageItem(FOOT_TEXT, "gotoPage('" + getUrl(totalPage) + "')"));
                // }
            }
            else {
                items.add(new PageItem(NEXT_TEXT, BLANK_HREF));
                // if (!isIgnore) {
                items.add(new PageItem(FOOT_TEXT, BLANK_HREF));
                // }
            }

            return items;
        }

        /**
         * 是否有上一页
         *
         * @return
         */
        public boolean isHasPrevious() {
            return page.getCurrentPage() != 1;
        }

        /**
         * 是否有下一页
         *
         * @return
         */
        public boolean isHasNext() {
            int totalPage = page.getTotalNum() / page.getRowNum();
            if (page.getTotalNum() % page.getRowNum() != 0) {
                totalPage++;
            }

            return page.getCurrentPage() < totalPage;
        }

        /**
         * 是否有首页
         *
         * @return
         */
        public boolean isHasHead() {
            return page.getCurrentPage() > 1;
        }

    }

    public class PageItem {
        private String text;
        private String href;

        public PageItem(String text, String href) {
            this.text = text;
            this.href = href;
        }

        /**
         * 是否有链接可以跳转
         *
         * @return
         */
        public boolean isHasHref() {
            return !PageBuilder.BLANK_HREF.equals(this.href);
        }

        /**
         * 是否是首页尾页上下页
         *
         * @return
         */
        public boolean isPages() {
            return (PageBuilder.HEAD_TEXT.equals(this.text) || PageBuilder.PREVIOUS_TEXT.equals(this.text)
                    || PageBuilder.NEXT_TEXT.equals(this.text) || PageBuilder.FOOT_TEXT.equals(this.text));
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
