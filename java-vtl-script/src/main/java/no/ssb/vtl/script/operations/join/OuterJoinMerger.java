package no.ssb.vtl.script.operations.join;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.ToIntFunction;

public class OuterJoinMerger implements BiFunction<DataPoint, DataPoint, DataPoint> {

    private final Map<String, String> rightMapping;
    //private final Map<String, String> leftMapping;

    //private final DataPointView leftView;
    private final DataPointView rightView;
    private final DataPointView resultView;
    private final int size;

    public OuterJoinMerger(AbstractJoinOperation joinOperation, Dataset left, Dataset right) {

        Table<String, String, String> mapping = AbstractJoinOperation.getColumnMapping(joinOperation.datasets);
        ImmutableBiMap<Dataset, String> datasetNames = ImmutableBiMap.copyOf(joinOperation.datasets).inverse();

        size = mapping.rowKeySet().size();

        rightMapping = mapping.column(datasetNames.get(right));
        //leftMapping = mapping.column(datasetNames.get(right));

        //leftView = new DataPointView(joinOperation.getDataStructure());
        rightView = new DataPointView(right.getDataStructure());
        resultView = new DataPointView(joinOperation.getDataStructure());
    }

    @Override
    public DataPoint apply(DataPoint left, DataPoint right) {

        resultView.setDataDoint(DataPoint.create(size));
        //leftView.setDataDoint(left);
        rightView.setDataDoint(right);

        if (left != null) {
            resultView.setDataDoint(DataPoint.create(left));
            //for (Map.Entry<String, String> mapping : leftMapping.entrySet()) {
            //    resultView.put(mapping.getKey(), leftView.get(mapping.getValue()));
            //}
        } else {
            resultView.setDataDoint(DataPoint.create(size));
        }
        if (right != null) {
            for (Map.Entry<String, String> mapping : rightMapping.entrySet()) {
                resultView.put(mapping.getKey(), rightView.get(mapping.getValue()));
            }
        }
        return resultView.getDataDoint();
    }

    private final class DataPointView implements Map<String, VTLObject> {

        private final ToIntFunction<String> hash;
        private DataPoint dp = DataPoint.create(0);

        public DataPointView(ToIntFunction<String> hash) {
            this.hash = hash;
        }

        public DataPointView(DataStructure structure) {
            ImmutableList<String> list = ImmutableSet.copyOf(structure.keySet()).asList();
            this.hash = list::indexOf;
        }

        public VTLObject get(String key) {
            return dp.get(hash.applyAsInt(key));
        }

        public VTLObject remove(String key) {
            return dp.set(hash.applyAsInt(key), VTLObject.NULL);
        }

        public DataPoint getDataDoint() {
            return dp;
        }

        public void setDataDoint(DataPoint dp) {
            this.dp = dp;
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isEmpty() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsKey(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsValue(Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public VTLObject get(Object key) {
            return this.get((String) key);
        }

        @Override
        public VTLObject put(String key, VTLObject value) {
            return dp.set(hash.applyAsInt(key), value);
        }

        @Override
        public VTLObject remove(Object key) {
            return this.remove((String) key);
        }

        @Override
        public void putAll(Map<? extends String, ? extends VTLObject> m) {
            for (Entry<? extends String, ? extends VTLObject> entry : m.entrySet()) {
                this.put(entry.getKey(), entry.getValue());
            }
        }

        @Override
        public void clear() {
            dp.clear();
        }

        @Override
        public Set<String> keySet() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Collection<VTLObject> values() {
            return dp;
        }

        @Override
        public Set<Entry<String, VTLObject>> entrySet() {
            throw new UnsupportedOperationException();
        }
    }
}
