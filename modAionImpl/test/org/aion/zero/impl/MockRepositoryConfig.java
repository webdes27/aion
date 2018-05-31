package org.aion.zero.impl;

import org.aion.base.db.IContractDetails;
import org.aion.base.db.IRepositoryConfig;
import org.aion.db.impl.DBVendor;
import org.aion.db.impl.DatabaseFactory;
import org.aion.zero.impl.db.ContractDetailsAion;

import java.util.Properties;

public class MockRepositoryConfig implements IRepositoryConfig {
    private DBVendor vendor = DBVendor.MOCKDB;

    @Override
    public String getDbPath() {
        return "";
    }

    @Override
    public int getPrune() {
        return 0;
    }

    @Override
    public IContractDetails contractDetailsImpl() {
        return ContractDetailsAion.createForTesting(0, 1000000).getDetails();
    }

    @Override
    public Properties getDatabaseConfig(String db_name) {
        Properties props = new Properties();
        props.setProperty(DatabaseFactory.Props.DB_TYPE, vendor.toValue());
        props.setProperty(DatabaseFactory.Props.ENABLE_HEAP_CACHE, "false");
        return props;
    }

    public MockRepositoryConfig(DBVendor vendor) {
        this.vendor = vendor;
    }
}