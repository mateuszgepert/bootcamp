package solid.l;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class ComplexListProvider {

    private List<String> list = new ArrayList<>();

    List<String> getSomeComplexList() {
        list.add("first");
        list.add("second");
        list.add("second");
        list.add("");

        return list;
    }

    void switchLists() {
        if(list instanceof ArrayList<String>) {
            list = new LinkedList<>();
        } else {
            list = new ArrayList<>();
        }
    }
}
