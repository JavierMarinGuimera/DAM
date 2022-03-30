/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marianao.dam.m09.ftp.client;

/**
 *
 * @author Josep Cañellas <jcanell4@ioc.cat>
 */
public class FtpSynchronizer {
    ConditionalNotifier<Boolean> enabled = new ConditionalNotifier<>("E", true);
    ConditionalNotifier<Boolean> ready = new ConditionalNotifier<>("R", false);

    public void disableSynchronizer() {
        enabled.conditionalWait(Boolean.TRUE);
        enabled.changeCondition(Boolean.FALSE);
    }

    public void enableSynchornizer() {
        ready.changeCondition(Boolean.FALSE);
        enabled.changeCondition(Boolean.TRUE);
    }

    public boolean isEnabled() {
        return enabled.get();
    }

    public boolean isDisabled() {
        return !enabled.get();
    }

    public void waitingToStart() {
        ready.conditionalWait(Boolean.TRUE);
    }

    public void youCanStart() {
        ready.changeCondition(Boolean.TRUE);
    }

    public void waitingToEnabled() {
        enabled.conditionalWait(Boolean.TRUE);
    }
}
