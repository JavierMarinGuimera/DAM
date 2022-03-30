/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marianao.dam.m09.ftp.client;

import marianao.dam.m09.ftp.client.models.ConditionalNotifier;

/**
 *
 * @author Josep Cañellas <jcanell4@ioc.cat>
 */
public class FtpSynchronizer {
    ConditionalNotifier<Boolean> ready = new ConditionalNotifier<>("R", false);
    ConditionalNotifier<Boolean> enabled = new ConditionalNotifier<>("E", true);

    public void enableSynchronizer() {
        ready.changeCondition(false);
        enabled.changeCondition(true);
    }

    public void disableSynchronizer() {
        enabled.conditionalWait(true);
        enabled.changeCondition(false);
    }

    public boolean isEnabled() {
        return enabled.get();
    }

    public boolean isDisabled() {
        return !enabled.get();
    }

    public void waitingToStart() {
        ready.conditionalWait(true);
    }

    public void youCanStart() {
        ready.changeCondition(true);
    }

    public void waitingToEnabled() {
        enabled.conditionalWait(true);
    }
}
