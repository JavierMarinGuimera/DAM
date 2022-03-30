/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marianao.dam.m09.ftp.client.models;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josep Cañellas <jcanell4@ioc.cat>
 */
public class ConditionalNotifier<T> {
    private String name;
    private T condition;

    public ConditionalNotifier(String name, T initialValue) {
        this.name = name;
        this.condition = initialValue;
    }

    public void conditionalWait(T value) {
        synchronized (this) {
            try {
                while (!condition.equals(value)) {
                    wait();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void changeCondition(T newValue) {
        synchronized (this) {
            if (!condition.equals(newValue)) {
                notify();
                condition = newValue;
            }
        }
    }

    public T get() {
        return condition;
    }
}
