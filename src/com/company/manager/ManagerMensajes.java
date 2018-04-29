package com.company.manager;

import com.company.Database;
import com.company.model.Mensaje;

import java.util.List;

public class ManagerMensajes {

    public boolean enviarMensaje(int emisorID, String receptor, String mensaje){
        return Database.get().enviarMensaje(emisorID,receptor,mensaje);
    }

    public List<Mensaje> selectMensajesUsuario(int receptorID){
        return Database.get().selectMensajesUsuario(receptorID);
    }

    public List<Mensaje> selectMensajesNoLeidosUsuario(int receptorID){
        return Database.get().selectMensajesNoLeidosUsuario(receptorID);
    }
    public List<Mensaje> marcarMensajesLeidos(int receptorID){
        return Database.get().marcarMensajesLeidos(receptorID);
    }

    public int contarMensajesNoLeidos(int receptorID){
        return Database.get().contarMensajesNoLeidos(receptorID);
    }

}
