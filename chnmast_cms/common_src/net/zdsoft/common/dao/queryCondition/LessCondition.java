/* 
 * @(#)TmsLessCondition.java    Created on 2011-2-15
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.dao.queryCondition;

import java.io.Serializable;
import java.sql.Types;
import java.util.Date;

/**
 * @author dingw
 * @version $Revision: 1.0 $, $Date: 2011-2-15 上午11:16:29 $
 */
@SuppressWarnings("serial")
public class LessCondition implements QueryCondition, Serializable {
	private final String column;
	private final Date value;

	/**
     * 
     */
	public LessCondition(String column, Date value) {
		this.column = column;
		this.value = value;
	}

	@Override
	public String getCondition() {
		return column + "<" + "?";
	}

	@Override
	public Object[] getParameters() {
		return new Object[] { value };
	}

	@Override
	public int[] getTypes() {
		return new int[] { Types.TIMESTAMP };
	}

}
