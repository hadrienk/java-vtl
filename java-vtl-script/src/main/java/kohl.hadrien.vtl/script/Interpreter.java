package kohl.hadrien.vtl.script;

import com.google.common.collect.ImmutableMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.script.ScriptException;
import kohl.hadrien.Attribute;
import kohl.hadrien.Component;
import kohl.hadrien.DataStructure;
import kohl.hadrien.Dataset;
import kohl.hadrien.Identifier;
import kohl.hadrien.Measure;
import kohl.hadrien.vtl.script.connector.Connector;
import kohl.hadrien.vtl.script.connector.ConnectorException;

public class Interpreter {

  public static void main(String... args) throws IOException {

    VTLScriptEngine vtlScriptEngine = new VTLScriptEngine(getFakeConnector());


    try (
        PrintStream output = System.out;
        PrintStream error = System.err;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    ) {

      output.print(">");

      String read;
      while ((read = input.readLine()) != null) {
        try {
          Object result = vtlScriptEngine.eval(read);
          if (result instanceof Dataset) {
            Dataset dataset = (Dataset) result;
            printDataset(output, dataset);
          }
          output.println(result);
          output.flush();
        } catch (ScriptException e) {
          e.printStackTrace(error);
        } finally {
          output.print(">");
        }
      }

    } catch (Throwable t) {
      t.printStackTrace();
    }

  }

  private static void printDataset(PrintStream output, Dataset dataset) {
    // Quickly print stream for now.
    for (Map.Entry<String, Class<? extends Component>> component : dataset
        .getDataStructure().entrySet()) {
      output.print(component.getKey());
      output.print(component.getValue());
      output.print(",");
    }
    output.println();
    for (Dataset.Tuple tuple : (Iterable<Dataset.Tuple>) dataset.stream()::iterator) {
      for (Component component : tuple) {
        output.print(component.get());
        output.print(",");
      }
      output.println();
    }
  }

  static Connector getFakeConnector() {

    DataStructure dataStructure = new DataStructure(ImmutableMap.of(
        "id", Identifier.class,
        "measure", Measure.class,
        "attribute", Attribute.class
    ));

    return new Connector() {

      @Override
      public boolean canHandle(String identifier) {
        return true;
      }

      @Override
      public Dataset getDataset(String identifier) throws ConnectorException {
        return new Dataset() {

          @Override
          public Stream<Tuple> get() {

            return IntStream.rangeClosed(1, 100).boxed()
                .map(integer -> {
                  Identifier<Integer> identifier = new Identifier<Integer>(Integer.class) {
                    @Override
                    protected String name() {
                      return "id";
                    }

                    @Override
                    public Integer get() {
                      return integer;
                    }
                  };

                  Measure<String> measure = new Measure<String>(String.class) {
                    @Override
                    protected String name() {
                      return "measure";
                    }

                    @Override
                    public String get() {
                      return "measure" + integer;
                    }
                  };

                  Attribute<String> attribute = new Attribute<String>(String.class) {
                    @Override
                    protected String name() {
                      return "attribute";
                    }

                    @Override
                    public String get() {
                      return "attribute" + integer;
                    }
                  };

                  return new AbstractTuple() {


                    @Override
                    public List<Identifier> ids() {
                      return Arrays.asList(identifier);
                    }

                    @Override
                    public List<Component> values() {
                      return Arrays.asList(measure, attribute);
                    }
                  };
                });
          }

          @Override
          public Set<List<Identifier>> cartesian() {
            return null;
          }

          @Override
          public DataStructure getDataStructure() {
            return dataStructure;
          }
        };

      }

      @Override
      public Dataset putDataset(String identifier, Dataset dataset) throws ConnectorException {
        return dataset;
      }
    };
  }

}
