package com.mycompany.tarea_5;

import Controlador.Controlador;
import Modelo.Conexion;
import Vista.datos_Persona;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TAREA_5 {

    public static void main(String[] args) {
        try {
            // Establecer el look and feel de JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        // Intentar la conexión
        Conexion con = new Conexion();
        Connection connection = null;

        try {
            // Conectar a la base de datos
            connection = con.conectar();

            if (connection != null) {
                JOptionPane.showMessageDialog(null, "Conexión a la base de datos establecida.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión.");
            }
        } finally {
            // Aquí podrías cerrar la conexión si fuera necesario, para evitar fugas de memoria
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión con la base de datos.");
                    e.printStackTrace();
                }
            }
        }

        // Crear instancia de la vista
        datos_Persona vista = new datos_Persona();

        // Crear instancia del controlador
        Controlador controlador = new Controlador(vista);  // Asocia la vista al controlador

        // Ejecutar la interfaz gráfica
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                vista.setVisible(true);  // Mostrar la vista
            }
        });
    }
}
