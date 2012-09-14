package com.alvazan.test;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alvazan.orm.api.base.Bootstrap;
import com.alvazan.orm.api.base.DbTypeEnum;
import com.alvazan.orm.api.base.NoSqlEntityManagerFactory;

public class FactorySingleton {

	private static final Logger log = LoggerFactory.getLogger(FactorySingleton.class);
	
	private static NoSqlEntityManagerFactory factory;
	
	public synchronized static NoSqlEntityManagerFactory createFactoryOnce() {
		if(factory == null) {
			/**************************************************
			 * FLIP THIS BIT TO CHANGE FROM CASSANDRA TO ANOTHER ONE
			 **************************************************/
			String clusterName = "PlayCluster";
			DbTypeEnum serverType = DbTypeEnum.CASSANDRA;
			String seeds = "localhost:9160";
			//We used this below commented out seeds to test our suite on a cluster of 6 nodes to see if any issues pop up with more
			//nodes using the default astyanax consistency levels which I believe for writes and reads are both QOURUM
			//which is perfect for us as we know we will get the latest results
			//String seeds = "a1.bigde.nrel.gov:9160,a2.bigde.nrel.gov:9160,a3.bigde.nrel.gov:9160";
			createFactory(serverType, clusterName, seeds);
		}
		return factory;
	}

	private static void createFactory(DbTypeEnum server, String clusterName, String seeds) {
		log.info("CREATING FACTORY FOR TESTS");
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(Bootstrap.AUTO_CREATE_KEY, "create");
		switch (server) {
		case IN_MEMORY:
			//nothing to do
			break;
		case CASSANDRA:
			Bootstrap.createAndAddBestCassandraConfiguration(props, clusterName, "PlayOrmKeyspace", seeds);
			break;
		default:
			throw new UnsupportedOperationException("not supported yet, server type="+server);
		}

		factory = Bootstrap.create(server, props, null, null);
	}
	
}
