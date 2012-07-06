package com.alvazan.orm.layer3.spi.index.inmemory;

import java.util.List;

import org.apache.lucene.store.RAMDirectory;

import com.alvazan.orm.impl.meta.MetaClass;
import com.alvazan.orm.impl.meta.MetaQuery;
import com.alvazan.orm.layer3.spi.index.SpiIndexQuery;

public class SpiIndexQueryImpl implements SpiIndexQuery {

	private RAMDirectory ramDir;

	@Override
	@SuppressWarnings("rawtypes")
	public void setParameter(String paraMeterName, Object value) {

	}

	@Override
	public List getResultList() {
		//query the ram directory.  do we need to synchronize on the MetaClass
		//as any query on MetaClass can query this one index.  do not synchronize
		//on this class because there is one instace for each active query on
		//the same thread(some queries may be the same)
		
		
		return null;
	}

	public void setup(MetaClass clazz, MetaQuery info, RAMDirectory ramDir) {
		this.ramDir = ramDir;
	}

}