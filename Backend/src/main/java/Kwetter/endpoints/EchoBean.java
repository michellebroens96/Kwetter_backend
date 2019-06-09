/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Kwetter.endpoints;

import java.io.IOException;
import javax.ejb.Asynchronous;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.websocket.EncodeException;
import javax.websocket.Session;


@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EchoBean {

    @EJB
    private EchoBean delegate;

    @Asynchronous
    public void send(Session session, Message message, int repeats, long delay, double delayMultiplier) {
        try {
            synchronized(session) {
                session.getBasicRemote().sendObject(message);
            }
            Thread.sleep(delay);
        }
        catch(IOException | EncodeException e) {
            throw new IllegalStateException(e);
        }
        catch(InterruptedException e) {
            throw  new IllegalStateException(e);
        }
        if(1 < repeats) {
            delegate.send(
                    session,
                    new Message("." + message.getText()),
                    repeats - 1,
                    Math.round(delay * delayMultiplier),
                    delayMultiplier
            );
        }

    }
}
