import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        Random r = new Random();
        BST<Double> bst = new BST<Double>();
        BST<Integer> bst2 = new BST<Integer>();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> yValues2 = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            Double thisY = r.nextDouble();
            xValues.add(i);
            bst.add(thisY);
            bst2.add(i);
            yValues.add(ExperimentHelper.optimalAverageDepth(bst.size()));
            yValues2.add(ExperimentHelper.optimalAverageDepth(bst2.size()));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
//        chart.addSeries("Random Insertion", xValues, yValues);
        chart.addSeries("Sequential Insertion", xValues, yValues2);
        new SwingWrapper(chart).displayChart();

    }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
        experiment1();
    }
}
