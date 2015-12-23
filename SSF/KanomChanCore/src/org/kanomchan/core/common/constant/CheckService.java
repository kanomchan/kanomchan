package org.kanomchan.core.common.constant;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;

public interface CheckService {
	public boolean checkNeedleList(String tableName, String columnName)throws RollBackException, NonRollBackException;
<<<<<<< HEAD
	public boolean checkTableLang(String tableName)throws RollBackException, NonRollBackException;
=======
>>>>>>> branch 'v1.4.0' of https://github.com/viatoro/kanomchan.git
	public boolean checkColumnNameInTableLang(String tableName, String columnName)throws RollBackException, NonRollBackException;
	public boolean checkClearableList(String tableName, String columnName)throws RollBackException, NonRollBackException;
	public boolean checkIncludeMinusOne(String name, String columnName)throws RollBackException, NonRollBackException;
}
