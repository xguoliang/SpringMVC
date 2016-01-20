/**
 *
 * 
 * 修订历史记录：
 * 
 * Revision	1.0	 2010-11-17  guolei_mao  创建。
 * 
 */

package com.mongo.morphia.complex.core_dal.impl.morphia;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.mongo.morphia.complex.core.BObject;
import com.mongo.morphia.complex.core_dal.Updates;
import com.mongo.morphia.complex.core_dal.UpdatesOperation;
import com.mongo.morphia.complex.core_dal.impl.UpdatesOperationImpl;
import com.mongodb.DBRef;


/**
 * 类说明:
 * 
 * @author <a href="mailto:guolei_mao@kingdee.com">guolei_mao</a>
 * @version 1.0, 2010-11-17
 */
public class UpdatesImpl extends com.mongo.morphia.complex.core_dal.impl.UpdatesImpl {
    private static final long serialVersionUID = -2543156115933246549L;
    private MongoHelperImpl mongoHelper;

    public UpdatesImpl(MongoHelperImpl mongoHelper) {
        this.mongoHelper = mongoHelper;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Updates set(String field, Object value) {
        if (value instanceof BObject) {
            value = toDBRef((BObject) value);
        } else {
            List list = null;
            if (value.getClass().isArray())
                list = Arrays.asList(value);
            else if (value instanceof Collection) {
                list = new ArrayList();
                list.addAll((Collection) value);
            }
            if (list != null && list.size()>0 && (list.get(0) instanceof BObject)) {
                List<DBRef> refs = new ArrayList<DBRef>(list.size());
                for (Object object : list) {
                    refs.add(toDBRef((BObject) object));
                }
                value = refs;
            }
        }

        return super.set(field, value);
    }

    private DBRef toDBRef(BObject value) {
        String id = value.getId();
        DBRef ref = new DBRef(mongoHelper.getDb(), mongoHelper.getCollectionName(value.getClass()), id);
        return ref;
    }
}