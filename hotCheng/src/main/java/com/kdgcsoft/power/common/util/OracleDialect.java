package com.kdgcsoft.power.common.util;
import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.type.StandardBasicTypes;

public class OracleDialect extends Oracle10gDialect {
	public OracleDialect(){  
        super();  

        registerHibernateType(Types.CHAR, StandardBasicTypes.STRING.getName());  
        registerHibernateType(Types.CLOB, StandardBasicTypes.STRING.getName());
        registerHibernateType(Types.BLOB, StandardBasicTypes.STRING.getName());
    }  
}
