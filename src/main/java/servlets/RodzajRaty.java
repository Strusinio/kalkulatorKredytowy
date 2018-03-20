package servlets;

public enum RodzajRaty {
	STALA("s", "Rata stala"), MALEJACA("m", "Rata malejaca");

	private String identyfikator;
	private String wartosc;

	private RodzajRaty(String identyfikator, String wartosc) {
		this.identyfikator = identyfikator;
		this.wartosc = wartosc;
	}

	public static RodzajRaty pobierz(String identyfikator) {
		for (RodzajRaty rata : RodzajRaty.values()) {
			if (rata.identyfikator.equals(identyfikator)) {
				return rata;
			}
		}
		return null;
	}

	public String getWartosc() {
		return wartosc;
	}
}
