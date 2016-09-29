package kohl.hadrien;

import com.codepoetics.protonpack.StreamUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public abstract class AbstractRelationalDataset extends ForwardingStream<Dataset.Tuple> implements Dataset {

    @Override
    public Set<List<Identifier>> cartesian() {
        return null;
    }

    @Override
    public DataStructure getDataStructure() {
        return null;
    }

    @Override
    public Dataset merge(Dataset dataset) {
        return new AbstractRelationalDataset() {
            @Override
            Stream<Tuple> delegate() {
                return StreamUtils.join(Tuple::combine, AbstractRelationalDataset.this, dataset);
            }
        };
    }

    @Override
    public Dataset union(Dataset dataset) {
        return null;
    }

    @Override
    public Dataset intersect(Dataset dataset) {
        return null;
    }

    @Override
    public Dataset symdiff(Dataset dataset) {
        return null;
    }

    @Override
    public Dataset setdiff(Dataset dataset) {
        return null;
    }

}
