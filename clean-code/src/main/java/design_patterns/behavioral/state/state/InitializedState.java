package design_patterns.behavioral.state.state;

class InitializedState implements State {


    @Override
    public State block() {
        return this;
    }

    @Override
    public State activate() {
        return new ActiveState();
    }

    @Override
    public State disable() {
        return new DisabledState();
    }

    @Override
    public State unblock() {
        return this;
    }

    @Override
    public String name() {
        return "INITIALIZED";
    }
}
