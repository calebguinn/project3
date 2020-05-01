import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
public class HammingPlot extends Application {
	public static void main(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		XYChart.Series<Number, Number> circular = new XYChart.Series<>();
		circular.setName("circular boundary conditions");
		XYChart.Series<Number, Number> fixed = new XYChart.Series<>();
		fixed.setName("fixed boundary conditions (OFF-OFF)");
		
		List<XYChart.Data<Number, Number>> cData = circular.getData();
		List<XYChart.Data<Number, Number>> fData = fixed.getData();
		
		File file1 = new File("hamming-elementary60-circularbc.txt");
		FileReader reader1 = new FileReader(file1);
		BufferedReader cReader = new BufferedReader(reader1);
		File file2 = new File("hamming-elementary60-fixedbc-off-off.txt");
		FileReader reader2 = new FileReader(file2);
		BufferedReader fReader = new BufferedReader(reader2);
		
		XYChart.Data<Number, Number> zero = new XYChart.Data<>(0, 0);
		cData.add(zero);
		fData.add(zero);
		
		for(int i = 0; i < 100; i++) {
			String line = cReader.readLine();
			int cPoint = Integer.parseInt(line);
			line = fReader.readLine();
			int fPoint = Integer.parseInt(line);
			
			XYChart.Data<Number, Number> cPt = new XYChart.Data<>(i+1, cPoint);
			XYChart.Data<Number, Number> fPt = new XYChart.Data<>(i+1, fPoint);
			cData.add(cPt);
			fData.add(fPt);
		}
		cReader.close();
		fReader.close();
		
		NumberAxis xAxis = new NumberAxis("step number", 0, 100, 10);
		NumberAxis yAxis = new NumberAxis("hamming distance", 0, 100, 10);
		
		LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle("Elementary Rule 60 (Caleb Guinn, ID #113452367)");
		chart.setCreateSymbols(false);
		
		List<XYChart.Series<Number, Number>> seriesList = chart.getData();
		seriesList.add(circular);
		seriesList.add(fixed);
		
		Scene scene = new Scene(chart, 960, 600);
		
		stage.setScene(scene);
		stage.setTitle("Elementary Rule 60 (Caleb Guinn, ID #113452367");
		stage.show();
		
		String filename = "plots" + File.separator + "hamming-elementary60.png";
		saveScene(scene, filename);
	}

	public static void saveScene(Scene scene, String filename) throws IOException {	
		// Convert the given Scene to an image that can be written to a file.
		WritableImage image = scene.snapshot(null);
		BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
		
		// Create a file with the given name and save the image as a PNG.
		File output = new File(filename);
		ImageIO.write(bufferedImage, "png", output);
	}
}
