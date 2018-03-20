package servlets;

import java.util.ArrayList;
import java.util.List;

public class Model {
	private Long kwota;
	private Long iloscRat;
	private Long procent;
	private Long stala;
	private String blad;
	private RodzajRaty rodzajRaty;

	private List<Splata> splaty = new ArrayList<>();

	public Model() {

	}

	public Model(String kwota, String rata, String procent, String stala, String rodzajRaty) {
		if (kwota == null || rata == null || procent == null || stala == null || rodzajRaty == null) {
			blad = "Wypelnij wszystkie dane!";
		} else {
			try {
				this.kwota = Long.parseLong(kwota);
				this.iloscRat = Long.parseLong(rata);
				this.procent = Long.parseLong(procent);
				this.stala = Long.parseLong(stala);
				this.rodzajRaty = RodzajRaty.pobierz(rodzajRaty);
				stworzSplaty();
			} catch (NumberFormatException e) {
				blad = "Wypelnij dane poprawnie";
			}
		}
	}

	public Long getKwota() {
		return kwota;
	}

	public Long getRata() {
		return iloscRat;
	}

	public Long getProcent() {
		return procent;
	}

	public Long getStala() {
		return stala;
	}

	public String getBlad() {
		return blad;
	}

	public void setBlad(String blad) {
		this.blad = blad;
	}

	public RodzajRaty getRodzajRaty() {
		return rodzajRaty;
	}

	private void stworzSplaty() {
		Double rataStala = policzRateStala();
		for (int i = 0; i < iloscRat; i++) {
			Splata splata = new Splata();
			splata.setNumerRaty(i + 1);
			splata.setOplaty(stala);
			if (rodzajRaty == RodzajRaty.STALA) {
				splata.setCalowitaKwotaRaty(rataStala + splata.getOplaty());
			} else {
				splata.setCalowitaKwotaRaty(policzRateMalejaca(splata.getNumerRaty()) + splata.getOplaty());
			}
			splata.setKwota((double) kwota / iloscRat);
			splata.setOdsetki(splata.getCalowitaKwotaRaty() - splata.getKwota() - splata.getOplaty());
			splaty.add(splata);
		}
	}

	private Double policzRateStala() {
		Double stalaProcentowa = (1 + ((double) 1 / 12) * ((double) procent / 100));
		return (kwota * Math.pow(stalaProcentowa, iloscRat) * (stalaProcentowa - 1))
				/ ((double) Math.pow(stalaProcentowa, iloscRat) - 1);
	}

	private Double policzRateMalejaca(int numerRaty) {
		return ((double) kwota / iloscRat) * ((1 + (iloscRat - numerRaty + 1) * (((double) procent / 100) / 12)));
	}

	public List<Splata> getSplaty() {
		return splaty;
	}

}
