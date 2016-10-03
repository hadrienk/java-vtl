package kohl.hadrien;

import com.google.common.collect.ForwardingMap;

import java.util.Map;

/**
 * Created by hadrien on 07/09/16.
 */
public class DataStructure extends ForwardingMap<String, Component> {



    public DataStructure(Iterable<Identifier> identifiers, Iterable<Component> measures) {
    }

    @Override
    protected Map<String, Component> delegate() {
        return null;
    }
}
