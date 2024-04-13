package ventanaDElogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class ventandelogincomobiblioteca extends JFrame {
    
    private JButton registrarButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton salirButton;
    private JLabel statusLabel;

    public ventandelogincomobiblioteca() {
        setTitle("Login");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("usuario:");
        JLabel passwordLabel = new JLabel("contraseña:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        salirButton = new JButton("salir");
        loginButton = new JButton("Login");
        statusLabel = new JLabel("");

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(salirButton);
        add(loginButton);
        add(statusLabel);
        
        registrarButton = new JButton("Registrarse");
    add(registrarButton);

    registrarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                if (register(username, password)) {
                    statusLabel.setText("Usuario registrado exitosamente!");
                } else {
                    statusLabel.setText("El usuario ya existe!");
                }
            } else {
                statusLabel.setText("Por favor, ingrese un nombre de usuario y contraseña.");
            }
        }
    });
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword( ));

                if (authenticate(username, password)) {
                    statusLabel.setText("Login successful!");
                    
                } else {
                    statusLabel.setText("Invalido nombre o password!");
                }
            }
        });
    }
    


    private boolean authenticate(String username, String password) {
        
        return username.equals("admin") && password.equals("password");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ventandelogincomobiblioteca loginWindow = new ventandelogincomobiblioteca();
                loginWindow.setVisible(true);
            }
        });
    }


    private boolean register(String username, String password) {
    try {
        FileWriter writer = new FileWriter("usuarios.txt", true);
        PrintWriter printer = new PrintWriter(writer);


        printer.println(username + ":" + password);

        printer.close();
        return true;
    } catch (IOException ex) {
        ex.printStackTrace();
        return false;
    }
}
}
            