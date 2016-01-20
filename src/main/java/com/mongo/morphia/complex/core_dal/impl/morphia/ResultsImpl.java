package com.mongo.morphia.complex.core_dal.impl.morphia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.morphia.Key;
import com.google.code.morphia.query.MorphiaIterator;
import com.google.code.morphia.query.Query;
import com.mongo.morphia.complex.common.StringUtils;
import com.mongo.morphia.complex.core.BObject;
import com.mongo.morphia.complex.core_dal.Results;
import com.mongo.morphia.complex.core_dal.dao.LongQueryConfigDAO;
import com.mongo.morphia.complex.core_dal.dao.LongQueryDAO;
import com.mongo.morphia.complex.core_dal.dao.impl.LongQueryConfigDAOImpl;
import com.mongo.morphia.complex.core_dal.dao.impl.LongQueryDAOImpl;
import com.mongo.morphia.complex.core_dal.entity.LongQuery;
import com.mongo.morphia.complex.core_dal.entity.LongQueryConfig;
import com.mongodb.ServerAddress;

@SuppressWarnings("unchecked")
public class ResultsImpl<E extends BObject> implements Results<E> {
    private static Logger logger = LoggerFactory.getLogger(ResultsImpl.class);
    private static LongQueryConfigDAO lqcDAO = new LongQueryConfigDAOImpl();
    private static LongQueryDAO lqDAO = new LongQueryDAOImpl();

    private Query<E> query;

    private Iterator<E> it;
    private MongoHelperImpl mongoHelper;

    public ResultsImpl(Query<E> query, MongoHelperImpl mongoHelper) {
        this.query = query;
        this.mongoHelper = mongoHelper;
    }

    @Override
    public List<E> asList() {
        List<E> list = new ArrayList<E>();
        while (hasNext()) {
            list.add(next());
        }
        return list;
    }

    public String asIds() {
        StringBuffer sb = new StringBuffer();
        Iterator<Key<E>> it = query.fetchKeys().iterator();
        while (it.hasNext()) {
            sb.append(it.next().getId());
            sb.append(",");
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        MorphiaIterator<E, E> mi = (MorphiaIterator<E, E>) query.iterator();
        it = new MorphiaIteratorgl<E, E>(mongoHelper, mi);
        return new MorphiaIteratorgl<E, E>(mongoHelper, (MorphiaIterator<E, E>) query.iterator());
    }

    @Override
    public boolean hasNext() {
        Date start = null;
        LongQueryConfig lqc = lqcDAO.getLongQueryConfig();
        boolean open = lqc.isOpen()
                && !this.mongoHelper.getEntityClass().getSimpleName().equals(LongQuery.class.getSimpleName())
                && !this.mongoHelper.getEntityClass().getSimpleName().equals(LongQueryConfig.class.getSimpleName());
        if (open) {
            start = new Date();
        }
        try {
            if (it == null) {
                MorphiaIterator<E, E> mi = (MorphiaIterator<E, E>) query.iterator();
                it = new MorphiaIteratorgl<E, E>(mongoHelper, mi);
            }
            if (it == null)
                return false;
            else
                return it.hasNext();
        } finally {
            if (open) {
                Date end = new Date();
                try {
                    if (end.getTime() - start.getTime() > lqc.getTime()) {
                        LongQuery lq = new LongQuery();
                        StringBuilder str = new StringBuilder();
                        List<ServerAddress> list = this.mongoHelper.getMongo().getServerAddressList();
                        if (list != null) {
                            for (ServerAddress addr : list) {
                                str.append(
                                        addr.getSocketAddress().getAddress().getHostAddress() + ":"
                                                + addr.getSocketAddress().getPort()).append(',');
                            }
                            str.deleteCharAt(str.length() - 1);
                        }
                        lq.setUrl(str.toString());
                        lq.setReadPreference(this.mongoHelper.getDb().getReadPreference().getName());
                        lq.setCollName(this.mongoHelper.getDb().getName() + "." + this.mongoHelper.getCollectionName());
                        lq.setQuery(query.toString());
                        lq.setStart(start);
                        lq.setCost(end.getTime() - start.getTime());
                        lq.setStackTrace(StringUtils.stackToString(new Exception()));
                        lqDAO.save(lq);
                    }
                } catch (Throwable t) {
                    logger.warn(t.getMessage(), t);
                }
            }
        }
    }

    @Override
    public E next() {
        Date start = null;
        LongQueryConfig lqc = lqcDAO.getLongQueryConfig();
        boolean open = lqc.isOpen()
                && !this.mongoHelper.getEntityClass().getSimpleName().equals(LongQuery.class.getSimpleName())
                && !this.mongoHelper.getEntityClass().getSimpleName().equals(LongQueryConfig.class.getSimpleName());
        if (open) {
            start = new Date();
        }
        try {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                E e = it.next();
                return e;
            }
        } finally {
            if (open) {
                Date end = new Date();
                try {
                    if (end.getTime() - start.getTime() > lqc.getTime()) {
                        LongQuery lq = new LongQuery();
                        lq.setCollName(this.mongoHelper.getDb().getName() + "." + this.mongoHelper.getCollectionName());
                        lq.setQuery(query.toString());
                        lq.setStart(start);
                        lq.setCost(end.getTime() - start.getTime());
                        lq.setStackTrace(StringUtils.stackToString(new Exception()));
                        lqDAO.save(lq);
                    }
                } catch (Throwable t) {
                    logger.warn(t.getMessage(), t);
                }
            }
        }
    }

    @Override
    public void remove() {
        if (it != null) {
            it.remove();
        }
    }
}
