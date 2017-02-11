package com.azaharzafra.directorio;

import android.provider.BaseColumns;

/* Created by Azahar on 06/12/2016.*/

public class ContactoContract {

    public static abstract class ContactosEntry implements BaseColumns {
        public static final String TABLE_NAME ="Contactos";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String NUMBER = "number";
    }

}
