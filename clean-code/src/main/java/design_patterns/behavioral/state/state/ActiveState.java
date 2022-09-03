package design_patterns.behavioral.state.state;

class ActiveState implements State{

    public State block() {
        return new BlockedState();
    }

    @Override
    public State activate() {
        return this;
    }

    @Override
    public State disable() {
        return new DisabledState();
    }

    @Override
    public State unblock() {
        return new ActiveState();
    }

    @Override
    public String name() {
        return "ACTIVE";
    }
}
