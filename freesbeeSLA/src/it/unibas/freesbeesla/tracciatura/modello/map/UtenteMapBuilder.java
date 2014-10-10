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
public class UtenteMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "it.unibas.freesbeesla.tracciatura.modello.map.UtenteMapBuilder";

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

        dbMap.addTable("utente");
        TableMap tMap = dbMap.getTable("utente");

        tMap.setPrimaryKeyMethod("none");


              tMap.addPrimaryKey("utente.USERNAME", "" );
                        	          tMap.addColumn("utente.PASSWORD", "", 100 );
                        }
}
