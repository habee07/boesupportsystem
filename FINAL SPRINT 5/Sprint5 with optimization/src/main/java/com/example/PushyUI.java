package com.example;


import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

/**
 * Created by habee on 2018/11/15.
 */
@Push
public class PushyUI extends UI {
   // Chart chart = new Chart(ChartType.AREASPLINE);
    //DataSeries series = new DataSeries();

    @Override
    protected void init(VaadinRequest request) {
       /** chart.setSizeFull();
        setContent(chart);

        // Prepare the data display
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Hot New Data");
        conf.setSeries(series);

        // Start the data feed thread
        new FeederThread().start();
        **/
    }

   /** class FeederThread extends Thread {
        int count = 0;

        @Override
        public void run() {
            try {
                // Update the data for a while
                while (count < 100) {
                    Thread.sleep(1000);

                    access(new Runnable() {
                        @Override
                        public void run() {
                            double y = Math.random();
                            series.add(
                                    new DataSeriesItem(count++, y),
                                    true, count > 10);
                        }
                    });
                }

                // Inform that we have stopped running
                access(new Runnable() {
                    @Override
                    public void run() {
                        setContent(new Label("Done!"));
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
        **/
}