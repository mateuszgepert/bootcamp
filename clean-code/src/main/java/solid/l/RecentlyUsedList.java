package solid.l;

import java.util.ArrayList;

import static java.util.Objects.nonNull;

class RecentlyUsedList extends ArrayList<String> {

    @Override
    public boolean add(String elem) {
        if (nonNull(elem) && elem.isEmpty()) {
            throw new EmptyElementPassedException();
        }
        this.remove(elem);
        this.add(0, elem);
        return true;
    }

    static class EmptyElementPassedException extends RuntimeException {

    }
}
