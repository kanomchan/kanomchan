<<<<<<< HEAD

package org.kanomchan.core.common.constant;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;

public interface CheckService {
	public boolean checkNeedleList(String tableName, String columnName)throws RollBackException, NonRollBackException;
	public boolean checkTableLang(String tableName)throws RollBackException, NonRollBackException;
	public boolean checkColumnNameInTableLang(String tableName, String columnName)throws RollBackException, NonRollBackException;
	public boolean checkClearableList(String tableName, String columnName)throws RollBackException, NonRollBackException;
	public boolean checkIncludeMinusOne(String name, String columnName)throws RollBackException, NonRollBackException;
}
=======
package org.kanomchan.core.common.constant;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;

public interface CheckService {
	public boolean checkNeedleList(String tableName, String columnName)throws RollBackException, NonRollBackException;
	public boolean checkTableLang(String tableName)throws RollBackException, NonRollBackException;
	public boolean checkColumnNameInTableLang(String tableName, String columnName)throws RollBackException, NonRollBackException;
	public boolean checkClearableList(String tableName, String columnName)throws RollBackException, NonRollBackException;
	public boolean checkIncludeMinusOne(String name, String columnName)throws RollBackException, NonRollBackException;
}
>>>>>>> branch 'master' of https://github.com/viatoro/kanomchan.git
