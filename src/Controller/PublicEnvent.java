/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author FPTSHOP
 */
public class PublicEnvent {

    private static PublicEnvent instance;
    private EventLogin eventLogin;
    public EventMain eventMain;

    public EventMain getEventMain() {
        return eventMain;
    }

    public void addEventMain(EventMain eventMain) {
        this.eventMain = eventMain;
    }
    public static PublicEnvent getInstance() {
        if (instance == null) {
            instance = new PublicEnvent();
        }
        return instance;
    }

    public EventLogin getEventLogin() {
        return eventLogin;
    }



    public static void setInstance(PublicEnvent instance) {
        PublicEnvent.instance = instance;
    }

    public void addEventLogin(EventLogin eventLogin) {
        this.eventLogin = eventLogin;
    }


    
}
