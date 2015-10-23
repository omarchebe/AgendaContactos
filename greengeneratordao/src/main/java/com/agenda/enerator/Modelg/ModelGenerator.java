package com.agenda.enerator.Modelg;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by gaku on 1/9/14.
 */


public class ModelGenerator {

    private static final String OUTPUT_SOURCE_GEN = "../Agenda/app/src/main/java";
    private static final String SCHEMA_NAME = "com.agenda.omarche.agenda.model";
    private static int SCHEMA_VERSION = 1;

    private static Entity contacto;


    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(SCHEMA_VERSION, SCHEMA_NAME);
        schema.enableKeepSectionsByDefault();

        mapCatalogos(schema);

        new DaoGenerator().generateAll(schema, OUTPUT_SOURCE_GEN);
    }

    private static void mapCatalogos(Schema schema) {
        contacto = schema.addEntity("Contacto");
        contacto.addLongProperty("contactoId").primaryKey().autoincrement();
        contacto.addStringProperty("Nombre").notNull();
        contacto.addStringProperty("CorreoElectronico");
        contacto.addStringProperty("Telefono");
        contacto.addStringProperty("Direccion");
        contacto.addStringProperty("imagenUri");
    }
}
