package org.labisd;

public class Peso {

	/**
	 * il peso restituito deve essere sempre >= 0
	 * 
	 * ma non lo è per (gas = O, volume = 100)
	 * basta quindi 2-wise per individuare il malfunzionamento
	 */
	public static int peso(String gas, int pressione, int volume) {
		
		if (gas.equals("H")) {
			return (pressione - 5) * volume;
		} else if (gas.equals("O")) {
			return pressione * (volume - 200);
		} else {
			return pressione * volume;
		}
	}

	/**
	 * il peso restituito deve essere sempre >= 0
	 * 
	 * ma non lo è per (gas = O, pressione = 15, volume = 100)
	 * serve quindi 3-wise (esaustivo) per individuare il malfunzionamento
	 */
	public static int pesoEsteso(String gas, int pressione, int volume) {
		
		if (gas.equals("H")) {
			return (pressione - 5) * volume;
		} else if (gas.equals("O")) {
			if (pressione > 10)
				return pressione * (volume - 200);
			else 
				return pressione/2 * (volume - 100);
		} else {
			return pressione * volume;
		}
	}

}
