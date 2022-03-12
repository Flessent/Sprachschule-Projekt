package dre.org;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL10Dialect;

import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;

public class PostgresSQL12JsonDialect extends PostgreSQL10Dialect{


    public PostgresSQL12JsonDialect() {
        super();
        this.registerHibernateType(
            Types.OTHER, JsonNodeBinaryType.class.getName()
        );
    }
}
