package com.mycompany.ceng431_hmw3;

import com.mycompany.ceng431_hmw3.ConcreteClasses.LoginState;
import com.mycompany.ceng431_hmw3.ConcreteClasses.Model;
import com.mycompany.ceng431_hmw3.Controller.LoginController;
import com.mycompany.ceng431_hmw3.Controller.MainController;
import com.mycompany.ceng431_hmw3.Views.MainView;

public class Application
{
    private Model model;

    public Application() {
        model = Model.getInstance();
    }

    public void run() throws InterruptedException {
        LoginController loginController = new LoginController();



        while(model.getLoginState()== LoginState.NON_AUTHORIZED)
        {
            Thread.sleep(1);
        }
        model.removeObserver(loginController.getLoginView());
        MainView mainView = new MainView();
        model.registerObserver(mainView);










        MainController mainController = new MainController(mainView);
        mainView.setVisible(true);

    }
}
