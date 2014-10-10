package it.unibas.freesbeesla.tracciatura.modello.map;

import java.util.Date;
import java.math.BigDecimal;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.TableMap;

/**
  *  This class was autogenerated by Torque on:
  *
  * [Tue Dec 14 20:27:47 CET 2010]
  *
  */
public class ServiceMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "it.unibas.freesbeesla.tracciatura.modello.map.ServiceMapBuilder";

    /**
     * The database map.
     */
    private DatabaseMap dbMap = null;

    /**
     * Tells us if this DatabaseMapBuilder is built so that we
     * don't have to re-build it every time.
     *
     * @return true if this DatabaseMapBuilder is built
     */
    public boolean isBuilt()
    {
        return (dbMap != null);
    }

    /**
     * Gets the databasemap this map builder built.
     *
     * @return the databasemap
     */
    public DatabaseMap getDatabaseMap()
    {
        return this.dbMap;
    }

    /**
     * The doBuild() method builds the DatabaseMap
     *
     * @throws TorqueException
     */
    public void doBuild() throws TorqueException
    {
        dbMap = Torque.getDatabaseMap("freesbee_sla");

        dbMap.addTable("icar_inf2_service");
        TableMap tMap = dbMap.getTable("icar_inf2_service");

        tMap.setPrimaryKeyMethod("none");


              tMap.addPrimaryKey("icar_inf2_service.INF2_ID_SERVICE", "" );
                    tMap.addPrimaryKey("icar_inf2_service.INF2_ID_INITIATOR", "" );
                    tMap.addPrimaryKey("icar_inf2_service.INF2_ID_RESPONDER", "" );
                        	          tMap.addColumn("icar_inf2_service.INF2_ID_AGREEMENT", "", 80 );
                                      	          tMap.addColumn("icar_inf2_service.INF2_ID_STATE", "", 30 );
                                        tMap.addColumn("icar_inf2_service.INF2_SLA_OBJECT", "" );
                                tMap.addColumn("icar_inf2_service.INF2_COUNT_PENDING_REQUEST", new Long(0) );
                                tMap.addColumn("icar_inf2_service.INF2_ACTIVE", new Long(0) );
                }
}
