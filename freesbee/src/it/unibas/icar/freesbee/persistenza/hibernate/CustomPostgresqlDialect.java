package it.unibas.icar.freesbee.persistenza.hibernate;

import org.hibernate.dialect.PostgreSQLDialect;

public class CustomPostgresqlDialect extends PostgreSQLDialect {

    @Override
    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String  referencedTable, String[] primaryKey, boolean arg4) {
        return super.getAddForeignKeyConstraintString(constraintName, foreignKey,  referencedTable, primaryKey, arg4) + " deferrable initially immediate";
    }
}
