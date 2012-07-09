package com.alvazan.orm.layer2.nosql.cache;

import java.util.HashMap;
import java.util.Map;

import com.alvazan.orm.api.spi.layer2.MetaTableDbo;

public class InfoForWiring {

	private MetaTableDbo noAliasTable;
	private Map<String, MetaTableDbo> aliasToMeta = new HashMap<String, MetaTableDbo>();
	private boolean selectStarDefined;
	
	public void setNoAliasTable(MetaTableDbo metaClass) {
		this.noAliasTable = metaClass;
	}

	public MetaTableDbo getNoAliasTable() {
		return noAliasTable;
	}

	public void put(String alias, MetaTableDbo metaClass) {
		aliasToMeta.put(alias, metaClass);
	}

	public MetaTableDbo getInfoFromAlias(String alias) {
		return aliasToMeta.get(alias);
	}

	public void setSelectStarDefined(boolean defined) {
		selectStarDefined = defined;
	}

	public boolean isSelectStarDefined() {
		return selectStarDefined;
	}
	

}