package design_patterns.behavioral.state.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class BlockedState implements State {

    public State block() {
        return this;
    }

    @Override
    public State activate() {
        return this;
    }

    @Override
    public State disable() {
        System.out.println("Someone tries to disable blocked account");
        return this;
    }

    @Override
    public State unblock() {
        return new ActiveState();
    }

    @Override
    public String name() {
        return "BLOCKED";
    }
}
