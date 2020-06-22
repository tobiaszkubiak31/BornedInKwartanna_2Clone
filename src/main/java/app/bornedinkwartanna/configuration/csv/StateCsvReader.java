package app.bornedinkwartanna.configuration.csv;

import app.bornedinkwartanna.domain.State;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StateCsvReader implements CsvReaderInterface {

	private static final String WORD_SEPARATOR = ",";

	@Override
	public List<State> read(String inputFilePath) {
		List<State> states = new ArrayList<>();
		try {
			File inputF = new File(inputFilePath);
			InputStream inputStream = new FileInputStream(inputF);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

			// skip the header of the csv
			states = br.lines().skip(1).map(mapToObject).collect(Collectors.toList());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return states;
	}

	private Function<String, State> mapToObject = (line) -> {
		String[] elements = line.split(WORD_SEPARATOR);

		return State.builder()
			.name(elements[0])
			.groceries(Double.valueOf(elements[1]))
			.preparedFood(Double.valueOf(elements[2]))
			.prescriptionDrug(Double.valueOf(elements[3]))
			.nonPrescriptionDrug(Double.valueOf(elements[4]))
			.clothing(Double.valueOf(elements[5]))
			.intangibles(Double.valueOf(elements[6]))
			.logistics(Double.valueOf(elements[7]))
			.build();
	};

}
