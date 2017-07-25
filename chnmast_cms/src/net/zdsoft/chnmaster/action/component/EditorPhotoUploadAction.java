package net.zdsoft.chnmaster.action.component;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.action.common.CmsBaseAction;
import net.zdsoft.chnmaster.config.Config;
import net.zdsoft.chnmaster.service.component.EditorPhotoUploadService;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.enums.FileType;
import net.zdsoft.common.upload.RequestFileHandler;
import net.zdsoft.common.upload.SimpleFileValidator;
import net.zdsoft.common.upload.UploadFile;
import net.zdsoft.common.utils.UploadUtil;
import net.zdsoft.keel.util.DateUtils;
import net.zdsoft.keel.util.FileUtils;
import net.zdsoft.keel.util.Validators;

/**
 * 编辑器照片上传的action
 *
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller
@SuppressWarnings("serial")
public class EditorPhotoUploadAction extends CmsBaseAction {

    EditorPhotoUploadService editorPhotoUploadService;

    private String uploadPhotoResult;
    private String loadPhotoesResult;

    private String path;// 文件路径
    private String order;// 按文件名称 NAME ，文件大小 SIZE，文件类型TYPE 排序

    public String uploadPhoto() {
        String error = "";
        String photoUrl = "";
        try {
            List<UploadFile> files = RequestFileHandler.handleFiles(getRequest(), new PhotoFileValidator());
            if (CollectionUtils.isEmpty(files)) {
                error = "获得的文件为空";
            }
            else {
                photoUrl = editorPhotoUploadService.uploadPhoto(getUser().getId(), files.get(0));
            }
        }
        catch (Throwable e) {
            log.error("编辑器上传图片失败", e);
            error = "编辑器上传图片失败：" + e.getMessage();
        }

        JSONObject obj = new JSONObject();
        if (Validators.isBlank(error)) {
            obj.put("error", 0);
            obj.put("url", photoUrl);
        }
        else {
            obj.put("error", 1);
            obj.put("message", error);
        }
        uploadPhotoResult = obj.toString();
        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String loadPhotoes() {
        // 编辑器上传图片磁盘路径
        String photoPath = FileType.USER_FILE.getStoreDir() + UploadUtil.getEditorFolder();
        // 编辑器上传图片URL路径
        String photoAbsoluteUrl = Config.getParam(BaseConstants.DOMAIN_CMS) + UploadUtil.getEditorFolder();
        // 图片扩展名
        String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
        String currentPath = photoPath + path;
        String currentUrl = photoAbsoluteUrl + path;
        String currentDirPath = path;
        String moveupDirPath = "";
        if (!"".equals(path)) {
            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
            moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }
        // 不允许使用..移动到上一级目录
        if (path.indexOf("..") >= 0) {
            loadPhotoesResult = "Access is not allowed.";
            return SUCCESS;
        }
        // // 最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            loadPhotoesResult = "Parameter is not valid.";
            return SUCCESS;
        }
        // 目录不存在或不是目录
        File currentPathFile = new File(currentPath);
        if (!currentPathFile.isDirectory()) {
            loadPhotoesResult = "编辑器上传图片目录不存在或不是目录.";
            return SUCCESS;
        }
        // 遍历目录取的文件信息
        List<Hashtable> fileList = new ArrayList<Hashtable>();
        if (currentPathFile.listFiles() != null) {
            for (File file : currentPathFile.listFiles()) {
                Hashtable<String, Object> hash = new Hashtable<String, Object>();
                String fileName = file.getName();
                if (file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                }
                else if (file.isFile()) {
                    String fileExt = FileUtils.getExtension(fileName).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.<String> asList(fileTypes).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", DateUtils.date2StringBySecond(new Date(file.lastModified())));
                fileList.add(hash);
            }
        }
        if ("size".equals(order)) {
            Collections.sort(fileList, new SizeComparator());
        }
        else if ("type".equals(order)) {
            Collections.sort(fileList, new TypeComparator());
        }
        else {
            Collections.sort(fileList, new NameComparator());
        }
        JSONObject result = new JSONObject();
        result.put("moveup_dir_path", moveupDirPath);
        result.put("current_dir_path", currentDirPath);
        result.put("current_url", currentUrl);
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);
        loadPhotoesResult = result.toString();
        return SUCCESS;
    }

    class PhotoFileValidator extends SimpleFileValidator {

        @Override
        public Set<String> getAvailableSuffixes() {
            Set<String> suffix = new HashSet<String>();

            suffix.add("jpg");
            suffix.add("jpeg");
            suffix.add("png");
            suffix.add("bmp");
            suffix.add("gif");

            return suffix;
        }

        @Override
        public long getMaxSize() {
            return 3 * 1024 * 1024;
        }

    }

    @SuppressWarnings("unchecked")
    private class NameComparator implements Comparator {
        @Override
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable) a;
            Hashtable hashB = (Hashtable) b;
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            }
            else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            }
            else {
                return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private class SizeComparator implements Comparator {
        @Override
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable) a;
            Hashtable hashB = (Hashtable) b;
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            }
            else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            }
            else {
                if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
                    return 1;
                }
                else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private class TypeComparator implements Comparator {
        @Override
        public int compare(Object a, Object b) {
            Hashtable hashA = (Hashtable) a;
            Hashtable hashB = (Hashtable) b;
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            }
            else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            }
            else {
                return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
            }
        }
    }

    public String getUploadPhotoResult() {
        return uploadPhotoResult;
    }

    public String getLoadPhotoesResult() {
        return loadPhotoesResult;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setEditorPhotoUploadService(EditorPhotoUploadService editorPhotoUploadService) {
        this.editorPhotoUploadService = editorPhotoUploadService;
    }

}
