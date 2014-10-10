package it.unibas.freesbeesla.ws.web.grafico;

import it.unibas.freesbeesla.ws.web.persistenza.soap.DAOException;
import it.unibas.freesbeesla.ws.web.stub.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.ui.HorizontalAlignment;

public class GeneraGrafico {

    private Log logger = LogFactory.getLog(GeneraGrafico.class);
    private StatisticheSLAErogatore statisticheSLAErogatore;
    private String operazione;
    private Double soglia;

    public GeneraGrafico(StatisticheSLAErogatore statistiche) {
        statisticheSLAErogatore = statistiche;
        operazione = statistiche.getOperazione();
        soglia = statistiche.getSoglia();
    }

    public BufferedImage getChartViewer() throws DAOException {
        BufferedImage chartImage = null;
        ChartRenderingInfo info = null;
        JFreeChart chart = null;
        try {

            DefaultCategoryDataset dataset = statisticheSLAErogatore.getDataSetSla();
            chart = ChartFactory.createBarChart3D(costruisciTitolo(), "Date monitoraggio", "Valore parametri SLA", ((CategoryDataset) (dataset)), PlotOrientation.HORIZONTAL, false, false, false);
            chart.setBackgroundPaint(Color.WHITE);

            chart.getTitle().setMargin(25, 25, 25, 0);
            chart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);

            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            plot.setNoDataMessage("Errore. Non sono presenti dati");
            BarrePersonalizzate renderer = new BarrePersonalizzate(soglia, operazione);
            plot.setRenderer(((org.jfree.chart.renderer.category.CategoryItemRenderer) (renderer)));
            plot.setOrientation(PlotOrientation.HORIZONTAL);
            ValueMarker marker = new ValueMarker(soglia.doubleValue(), ((Paint) (new Color(183, 117, 138))), ((java.awt.Stroke) (new BasicStroke(1.0F))), ((Paint) (new Color(183, 117, 138))), ((java.awt.Stroke) (new BasicStroke(1.0F))), 1.0F);
            plot.addRangeMarker(((org.jfree.chart.plot.Marker) (marker)), Layer.BACKGROUND);
            renderer.setMaxBarWidth(0.050000000000000003D);
            CategoryTextAnnotation a = new CategoryTextAnnotation("Soglia", dataset.getColumnKey(0), soglia.doubleValue());
            a.setFont(new Font("SansSerif", 1, 8));

            ValueAxis rangeAxis = chart.getCategoryPlot().getRangeAxis();
            Double upper = Double.valueOf(rangeAxis.getRange().getUpperBound());
            Double lower = Double.valueOf(rangeAxis.getRange().getLowerBound());

            if (upper.doubleValue() < soglia.doubleValue()) {
                Range newRange = new Range(lower.doubleValue(), soglia.doubleValue() + soglia.doubleValue() * 0.10000000000000001D);
                rangeAxis.setRange(newRange);
                a.setTextAnchor(TextAnchor.BASELINE_LEFT);
                a.setRotationAngle(1.6);

            } else if (soglia.doubleValue() < lower.doubleValue()) {
                Range newRange = new Range(soglia.doubleValue() - soglia.doubleValue() * 0.10000000000000001D, upper.doubleValue());
                rangeAxis.setRange(newRange);
                a.setTextAnchor(TextAnchor.BASELINE_LEFT);
                a.setRotationAngle(1.6);

            } else {
                a.setTextAnchor(TextAnchor.BASELINE_LEFT);
                a.setRotationAngle(1.6);

            }

            CategoryAxis catAxis = chart.getCategoryPlot().getDomainAxis();
            catAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
            plot.setDomainAxis(catAxis);
            plot.addAnnotation(((org.jfree.chart.annotations.CategoryAnnotation) (a)));


            info = new ChartRenderingInfo(((org.jfree.chart.entity.EntityCollection) (new StandardEntityCollection())));
            chartImage = chart.createBufferedImage(640, 400, info);
        } catch (Exception exception) {
            logger.error("Errore. " + exception.getMessage());
            throw new DAOException(exception);
        }

        return chartImage;
    }

    private String costruisciTitolo() {
        String titolo = statisticheSLAErogatore.getSlaNome();
        if (operazione.equalsIgnoreCase("Greater")) {
            titolo += " maggiore di ";
        } else if (operazione.equalsIgnoreCase("GreaterEqual")) {
            titolo += " maggiore uguale di ";
        } else if (operazione.equalsIgnoreCase("Less")) {
            titolo += " minore di ";
        } else if (operazione.equalsIgnoreCase("LessEqual")) {
            titolo += " minore uguale di ";
        } else if (operazione.equalsIgnoreCase("Equal")) {
            titolo += " uguale a ";
        } else if (operazione.equalsIgnoreCase("NotEqual")) {
            titolo += " diverso a ";
        }
        titolo += soglia + " richiesto ";
        return titolo;
    }

    class BarrePersonalizzate extends BarRenderer3D {

        Double soglia = null;
        String op = null;

        public Paint getItemPaint(int row, int column) {
            CategoryDataset dataset = getPlot().getDataset();
            Double value = Double.valueOf(dataset.getValue(row, column).doubleValue());
            if (value == null) {
                return ((Paint) (Color.yellow));
            }
            if (isRespected(op, value, soglia)) {
                return ((Paint) (Color.green));
            } else {
                return ((Paint) (Color.red));
            }
        }

        public boolean isRespected(String op, Double val1, Double val2) {
            if (op.equalsIgnoreCase("Greater")) {
                if (val1.doubleValue() > val2.doubleValue()) {
                    return true;
                }
            } else if (op.equalsIgnoreCase("GreaterEqual")) {
                if (val1.doubleValue() >= val2.doubleValue()) {
                    return true;
                }
            } else if (op.equalsIgnoreCase("Less")) {
                if (val1.doubleValue() < val2.doubleValue()) {
                    return true;
                }
            } else if (op.equalsIgnoreCase("LessEqual")) {
                if (val1.doubleValue() <= val2.doubleValue()) {
                    return true;
                }
            } else if (op.equalsIgnoreCase("Equal")) {
                if (val1 == val2) {
                    return true;
                }
            } else if (op.equalsIgnoreCase("NotEqual") && val1 != val2) {
                return true;
            }
            return false;
        }

        public BarrePersonalizzate(Double soglia, String op) {
            super();
            this.soglia = soglia;
            this.op = op;
        }
    }
}
