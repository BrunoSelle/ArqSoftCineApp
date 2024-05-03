/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datasys.oraclefactory;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import oracle.ucp.admin.UniversalConnectionPoolManager;
import oracle.ucp.admin.UniversalConnectionPoolManagerImpl;
import oracle.ucp.jdbc.PoolDataSourceImpl;
import com.cineapp.utils.UCipher;

/**
 *
 * @author Giuliano
 */
public class DatasysOracleUcpFactory implements ObjectFactory {
    private static Logger log = Logger.getLogger("com.datasys.oraclefactory.DatasysOracleUcpFactory");
    private PoolDataSourceImpl pds;

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        log.info("DatasysOracleUcpFactory.getObjectInstance chamado");
        pds = new PoolDataSourceImpl();

        UniversalConnectionPoolManager ucpManager = UniversalConnectionPoolManagerImpl.getUniversalConnectionPoolManager();
        ucpManager.setJmxEnabled(false);
        
        log.info("UCP PoolDataSource passou");
        
        Reference ref = (Reference)obj;
        Enumeration<RefAddr> addrs = ref.getAll();
        while (addrs.hasMoreElements()) {
            RefAddr addr = (RefAddr)addrs.nextElement();
            String propName = addr.getType();
            String propValue = (String)addr.getContent();
            if (propName.toLowerCase().equals("connectionfactoryclassname")) {
                pds.setConnectionFactoryClassName(propValue);
            }else if (propName.toLowerCase().equals("url")) {
                pds.setURL(propValue);
            }else if (propName.toLowerCase().equals("user")) {
                pds.setUser(propValue);
            }else if (propName.toLowerCase().equals("password")) {
                pds.setPassword(UCipher.decifra(propValue, UCipher.CHAVE));
            }else if (propName.toLowerCase().equals("connectionpoolname")) {
                pds.setConnectionPoolName(propValue);
            }else if (propName.toLowerCase().equals("minpoolsize")) {
                pds.setMinPoolSize(Integer.parseInt(propValue));
            }else if (propName.toLowerCase().equals("maxpoolsize")) {
                pds.setMaxPoolSize(Integer.parseInt(propValue));
            }else if (propName.toLowerCase().equals("initialpoolsize")) {
                pds.setInitialPoolSize(Integer.parseInt(propValue));
            }else if (propName.toLowerCase().equals("inactiveconnectiontimeout")) {
                pds.setInactiveConnectionTimeout(Integer.parseInt(propValue));
            }
        }
        pds.setConnectionWaitTimeout(40);
        pds.setAbandonedConnectionTimeout(0);
        ucpManager.createConnectionPool(pds);        
        log.info("UCP PooledDataSource passou por aqui!");
        return pds;
    }
}
