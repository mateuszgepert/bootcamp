package design_patterns.behavioral.state.state;

class DisabledState implements State {

    @Override
    public State block() {
        return this;
    }

    @Override
    public State activate() {
        return this;
    }

    @Override
    public State disable() {
        return this;
    }

    @Override
    public State unblock() {
        return this;
    }

    @Override
    public String name() {
        return "DISABLED";
    }
}
