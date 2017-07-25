package net.zdsoft.chnmaster.exoperate.common;

import java.util.Map;

import org.springframework.stereotype.Component;

import net.zdsoft.common.exoperate.ExoperateProcessor;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.upload.FilePathBuilder;

/**
 * 增删改富文本编辑器内容
 *
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2014-6-2 上午9:15:04 $
 */
@Component
public class OperateEditorUploadFileProcessor implements ExoperateProcessor {
    @SuppressWarnings("rawtypes")
    @Override
    public void process(String operateType, Map values) {
        String operate = String.valueOf(values.get("OPERATE"));
        if ("a".equals(operate)) { // 新增
            try {
                FilePathBuilder build = (FilePathBuilder) values.get("FILEBUILD");
                String content = String.valueOf(values.get("CONTENT"));
                Object temp = values.get("IS_BUILD2");// IS_BUILD2：布尔类型，可不传
                boolean isBuild2 = false;
                if (temp != null) {
                    isBuild2 = (Boolean) temp;
                }
                String filePath = build.buildUploadFilePath();
                if (isBuild2) {
                    filePath = build.buildUploadFilePath2();
                }
                FileSystemUtil.saveFile(content, filePath);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if ("e".equals(operate)) { // 编辑
            try {
                String filepath = String.valueOf(values.get("FILEPATH"));
                String content = String.valueOf(values.get("CONTENT"));
                FileSystemUtil.saveFile(content, filepath);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if ("d".equals(operate)) { // 删除附件
            try {
                String path = String.valueOf(values.get("PATH"));
                FileSystemUtil.deleteFile(path);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
