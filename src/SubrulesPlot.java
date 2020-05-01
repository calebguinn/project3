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
public class SubrulesPlot extends Application {
	public static void main(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		XYChart.Series<Number, Number> sub0 = new XYChart.Series<>();
		XYChart.Series<Number, Number> sub1 = new XYChart.Series<>();
		XYChart.Series<Number, Number> sub2 = new XYChart.Series<>();
		XYChart.Series<Number, Number> sub3 = new XYChart.Series<>();
		XYChart.Series<Number, Number> sub4 = new XYChart.Series<>();
		XYChart.Series<Number, Number> sub5 = new XYChart.Series<>();
		XYChart.Series<Number, Number> sub6 = new XYChart.Series<>();
		XYChart.Series<Number, Number> sub7 = new XYChart.Series<>();
		sub0.setName("subrule 0");
		sub1.setName("subrule 1");
		sub2.setName("subrule 2");
		sub3.setName("subrule 3");
		sub4.setName("subrule 4");
		sub5.setName("subrule 5");
		sub6.setName("subrule 6");
		sub7.setName("subrule 7");
		List<XYChart.Data<Number, Number>> zeroData = sub0.getData();
		List<XYChart.Data<Number, Number>> oneData = sub1.getData();
		List<XYChart.Data<Number, Number>> twoData = sub2.getData();
		List<XYChart.Data<Number, Number>> threeData = sub3.getData();
		List<XYChart.Data<Number, Number>> fourData = sub4.getData();
		List<XYChart.Data<Number, Number>> fiveData = sub5.getData();
		List<XYChart.Data<Number, Number>> sixData = sub6.getData();
		List<XYChart.Data<Number, Number>> sevenData = sub7.getData();
		
		File file = new File("subrules-elementary60-circularbc.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		for(int i = 0; i < 100; i++) {
			String line = br.readLine();
			String[] arr = line.split(", ");
			for(int j = 0; j < arr.length; j++) {
				int x = Integer.parseInt(arr[j]);
				XYChart.Data<Number, Number> point = new XYChart.Data<>(i+1, x);
				if(j == 0) {
					zeroData.add(point);
				}
				else if(j == 1) {
					oneData.add(point);
				}
				else if(j == 2) {
					twoData.add(point);
				}
				else if(j == 3) {
					threeData.add(point);
				}
				else if(j == 4) {
					fourData.add(point);
				}
				else if(j == 5) {
					fiveData.add(point);
				}
				else if(j == 6) {
					sixData.add(point);
				}
				else if(j == 7) {
					sevenData.add(point);
				}
			}
		}
		br.close();
		
		NumberAxis xAxis = new NumberAxis("step number", 0, 100, 10);
		NumberAxis yAxis = new NumberAxis("subrule count", 0, 100, 10);
		
		LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		chart.setTitle("Elementary Rule 60 (Caleb Guinn, ID #113452367)");
		chart.setCreateSymbols(false);
		
		List<XYChart.Series<Number, Number>> seriesList = chart.getData();
		seriesList.add(sub0);
		seriesList.add(sub1);
		seriesList.add(sub2);
		seriesList.add(sub3);
		seriesList.add(sub4);
		seriesList.add(sub5);
		seriesList.add(sub6);
		seriesList.add(sub7);
		
		Scene scene = new Scene(chart, 960, 600);
		
		stage.setScene(scene);
		stage.setTitle("Elementary Rule 60 (Caleb Guinn, ID #113452367");
		stage.show();
		
		String filename = "plots" + File.separator + "subrules-elementary60.png";
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
