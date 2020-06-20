package app.bornedinkwartanna.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "states")
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String name;
	@Column
	private Double groceries;
	@Column
	private Double preparedFood;
	@Column
	private Double prescriptionDrug;
	@Column
	private Double nonPrescriptionDrug;
	@Column
	private Double clothing;
	@Column
	private Double intangibles;
	@Column
	private Double logistics;

}
