package com.mycompany.ceng431_hmw3.Controller;

import com.mycompany.ceng431_hmw3.ConcreteClasses.Model;
import com.mycompany.ceng431_hmw3.Views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private LoginView loginView;
    private Model model;

    public LoginController() {
        this.loginView = new LoginView();
        this.model=Model.getInstance();
        model.registerObserver(loginView);

        loginView.addLoginButtonActionListener(new LoginButtonListener());
        loginView.setVisible(true);
    }


    private class LoginButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            model.login(loginView.getUsername(),loginView.getPassword());

        }
    }

    public LoginView getLoginView()
    {
        return loginView;
    }
}
