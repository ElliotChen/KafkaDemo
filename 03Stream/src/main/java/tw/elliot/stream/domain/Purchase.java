package tw.elliot.stream.domain;

import lombok.Data;

/**
 * @author elliot
 */
@Data
public class Purchase {
	private String memberId;
	private String itemId;
	private Double amount;
}
