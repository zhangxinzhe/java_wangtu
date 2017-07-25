package net.zdsoft.common.upload;

import net.zdsoft.common.enums.FileType;
import net.zdsoft.common.enums.PathType;


public abstract class AbstractUserFilePathBuilder extends FilePathBuilder{

	public AbstractUserFilePathBuilder(long agencyId, PathType pathType, long id, String suffix, String extName) {
        super(agencyId, pathType, id, suffix, extName);
    }

    public abstract String buildFilePath();

	public FileType fileType() {
		return FileType.USER_FILE;
	}

}
